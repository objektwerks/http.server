package objektwerks

import com.sun.net.httpserver.HttpServer
import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.LazyLogging

import java.net.InetSocketAddress

object Server extends LazyLogging:
  val config = ConfigFactory.load("server.conf")
  val host = config.getString("host")
  val port = config.getInt("port")
  val backlog = 0
  val http = HttpServer.create(new InetSocketAddress(port), backlog)

  @main def main(): Unit = logger.info(s"Http Server started at: $host:$port")
