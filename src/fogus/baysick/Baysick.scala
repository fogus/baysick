package fogus.baysick {
  import scala.collection.mutable.HashMap

  class Baysick {
    abstract sealed class BasicLine
    case class PrintString(num: Int, s: String) extends BasicLine
    case class PrintResult(num:Int, fn:Function0[String]) extends BasicLine
    case class PrintVariable(num: Int, s: Symbol) extends BasicLine
    case class PrintNumber(num: Int, number: BigInt) extends BasicLine
    case class Goto(num: Int, to: Int) extends BasicLine
    case class Input(num: Int, name: Symbol) extends BasicLine
    case class Let(num:Int, fn:Function0[Unit]) extends BasicLine
    case class If(num:Int, fn:Function0[Boolean], thenJmp:Int) extends BasicLine
    case class End(num: Int) extends BasicLine

    val lines = new HashMap[Int, BasicLine]
    val binds = new HashMap[Symbol, Any]

    def get(name:Symbol):Any = binds(name)
    def set(name:Symbol, value:Any) = binds(name) = value

    case class Assignr(sym:Symbol) {
      def :=(value:Any):Function0[Unit] = (() => set(sym, value))
    }

    case class Comparer(sym:Symbol) {
      def ===(rhs:BigInt):Function0[Boolean] = (() => get(sym) == rhs)
    }

    case class Jumpr(num:Int, fn:Function0[Boolean]) {
      def THEN(loc:Int) = lines(num) = If(num, fn, loc)
    }

    def stringify(x:Any*):String = x.mkString("")

    case class Appendr(lhs:Any) {
      /**
       * <code>appendage</code> refers to the LHS value to be appended, <b>at
       * runtime</b>.  This is done, by setting it to a function which performs
       * lookups (for symbols) and toString conversion.
       *
       */
      var appendage = lhs match {
        case sym:Symbol => (() => binds(sym).toString)
        case fn:Function0[Any] => fn
        case _ => (() => lhs.toString)
      }

      def %(rhs:Any):Function0[String] = {
        /**
         * Check the type of the RHS.  For symbols, do a lookup, then
         * concatenate it to the result of the appendage function.
         */
        (() => rhs match {
          case sym:Symbol => stringify(appendage(), binds(sym))
          case fn:Function0[Any] => stringify(appendage(), fn())
          case _ => stringify(appendage(), rhs)
        })
      }
    }

    def SQRT(i:BigInt):Function0[BigInt] = (() => Math.sqrt(i.intValue))
    def SQRT(s:Symbol):Function0[BigInt] = (() => Math.sqrt(binds(s).asInstanceOf[BigInt].intValue))

    def RUN() = gotoLine(lines.keys.toList.sort((l,r) => l < r).first)

    case class LineBuilder(num: Int) {
      def END() = lines(num) = End(num)

      object PRINT {
        def apply(str:String) = lines(num) = PrintString(num, str)
        def apply(number: BigInt) = lines(num) = PrintNumber(num, number)
        def apply(s: Symbol) = lines(num) = PrintVariable(num, s)
        def apply(fn:Function0[String]) = lines(num) = PrintResult(num, fn)
      }

      object INPUT {
        def apply(name: Symbol) = lines(num) = Input(num, name)
      }

      object LET {
        def apply(fn:Function0[Unit]) = lines(num) = Let(num, fn)
      }

      object GOTO {
        def apply(to: Int) = lines(num) = Goto(num, to)
      }

      object IF {
        def apply(fn:Function0[Boolean]) = Jumpr(num, fn)
      }
    }

    private def gotoLine(line: Int) {
      lines(line) match {
        case PrintNumber(_, number:BigInt) => {
          println(number)
          gotoLine(line + 10)
        }
        case PrintString(_, s:String) => {
          println(s)
          gotoLine(line + 10)
        }
        case PrintResult(_, fn:Function0[String]) => {
          println(fn())
          gotoLine(line + 10)
        }
        case PrintVariable(_, s:Symbol) => {
          val value = binds(s)
          println(value)
          gotoLine(line + 10)
        }
        case Input(_, name) => {
          var entry = readLine

          // Temporary hack
          try {
            binds(name) = BigInt(entry)
          }
          catch {
            case _ => binds(name) = entry
          }

          gotoLine(line + 10)
        }
        case Let(_, fn:Function0[Unit]) => {
          fn()
          gotoLine(line + 10)
        }
        case If(_, fn:Function0[Boolean], thenJmp:Int) => {
          if(fn()) {
            gotoLine(thenJmp)
          }
          else {
            gotoLine(line + 10)
          }
        }
        case Goto(_, to) => gotoLine(to)
        case End(_) => {
          println("BREAK IN LINE " + line)
        }
      }
    }

    implicit def int2LineBuilder(i: Int) = LineBuilder(i)
    implicit def toAppendr(key:Any) = Appendr(key)
    implicit def symbol2Assignr(sym:Symbol) = Assignr(sym)
    implicit def symbol2Comparer(sym:Symbol) = Comparer(sym)
  }
}
