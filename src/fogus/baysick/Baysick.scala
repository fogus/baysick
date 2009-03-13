package fogus.baysick {
  import scala.collection.mutable.HashMap

  class Baysick {
    abstract sealed class BasicLine
    case class PrintLine(num: Int, s: String) extends BasicLine
    case class GotoLine(num: Int, to: Int) extends BasicLine
    val lines = new HashMap[Int, BasicLine]

    case class linebuilder(num: Int) {
      def GOTO(to: Int) = lines(num) = GotoLine(num, to)
      def PRINT(s: String) = lines(num) = PrintLine(num, s)
    }

    private def gotoLine(line: Int) {
      lines(line) match {
        case PrintLine(_, s) => println(s); gotoLine(line + 10)
        case GotoLine(_, to) => gotoLine(to)
      }
    }

    def RUN {
      gotoLine(lines.keys.toList.first)
    }

    implicit def int2linebuilder(i: Int) = linebuilder(i)
  }
}
