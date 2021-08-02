package food.tracker.stream.utilities

import akka.Done
import akka.stream.scaladsl.Sink

import scala.concurrent.Future

trait SinkProvider[T] {
  def sink: Sink[T, Future[Done]]
}
