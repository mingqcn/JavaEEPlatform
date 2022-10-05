#!/bin/bash --

java -Xms1g -Xmx1g -Xss256k \
     -Dcom.sun.management.jmxremote.port=3214 \
     -Dcom.sun.management.jmxremote.ssl=false \
     -Dcom.sun.management.jmxremote.authenticate=false \
     -jar $1
