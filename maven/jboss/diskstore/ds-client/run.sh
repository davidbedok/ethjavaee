#!/bin/sh

java -cp "./lib/log4j-1.2.17.jar:./lib/jboss-client.jar:./../ds-ejbserviceclient/target/ds-ejbserviceclient-1.0.jar:./target/ds-client-1.0.jar" com.ericsson.diskstore.client.DiskClient