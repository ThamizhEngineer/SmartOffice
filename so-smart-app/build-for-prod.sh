#!/bin/sh

npm install 
ionic build
npx cap copy
ionic capacitor sync android
cd android/
[ -f local.properties ] && echo "local.properties already exists." || echo "sdk.dir=/home/dinesh/Android/Sdk" >> local.properties
./gradlew assembleDebug
cp ./app/build/outputs/apk/debug/app-debug.apk ~/Downloads/builds/pothigai-app.apk