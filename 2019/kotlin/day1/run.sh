#!/bin/sh

# build & run
kotlinc day1.kt -d day1.jar -include-runtime && \
kotlin day1.jar
