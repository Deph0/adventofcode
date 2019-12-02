#!/bin/sh

# build
kotlinc day1.kt -d day1.jar -include-runtime
# run
kotlin day1.jar
