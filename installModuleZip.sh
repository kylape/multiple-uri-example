#!/bin/bash

if [ ! "$JBOSS_HOME" ]; then
  echo "Please set JBOSS_HOME"
  exit 1
fi

unzip -d "$JBOSS_HOME" valveModule.zip
$JBOSS_HOME/bin/jboss-cli.sh -c --command='/subsystem=web/valve=multipleUri:add(module="com.redhat.gss.valve",class-name="com.redhat.gss.valve.UriValve")'
