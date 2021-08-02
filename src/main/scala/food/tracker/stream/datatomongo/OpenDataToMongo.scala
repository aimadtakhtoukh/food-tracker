package food.tracker.stream.datatomongo

import akka.actor.ActorSystem
import food.tracker.stream.datatomongo.flow.CsvFlow
import food.tracker.stream.datatomongo.sink.MongoFoodSink
import food.tracker.stream.datatomongo.source.NetSource
import org.json4s.native.Serialization
import org.json4s.native.Serialization.writePretty
import org.json4s.{Formats, NoTypeHints}

import scala.concurrent.ExecutionContextExecutor

// Quick batch to pull OpenData's CSV file and upload it in a mongo
object OpenDataToMongo extends App {
  implicit val formats: Formats = Serialization.formats(NoTypeHints)
  implicit val system: ActorSystem = ActorSystem("OpenFoodFactsToMongo")
  implicit val ec: ExecutionContextExecutor = system.dispatcher

  new NetSource().source
    .via(CsvFlow.flow)
    .map(writePretty(_))
    .runWith(MongoFoodSink.sink)
    .recover(_.printStackTrace())
    .andThen(_ => system.terminate())
}
