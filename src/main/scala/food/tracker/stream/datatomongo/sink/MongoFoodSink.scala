package food.tracker.stream.datatomongo.sink
import akka.Done
import akka.stream.alpakka.mongodb.scaladsl.MongoSink
import akka.stream.scaladsl.{Flow, Keep, Sink, Source}
import com.mongodb.reactivestreams.client.{MongoClients, MongoCollection, MongoDatabase}
import com.typesafe.config.{Config, ConfigFactory}
import food.tracker.stream.utilities.SinkProvider
import org.bson.codecs.configuration.CodecRegistries.fromRegistries
import org.mongodb.scala.Document
import org.mongodb.scala.MongoClient.DEFAULT_CODEC_REGISTRY

import scala.concurrent.Future

object MongoFoodSink extends SinkProvider[String] {
  private val config: Config = ConfigFactory.load()
  private val codecRegistry = fromRegistries(DEFAULT_CODEC_REGISTRY)
  private val client = MongoClients.create(config.getString("mongo.url"))
  val foodDb: MongoDatabase = client.getDatabase("heroes-feast")

  def foodCollection: MongoCollection[Document] = foodDb.getCollection("food", classOf[Document]).withCodecRegistry(codecRegistry)

  def convertToDocument(json : String) = Document(json)

  override def sink: Sink[String, Future[Done]] =
    Flow
      .fromFunction(convertToDocument)
      .toMat(MongoSink.insertOne(foodCollection))(Keep.right)
}
