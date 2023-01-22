package objektwerks

import com.typesafe.scalalogging.LazyLogging

import java.net.URI
import java.net.http.{HttpClient, HttpRequest, HttpResponse}
import java.net.http.HttpResponse.BodyHandlers
import java.time.Duration
import java.time.temporal.ChronoUnit.SECONDS
import java.util.concurrent.Executors

import scala.concurrent.{blocking, Await, ExecutionContext, Future}
import scala.concurrent.duration.*
import scala.jdk.FutureConverters.*

object Client extends LazyLogging:
  implicit private val executionContext: ExecutionContext = ExecutionContext.fromExecutor( Executors.newVirtualThreadPerTaskExecutor() )

  private val client = HttpClient
                         .newBuilder()
                         .executor( Executors.newVirtualThreadPerTaskExecutor() )
                         .build()

  private def sendBlockingHttpRequest(httpRequest: HttpRequest): HttpResponse[String] =
    val future = Future { // Useful for working OFF of a Swing or JavaFx UI thread!
      blocking {
        client.send( httpRequest, BodyHandlers.ofString )
      }
    }
    Await.result(future, 30.seconds)

  private def sendAsyncHttpRequest(httpRequest: HttpRequest): Future[HttpResponse[String]] =
    client.sendAsync( httpRequest, BodyHandlers.ofString ).asScala

  def get(url: String): String =
    logger.info(s"*** get url: $url")

    val httpRequest = HttpRequest
      .newBuilder
      .uri(URI(url))
      .timeout(Duration.of(30, SECONDS))
      .version(HttpClient.Version.HTTP_2)
      .GET()
      .build
    
    val httpResponse = sendBlockingHttpRequest(httpRequest)
    val response = httpResponse.body

    logger.info(s"*** get http response: $response")
    response

  def post(url: String,
           requestJson: String): Future[String] =
    logger.info(s"*** post url: $url")
    logger.info(s"*** post request json: $requestJson")

    val httpRequest = HttpRequest
      .newBuilder
      .uri(URI(url))
      .timeout(Duration.of(30, SECONDS))
      .version(HttpClient.Version.HTTP_2)
      .headers("Content-Type", "application/json; charset=UTF-8", "Accept", "application/json")
      .POST( HttpRequest.BodyPublishers.ofString(requestJson) )
      .build
    
    // See: https://github.com/objektwerks/pool.balance/blob/main/client/src/main/scala/pool/Fetcher.scala
    // for how to pass in a higher order function and just return Unit.
    sendAsyncHttpRequest(httpRequest).map { httpResponse =>
      val responseJson = httpResponse.body
      logger.info(s"*** post response json: $responseJson")
      responseJson
    }.recover { case error: Exception => error.getMessage }