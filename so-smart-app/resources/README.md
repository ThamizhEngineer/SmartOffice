# Notes for SmartOffice Mobile App
## For new machine 

- sudo apt-get install nodejs 

- sudo apt-get install npm 

- sudo npm install -g @angular/cli 

- sudo npm install -g @ionic/cli 

## Setup sdk  

- Ref - https://proandroiddev.com/how-to-setup-android-sdk-without-android-studio-6d60d0f2812a  

- Download Command line tools from - https://developer.android.com/studio#downloads 

 

## Create a new app 

- ionic start 

## Run App 

- ionic serve 

## Build app and Generate Apk

- cd so-smart-app (app_name) 

- npm install 

- ionic build 

- npx cap copy 

- ionic capacitor sync android 

- cd android/ 

- [ -f local.properties ] && echo "local.properties already exists." ||  echo "sdk.dir=/home/dinesh/Android/Sdk" >> local.properties 
- ./gradlew assembleDebug 

- cp ./app/build/outputs/apk/debug/app-debug.apk ~/Downloads/builds/ 

## Issue #1 - If you get error related to GPS_REQUIRED, run following from android folder 

sed -i 's/$GPS_REQUIRED/false/g' ./capacitor-cordova-android-plugins/src/main/AndroidManifest.xml 

## Issue #2 - 
/media/data/scrunchadmin/work/code/ps-dev/so-smart-app/android/capacitor-cordova-android-plugins/src/main/AndroidManifest.xml:15:60-92 Error:  Attribute uses-feature#android.hardware.location.gps@required at AndroidManifest.xml:15:60-92 has an illegal value=($GPS_REQUIRED), expected 'true' or 'false' 
/media/data/scrunchadmin/work/code/ps-dev/so-smart-app/android/capacitor-cordova-android-plugins/src/main/AndroidManifest.xml Error: 
        Validation failed, exiting 
- Fix -  
- vi /media/data/scrunchadmin/work/code/ps-dev/so-smart-app/android/capacitor-cordova-android-plugins/src/main/AndroidManifest.xml 
- Replace $gps_required with true 

 