import sbt._

class OptionParserProject(info: ProjectInfo) extends DefaultProject(info) {

  val specsDep = "org.scala-tools.testing" % "specs_2.8.1" % "1.6.7"
}
