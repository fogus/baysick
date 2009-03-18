import fogus.baysick.Baysick

/**
 * Tests the ability to run everyone's first BASIC program; the endless loop
 * proclaiming self-virtue.
 *
 * Run as:
 *
 * <code>
 * java -cp $SCALA_HOME/lib/scala-library.jar:baysick.jar EndlessLoop
 * </code>
 *
 **/
object EndlessLoop extends Baysick {
  def main(args:Array[String]) = {
    10 PRINT "Fogus Rulez!"
    20 GOTO 10
    30 END

    RUN
  }
}

object HelloWorld extends Baysick {
  def main(args:Array[String]) = {
    10 PRINT "Hello Cleveland!"
    20 END

    RUN
  }
}

object HelloNumbers extends Baysick {
  def main(args:Array[String]) = {
    10 PRINT "Here are some numbers:"
    20 PRINT 42
    30 PRINT 102845646545646546L
    40 PRINT "... and some math"
    50 PRINT "2 + 2"
    60 PRINT 2 + 2
    70 PRINT "102845646545646546 * 2"
    80 PRINT 102845646545646546L * 2
    90 PRINT "5 - 100"
    100 PRINT 5 - 100
    110 PRINT "3/2"
    120 PRINT 3 / 2
    130 END

    RUN
  }
}

object HelloName extends Baysick {
  def main(args:Array[String]) = {
    10 PRINT "What is your name: "
    20 INPUT 'a
    30 PRINT "Hello " % 'a
    40 END

    RUN
  }
}

object HelloPrint extends Baysick {
  def main(args:Array[String]) = {
    10 LET ('a := "World")
    20 PRINT "Hello " % 'a
    30 PRINT 'a % " World"
    40 LET ('a := 42)
    50 PRINT "Hello " % 42
    60 PRINT "Hello " % 'a
    70 PRINT 42 % " World"
    80 PRINT 'a % " World"
    90 PRINT 'a % 'a
    100 PRINT 42 % 42  // doesn't work!  :(  calls modulo
    110 PRINT "Hello " % "World"
    120 END

    RUN
  }
}

object HelloLet extends Baysick {
  def main(args:Array[String]) = {
    10 LET ('a := "Hello Let!")
    20 PRINT 'a
    30 LET ('a := 42)
    40 PRINT 'a
    50 END

    RUN
  }
}

object SquareRoot extends Baysick {
  def main(args:Array[String]) = {
    10 PRINT "Enter a number"
    20 INPUT 'n
    30 PRINT "Square root of " % 'n % " is " % Math.sqrt('n)
    40 END

    RUN
  }
}
