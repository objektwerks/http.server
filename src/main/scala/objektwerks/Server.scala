package objektwerks

import com.sun.net.httpserver.{HttpExchange, HttpHandler, HttpServer}
import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.LazyLogging

import java.net.InetSocketAddress
import java.time.Instant
import java.util.concurrent.Executors

object Server extends LazyLogging:
  private val config = ConfigFactory.load("server.conf")
  private val host = config.getString("host")
  private val port = config.getInt("port")
  private val address = InetSocketAddress(port)
  private val backlog = 0
  private val path = "/now"
  private val handler = NowHandler()
  private val filter = CorsFilter()
  private val http = HttpServer
    .create(
      address,
      backlog,
      "/now",
      handler,
      filter
    )

  @main def main(): Unit =
    http.setExecutor( Executors.newVirtualThreadPerTaskExecutor() )
    http.start()

    println(s"*** Press Control-C to shutdown server at: $host:$port")
    logger.info(s"*** Http Server started at: $host:$port")

    sys.addShutdownHook {
      logger.info(s"*** Http Server shutdown at: $host:$port")
      http.stop(3)
    }

    Thread.currentThread().join()