package food.tracker.stream.utilities

import akka.NotUsed
import akka.stream.scaladsl.Flow

trait FlowProvider[T, U] {
  def flow: Flow[T, U, NotUsed]
}
