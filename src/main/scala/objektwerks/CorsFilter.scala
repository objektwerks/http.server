package objektwerks

import com.sun.net.httpserver.Filter
import com.sun.net.httpserver.Filter.Chain
import com.sun.net.httpserver.HttpExchange

class CorsFilter extends Filter:
  override def doFilter(exchange: HttpExchange, chain: Chain): Unit =
    val headers = exchange.getResponseHeaders()
    headers.add("Content-Type", "application/json; charset=UTF-8")
    headers.add("Access-Control-Allow-Origin", "*")
    headers.add("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS")
    headers.add("Access-Control-Allow-Headers", "*")
    headers.add("Access-Control-Allow-Credentials", "true")
    headers.add("Access-Control-Allow-Credentials-Header", "*")

    chain.doFilter(exchange)

  override def description(): String = "Cors Filter"