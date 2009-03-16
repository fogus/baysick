package fogus.baysick {
  import scala.collection.mutable.HashMap

  class Baysick {
    abstract sealed class BasicLine
    case class PrintLine(num: Int, s: String) extends BasicLine
    case class GotoLine(num: Int, to: Int) extends BasicLine
    case class InputLine(num: Int, msg: String, name: Symbol) extends BasicLine
    case class EndLine(num: Int) extends BasicLine

    val lines = new HashMap[Int, BasicLine]
    val binds = new HashMap[Symbol, Any]

    case class linebuilder(num: Int) {
      def GOTO(to: Int) = lines(num) = GotoLine(num, to)
      def PRINT(s: String) = lines(num) = PrintLine(num, s)
      def INPUT(msg: String, name: Symbol) = lines(num) = InputLine(num, msg, name)
      def END() = lines(num) = EndLine(num)
    }

    private def gotoLine(line: Int) {
      lines(line) match {
        case PrintLine(_, s) => {
          println(s)
          gotoLine(line + 10)
        }
        case InputLine(_, msg, name) => {
          println("I will display" + msg)
          println("Then I will lookup " + name)
          gotoLine(line + 10)
        }
        case GotoLine(_, to) => gotoLine(to)
        case EndLine(_) => {
          println("Done.")
          System.exit(0)
        }
      }
    }

    def RUN {
      gotoLine(lines.keys.toList.first)
    }

    implicit def int2linebuilder(i: Int) = linebuilder(i)
  }
}
