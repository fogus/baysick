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

object HelloName extends Baysick {
  def main(args:Array[String]) = {
    10 PRINT "What is your name: "
    20 INPUT 'a
    30 PRINT ("Hello ", 'a)
    40 END

    RUN
  }
}
