import $ivy.`com.github.pathikrit::better-files:3.6.0`
import better.files._
import better.files.Dsl._
import better.files.Dsl.SymbolicOperations
val table = file"/Users/odd/Code/cloc/src/main/resources/io/github/adrianulbona/cloc/data/table.txt"
val index = file"/Users/odd/Code/cloc/src/main/resources/io/github/adrianulbona/cloc/data/countries.index"
val out = file"/Users/odd/Code/cloc/src/main/resources/io/github/adrianulbona/cloc/data/countries2.index"
val Line = """^([^\t]+)\t+([^\t]+)\t+([^\t]+)\t+([^\t]+)$""".r
val IndexLine = """^([^=]+)=([^\n]+)$""".r
val byName = table.lines.map { case l @ Line(name, iso2, iso3, id) => (name, (id.toInt, name, iso2, iso3)) }.toMap
out.printLines(index.lines.collect {
  case l @ IndexLine(id, names) => (id, names.split(",").toList)
}.map {
  case (id, names) => (id, names.map(byName.applyOrElse(_, (name: String) => (-1, name, "--", "---"))))
}.map {
  case (id, codes) => s"$id=${codes.map(t => t._2 + ";" + t._3 + ";" + t._4 + ";" + t._1).mkString("", ",", "")}"
})