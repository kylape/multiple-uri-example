#!/bin/bash

if [ ! "$JBOSS_HOME" ]; then
  echo "Please set JBOSS_HOME"
  exit 1
fi

mvn clean install

if [ $? -gt 0 ]; then
  echo
  echo ------------------------------------------
  echo Maven build failed.  Not installing module.
  echo ------------------------------------------
  echo
  exit 1
fi 

moduleDir="modules/system/layers/base/com/redhat/gss/valve/main"

mkdir -p $moduleDir

cp valve/target/multipleUriValve.jar $moduleDir
cp valve/src/main/resources/module.xml $moduleDir
zip -r valveModule.zip modules/ > /dev/null
rm -rf modules/

unzip -d "$JBOSS_HOME" valveModule.zip
$JBOSS_HOME/bin/jboss-cli.sh -c --command='/subsystem=web/valve=multipleUri:add(module="com.redhat.gss.valve",class-name="com.redhat.gss.valve.UriValve")'
