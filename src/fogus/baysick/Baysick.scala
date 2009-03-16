package fogus.baysick {
  import scala.collection.mutable.HashMap

  class Baysick {
    abstract sealed class BasicLine
    case class PrintString(num: Int, s: String) extends BasicLine
    case class PrintVariable(num: Int, s: Symbol) extends BasicLine
    case class PrintLine(num: Int, str: String, name: Symbol) extends BasicLine
    case class PrintNumber(num: Int, number: Int) extends BasicLine
    case class GotoLine(num: Int, to: Int) extends BasicLine
    case class InputLine(num: Int, name: Symbol) extends BasicLine
    case class EndLine(num: Int) extends BasicLine

    val lines = new HashMap[Int, BasicLine]
    val binds = new HashMap[Symbol, Any]

    case class linebuilder(num: Int) {
      def GOTO(to: Int) = lines(num) = GotoLine(num, to)
      def PRINT(s: String) = lines(num) = PrintString(num, s)
      def PRINT(number: BigInt) = lines(num) = PrintNumber(num, number)
      def PRINT(s: Symbol) = lines(num) = PrintVariable(num, s)
      def PRINT(str: String, name: Symbol) = lines(num) = PrintLine(num, str, name)
      def INPUT(name: Symbol) = lines(num) = InputLine(num, name)
      def END() = lines(num) = EndLine(num)
    }

    private def gotoLine(line: Int) {
      lines(line) match {
        case PrintLine(_, str: String, name: Symbol) => {
          val value = binds(name)
          println(str + value)
        }
        case PrintNumber(_, number:BigInt) => {
          println(number)
          gotoLine(line + 10)
        }
        case PrintString(_, s:String) => {
          println(s)
          gotoLine(line + 10)
        }
        case PrintVariable(_, s:Symbol) => {
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
