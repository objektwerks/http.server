package objektwerks

import com.sun.net.httpserver.HttpServer
import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.LazyLogging

final class Server extends LazyLogging:
  val config = ConfigFactory.load("server.conf")
  val host = config.getString("host")
  val port = config.getInt("port")
  