#!/bin/sh

# build & run
kotlinc day3.kt -d day3.jar -include-runtime && \
kotlin day3.jar
