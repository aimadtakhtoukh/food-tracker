package food.tracker.stream.datatomongo.source

import akka.NotUsed
import akka.stream.alpakka.file.scaladsl.FileTailSource
import akka.stream.scaladsl.Source
import akka.util.ByteString
import food.tracker.stream.utilities.SourceProvider

import java.nio.file.{FileSystem, FileSystems}
import scala.concurrent.duration.DurationInt

object FileSource extends SourceProvider[ByteString] {
  val fs: FileSystem = FileSystems.getDefault

  def source: Source[ByteString, NotUsed] = FileTailSource.lines(
    path = fs.getPath(
      "C:", "Users", "Dagger", "IdeaProjects",
      "food-tracker", "src", "main", "resources", "fr.openfoodfacts.org.products.csv"
    ),
    maxLineSize = 32768,
    pollingInterval = 250.millis,
    lf = "\n"
  )
    .map(line => ByteString(s"$line\n"))

}