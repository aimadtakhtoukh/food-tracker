package food.tracker.stream.datatomongo.source

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.client.RequestBuilding.Get
import akka.http.scaladsl.model.HttpRequest
import akka.stream.scaladsl.Source
import akka.util.ByteString
import food.tracker.stream.utilities.SourceProvider

import scala.concurrent.{ExecutionContext, Future}

class NetSource(implicit system: ActorSystem, executionContext: ExecutionContext) extends SourceProvider[ByteString] {
  val request: HttpRequest = Get("https://static.openfoodfacts.org/data/fr.openfoodfacts.org.products.csv")

  override def source: Source[ByteString, Future[Any]] =
    Source.futureSource(
      Http().singleRequest(request)
        .map(_.entity.dataBytes)
    )
}
