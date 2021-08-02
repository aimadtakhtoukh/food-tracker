package food.tracker.stream.datatomongo.flow

import akka.NotUsed
import akka.stream.alpakka.csv.scaladsl.{CsvParsing, CsvToMap}
import akka.stream.scaladsl.Flow
import akka.util.ByteString
import food.tracker.stream.utilities.FlowProvider

object CsvFlow extends FlowProvider[ByteString, Map[String, String]] {
  override def flow: Flow[ByteString, Map[String, String], NotUsed] =
    Flow[ByteString]
      .via(CsvParsing.lineScanner(delimiter = CsvParsing.Tab, maximumLineLength = 20480))
      .via(CsvToMap.toMap())
      .map(_.view.map {case key -> value => key.stripMargin('-') -> value.utf8String }.toMap)
}
