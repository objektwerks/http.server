package objektwerks

import com.sun.net.httpserver.{HttpExchange, HttpHandler}

import java.time.Instant

final class NowHandler extends HttpHandler:
  override def handle(exchange: HttpExchange): Unit =
    val response = Instant.now.toString
    exchange.sendResponseHeaders(200, response.length())
    exchange.getResponseHeaders().add("Content-Type", "application/json; charset=UTF-8")
    val outputStream = exchange.getResponseBody
    outputStream.write(response.getBytes())
    outputStream.flush()
    outputStream.close()
