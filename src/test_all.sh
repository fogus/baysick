#!/usr/bin/env bash

# Copyright (c) 2010-2014 Michael Fogus, http://www.fogus.me
#
# Licensed under The MIT License
# Re-distributions of files must retain the above copyright notice.
#
#  #######        ##      ##    ##    ######     ####     ######     ##   ##
#  ##    ##      ####      ##  ##    ##           ##     ##    ##    ##  ##
#  #######      ##  ##       ##       ######      ##     ##          ####
#  ##    ##    ########      ##            ##     ##     ##    ##    ##  ##
#  #######    ##      ##     ##       ######     ####     ######     ##   ##

java -cp $SCALA_HOME/lib/scala-library.jar:baysick.jar HelloWorld
java -cp $SCALA_HOME/lib/scala-library.jar:baysick.jar HelloNumbers
java -cp $SCALA_HOME/lib/scala-library.jar:baysick.jar HelloName
java -cp $SCALA_HOME/lib/scala-library.jar:baysick.jar HelloPrint
java -cp $SCALA_HOME/lib/scala-library.jar:baysick.jar HelloLet
java -cp $SCALA_HOME/lib/scala-library.jar:baysick.jar HelloIf
java -cp $SCALA_HOME/lib/scala-library.jar:baysick.jar SquareRoot
