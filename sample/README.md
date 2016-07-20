# Fastlane Android Sample

This project is a small example written in Kotlin with a MVP implementation with Rx and DI. It's only purpose is to demonstrate the functionality of fastlane for Android.

If you want to run fastlane for this project there is a few things you have to do.

1. [Install fastlane](https://github.com/fastlane/fastlane#installation)
2. Generate a certificate `keytool -genkey -v -keystore android.jks -alias android-fastlane-example -keyalg RSA -keysize 2048 -validity 10000`
3. Place the cert if `$HOME/keyvault`
4. Clone the repo
5. Run fastlane with `fastlane alpha` or `fastlane alpha storepass:'XXXX'`
