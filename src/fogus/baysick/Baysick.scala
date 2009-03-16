package fogus.baysick {
  import scala.collection.mutable.HashMap

  class Baysick {
    abstract sealed class BasicLine
    case class PrintLine(num: Int, s: Any) extends BasicLine
    case class GotoLine(num: Int, to: Int) extends BasicLine
    case class InputLine(num: Int, name: Symbol) extends BasicLine
    case class EndLine(num: Int) extends BasicLine

    val lines = new HashMap[Int, BasicLine]
    val binds = new HashMap[Symbol, Any]

    case class linebuilder(num: Int) {
      def GOTO(to: Int) = lines(num) = GotoLine(num, to)
      def PRINT(s: Any) = lines(num) = PrintLine(num, s)
      def INPUT(name: Symbol) = lines(num) = InputLine(num, name)
      def END() = lines(num) = EndLine(num)
    }

    private def gotoLine(line: Int) {
      lines(line) match {
        case PrintLine(_, s:String) => {
          println(s)
          gotoLine(line + 10)
        }
        case PrintLine(_, s:Symbol) => {
          val value = binds(s)
          println(value)
          gotoLine(line + 10)
        }
        case InputLine(_, name) => {
          val entry = readLine
          binds(name) = entry
          gotoLine(line + 10)
        }
        case GotoLine(_, to) => gotoLine(to)
        case EndLine(_) => {
          println("-- Done at line " + line)
        }
      }
    }

    def RUN {
      gotoLine(lines.keys.toList.sort((l,r) => l < r).first)
    }

    implicit def int2linebuilder(i: Int) = linebuilder(i)
  }
}
