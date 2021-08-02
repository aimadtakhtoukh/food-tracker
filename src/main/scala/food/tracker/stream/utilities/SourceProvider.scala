package food.tracker.stream.utilities

import akka.stream.scaladsl.Source

trait SourceProvider[T] {
  def source: Source[T, Any]
}
