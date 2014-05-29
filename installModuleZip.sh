#!/bin/bash

if [ ! "$JBOSS_HOME" ]; then
  echo "Please set JBOSS_HOME"
  exit 1
fi

unzip -d "$JBOSS_HOME" valveModule.zip
