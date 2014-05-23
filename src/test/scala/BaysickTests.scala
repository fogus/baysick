/**
 * Copyright (c) 2010-2014 Michael Fogus, http://www.fogus.me
 *
 * Licensed under The MIT License
 * Re-distributions of files must retain the above copyright notice.
 *
 * #######        ##      ##    ##    ######     ####     ######     ##   ##
 * ##    ##      ####      ##  ##    ##           ##     ##    ##    ##  ##
 * #######      ##  ##       ##       ######      ##     ##          ####
 * ##    ##    ########      ##            ##     ##     ##    ##    ##  ##
 * #######    ##      ##     ##       ######     ####     ######     ##   ##
 **/

import fogus.baysick.Baysick
import org.scalatest.FlatSpec

/**
 * Tests for Baysick.
 * TODO implement tests that actually check output and input.
 */
class BaysickTests extends FlatSpec {

  /**
   * TODO test input
   */
  ignore should "print the square root " in {
    object SquareRoot extends Baysick {
      def run() = {
        10 PRINT "Enter a number"
        20 INPUT 'n
        30 PRINT "Square root of " % "'n is " % SQRT('n)
        40 END

        RUN
      }
    }
    SquareRoot.run()
  }

  "Hello world" should "say hello to the world" in {
    object HelloWorld extends Baysick {
      def run() = {
        10 PRINT "\nHello Cleveland!"
        20 END

        RUN
      }
    }
    HelloWorld.run()
  }

  "HelloNumbers" should "print some numbers" in {
    object HelloNumbers extends Baysick {
      def run() = {
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
    HelloNumbers.run()
  }

  /**
   * TODO test input
   */
  ignore should "say hello" in {
    object HelloName extends Baysick {
      def run() = {
        10 PRINT "What is your name: "
        20 INPUT 'a
        30 PRINT "Hello " % 'a
        40 END

        RUN
      }
    }
    HelloName.run()
  }

  "HelloPrint" should "print hello" in {
    object HelloPrint extends Baysick {
      def run() = {
        10 LET ('a := "World")
        20 PRINT "Hello " % 'a
        30 PRINT 'a % " World"
        40 LET ('a := 42)
        50 PRINT "Hello " % 42
        60 PRINT "Hello " % 'a
        70 PRINT 42 % " World"
        80 PRINT 'a % " World"
        90 PRINT 'a % 'a
        100 PRINT 42 % 42 // doesn't work!  :(  calls modulo
        110 PRINT "Hello " % "World"
        120 END

        RUN
      }
    }
    HelloPrint.run()
  }

  "HelloLet" should "let hello" in {
    object HelloLet extends Baysick {
      def run() = {
        10 LET ('a := "Hello Let!") // weird, LET requires parens
        20 PRINT 'a
        30 LET ('a := 42)
        40 PRINT 'a
        50 END

        RUN
      }
    }
    HelloLet.run()
  }

  "HelloIf" should "do branching" in {
    object HelloIf extends Baysick {
      def run() = {
        10 LET ('a := 5)
        20 IF 'a === 5 THEN 40
        30 PRINT "This will never execute"
        40 PRINT "They were equal!"
        50 IF 'a === 6 THEN 70
        60 PRINT "This will execute 1st..."
        70 PRINT "...then this!"
        80 END

        RUN
      }
    }
    HelloIf.run()
  }

  /**
   * Lunar only works with Console I/O.
   * TODO have to figure testing that one out.
   */
  ignore should "fly to the moon" in {
    object Lunar extends Baysick {
      def run() = {
        10 PRINT "Welcome to Baysick Lunar Lander v0.0.1"
        20 LET ('dist := 100)
        30 LET ('v := 1)
        40 LET ('fuel := 1000)
        50 LET ('mass := 1000)

        60 PRINT "You are a in control of a lunar lander."
        70 PRINT "You are drifting towards the surface of the moon."
        80 PRINT "Each turn you must decide how much fuel to burn."
        90 PRINT "To accelerate enter a positive number, to decelerate a negative"

        100 PRINT "Distance " % 'dist % "km, " % "Velocity " % 'v % "km/s, " % "Fuel " % 'fuel
        110 INPUT 'burn
        120 IF ABS('burn) <= 'fuel THEN 150
        130 PRINT "You don't have that much fuel"
        140 GOTO 100
        150 LET ('v := 'v + 'burn * 10 / ('fuel + 'mass))
        160 LET ('fuel := 'fuel - ABS('burn))
        170 LET ('dist := 'dist - 'v)
        180 IF 'dist > 0 THEN 100
        190 PRINT "You have hit the surface"
        200 IF 'v < 3 THEN 240
        210 PRINT "Hit surface too fast (" % 'v % ")km/s"
        220 PRINT "You Crashed!"
        230 GOTO 250
        240 PRINT "Well done"

        250 END

        RUN
      }
    }
    Lunar.run()
  }
}
