#!/bin/sh

# build & run
kotlinc day2.kt -d day2.jar -include-runtime && \
kotlin day2.jar
