package objektwerks

import com.typesafe.scalalogging.LazyLogging

import java.net.URI
import java.net.http.{HttpClient, HttpRequest}
import java.net.http.HttpResponse.BodyHandlers
import java.time.Duration
import java.time.temporal.ChronoUnit.SECONDS

object Client extends LazyLogging:
  def call(url: String,
           requestJson: String): String =
    logger.info(s"url: $url")
    logger.info(s"request json: $requestJson")

    val request = HttpRequest
      .newBuilder
      .uri(URI(url))
      .timeout(Duration.of(10, SECONDS))
      .version(HttpClient.Version.HTTP_2)
      .headers("Content-Type", "application/json; charset=UTF-8", "Accept", "application/json")
      .POST( HttpRequest.BodyPublishers.ofString(requestJson) )
      .build
    
    val client = HttpClient.newHttpClient
    val responseJson = client.send( request, BodyHandlers.ofString ).body
    
    logger.info(s"response json: $requestJson")
    responseJson