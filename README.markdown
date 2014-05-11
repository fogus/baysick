## BAYSICK - an embedded Insane-specific Language for Scala implementing a subset of BASIC

### Here is a small example:

```scala
    object SquareRoot extends Baysick {
      def main(args:Array[String]) = {
        10 PRINT "Enter a number"
        20 INPUT 'n
        30 PRINT "Square root of " % "'n is " % SQRT('n)
        40 END
    
        RUN
      }
    }
```

### Here is a larger example

```scala
    object Lunar extends Baysick {
      def main(args:Array[String]) = {
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
```

Enjoy.

*note: [The BASIC programming language is 50 years old](https://www.dartmouth.edu/basicfifty/)*

## License

Copyright 2010-2014, [Michael Fogus](http://www.fogus.me)
Licensed under the MIT License.  
Redistributions of files must retain the above copyright notice.