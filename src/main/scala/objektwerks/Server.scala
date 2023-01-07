package objektwerks

import com.sun.net.httpserver.HttpServer
import com.typesafe.config.ConfigFactory

final class Server:
  val config = ConfigFactory.load("server.conf")
  val host = config.getString("host")
  val port = config.getInt("port")
  