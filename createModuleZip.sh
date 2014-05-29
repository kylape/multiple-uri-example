#!/bin/bash

moduleDir="modules/system/layers/base/com/redhat/gss/valve/main"

mkdir -p $moduleDir

cp valve/target/multipleUriValve.jar $moduleDir
cp valve/src/main/resources/module.xml $moduleDir
zip -r valveModule.zip modules/ > /dev/null
rm -rf modules/
