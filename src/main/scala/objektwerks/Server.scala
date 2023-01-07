package objektwerks

import com.sun.net.httpserver.{HttpExchange, HttpHandler, HttpServer}
import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.LazyLogging

import java.net.InetSocketAddress
import java.time.Instant
import java.util.concurrent.Executors

object Server extends LazyLogging:
  val config = ConfigFactory.load("server.conf")
  val host = config.getString("host")
  val port = config.getInt("port")
  val backlog = config.getInt("backlog")

  val http = HttpServer.create(InetSocketAddress(port), backlog)
  val handler = new HttpHandler {
    override def handle(exchange: HttpExchange): Unit =
      val response = Instant.now.toString
      exchange.sendResponseHeaders(200, response.length())
      val outputStream = exchange.getResponseBody()
      outputStream.write(response.getBytes())
      outputStream.close()
  }

  @main def main(): Unit =
    http.setExecutor(Executors.newVirtualThreadPerTaskExecutor())
    http.createContext("/", handler)

    http.start()
    logger.info(s"*** Http Server started at: $host:$port")
    println("*** Press Control-C to terminate server.")

    Thread.currentThread().join()

  sys.addShutdownHook {
    http.stop(3)
    logger.info(s"*** Http Server shutdown at: $host:$port")
    println("*** Server terminated.")
  }
