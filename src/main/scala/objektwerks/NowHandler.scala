package objektwerks

import com.sun.net.httpserver.{HttpExchange, HttpHandler}

import java.time.Instant

class NowHandler extends HttpHandler:
  override def handle(exchange: HttpExchange): Unit =
    val response = Instant.now.toString
    exchange.sendResponseHeaders(200, response.length())
    val outputStream = exchange.getResponseBody
    outputStream.write(response.getBytes())
    outputStream.flush()
    outputStream.close()
