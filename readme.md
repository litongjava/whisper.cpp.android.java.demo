# whisper.cpp.android.java.demo
[中文](readme_cn.md)|[English](readme.md)
A sample Android app using java code and [whisper.cpp library](https://github.com/ggerganov/whisper.cpp/) to do voice-to-text transcriptions.
## 1.Android integration with whisper.cpp library using Java code
### 1.1.directory structure
```
├── app
│   ├── .gitignore
│   ├── build.gradle
│   ├── libs
│   ├── proguard-rules.pro
│   ├── src
│   │   ├── androidTest
│   │   │   ├── java
│   │   │   │   ├── com
│   │   │   │   │   ├── litongjava
│   │   │   │   │   │   ├── whispercppandroidjavademo
│   │   │   │   │   │   │   ├── ExampleInstrumentedTest.java
│   │   ├── main
│   │   │   ├── AndroidManifest.xml
│   │   │   ├── assets
│   │   │   │   ├── logback.xml
│   │   │   │   ├── models
│   │   │   │   │   ├── ggml-tiny.bin
│   │   │   │   ├── samples
│   │   │   │   │   ├── .gitignore
│   │   │   │   │   ├── README.md
│   │   │   │   │   ├── jfk.wav
│   │   │   ├── java
│   │   │   │   ├── com
│   │   │   │   │   ├── litongjava
│   │   │   │   │   │   ├── whisper
│   │   │   │   │   │   │   ├── cpp
│   │   │   │   │   │   │   │   ├── android
│   │   │   │   │   │   │   │   │   ├── java
│   │   │   │   │   │   │   │   │   │   ├── bean
│   │   │   │   │   │   │   │   │   │   │   ├── WhisperSegment.java
│   │   │   │   │   │   │   │   │   │   ├── demo
│   │   │   │   │   │   │   │   │   │   │   ├── MainActivity.java
│   │   │   │   │   │   │   │   │   │   │   ├── app
│   │   │   │   │   │   │   │   │   │   │   │   ├── App.java
│   │   │   │   │   │   │   │   │   │   │   ├── ui
│   │   │   │   │   │   │   │   │   │   ├── media
│   │   │   │   │   │   │   │   │   │   │   ├── WaveEncoder.java
│   │   │   │   │   │   │   │   │   │   ├── recoder
│   │   │   │   │   │   │   │   │   │   │   ├── Recorder.java
│   │   │   │   │   │   │   │   │   │   ├── services
│   │   │   │   │   │   │   │   │   │   │   ├── WhisperService.java
│   │   │   │   │   │   │   │   │   │   ├── single
│   │   │   │   │   │   │   │   │   │   │   ├── LocalWhisper.java
│   │   │   │   │   │   │   │   │   │   ├── utils
│   │   │   │   │   │   │   │   │   │   │   ├── AssetUtils.java
│   │   │   │   │   ├── whispercppdemo
│   │   │   │   │   │   ├── whisper
│   │   │   │   │   │   │   ├── CpuInfo.java
│   │   │   │   │   │   │   ├── WhisperContext.java
│   │   │   │   │   │   │   ├── WhisperCpuConfig.java
│   │   │   │   │   │   │   ├── WhisperLib.java
│   │   │   │   │   │   │   ├── WhisperUtils.java
│   │   │   ├── jniLibs
│   │   │   │   ├── arm64-v8a
│   │   │   │   │   ├── libwhisper.so
│   │   │   │   │   ├── libwhisper_v8fp16_va.so
│   │   │   │   ├── armeabi-v7a
│   │   │   │   │   ├── libwhisper.so
│   │   │   │   │   ├── libwhisper_vfpv4.so
│   │   │   │   ├── jniLibs.zip
│   │   │   │   ├── x86
│   │   │   │   │   ├── libwhisper.so
│   │   │   │   ├── x86_64
│   │   │   │   │   ├── libwhisper.so
│   │   │   ├── res
│   │   │   │   ├── drawable
│   │   │   │   │   ├── ic_launcher_background.xml
│   │   │   │   ├── drawable-v24
│   │   │   │   │   ├── ic_launcher_foreground.xml
│   │   │   │   ├── layout
│   │   │   │   │   ├── activity_main.xml
│   │   │   │   ├── mipmap-anydpi-v26
│   │   │   │   │   ├── ic_launcher.xml
│   │   │   │   │   ├── ic_launcher_round.xml
│   │   │   │   ├── mipmap-hdpi
│   │   │   │   │   ├── ic_launcher.png
│   │   │   │   │   ├── ic_launcher_round.png
│   │   │   │   ├── mipmap-mdpi
│   │   │   │   │   ├── ic_launcher.png
│   │   │   │   │   ├── ic_launcher_round.png
│   │   │   │   ├── mipmap-xhdpi
│   │   │   │   │   ├── ic_launcher.png
│   │   │   │   │   ├── ic_launcher_round.png
│   │   │   │   ├── mipmap-xxhdpi
│   │   │   │   │   ├── ic_launcher.png
│   │   │   │   │   ├── ic_launcher_round.png
│   │   │   │   ├── mipmap-xxxhdpi
│   │   │   │   │   ├── ic_launcher.png
│   │   │   │   │   ├── ic_launcher_round.png
│   │   │   │   ├── values
│   │   │   │   │   ├── colors.xml
│   │   │   │   │   ├── strings.xml
│   │   │   │   │   ├── themes.xml
│   │   │   │   ├── values-night
│   │   │   │   │   ├── themes.xml
│   │   ├── test
│   │   │   ├── java
│   │   │   │   ├── com
│   │   │   │   │   ├── litongjava
│   │   │   │   │   │   ├── whispercppandroidjavademo
│   │   │   │   │   │   │   ├── ExampleUnitTest.java
├── build
│   ├── intermediates
│   │   ├── lint-cache
│   │   │   ├── maven.google
│   │   │   │   ├── androidx
│   │   │   │   │   ├── appcompat
│   │   │   │   │   │   ├── group-index.xml
│   │   │   │   │   ├── constraintlayout
│   │   │   │   │   │   ├── group-index.xml
│   │   │   │   │   ├── legacy
│   │   │   │   │   │   ├── group-index.xml
│   │   │   │   │   ├── test
│   │   │   │   │   │   ├── espresso
│   │   │   │   │   │   │   ├── group-index.xml
│   │   │   │   │   │   ├── ext
│   │   │   │   │   │   │   ├── group-index.xml
│   │   │   │   ├── com
│   │   │   │   │   ├── google
│   │   │   │   │   │   ├── android
│   │   │   │   │   │   │   ├── material
│   │   │   │   │   │   │   │   ├── group-index.xml
│   │   │   │   ├── master-index.xml
│   │   │   ├── sdk-registry.xml
│   │   │   │   ├── sdk-registry.xml
├── build.gradle
├── gradle
│   ├── wrapper
│   │   ├── gradle-wrapper.jar
│   │   ├── gradle-wrapper.properties
├── gradle.properties
├── gradlew
├── gradlew.bat
├── local.properties
├── readme.md
├── readme_cn.md
├── settings.gradle
```
## 1.2.Prepare the file
### 1.2.1.download AndroidWhisperCppLibrary

https://github.com/litongjava/whisper.cpp.android.java.demo/releases/tag/v1.0.0

1. `cmake\obj\arm64-v8a\libwhisper_v8fp16_va.so`: This is the bare, unoptimized or stripped .so file. It may contain debugging information and unused code snippets.

2. `AndroidWhisperCppLibrary-merged_native_libs.zip: This is the merged .so file, which may merge multiple native libraries, but may still contain debugging information.

3. `AndroidWhisperCppLibrary-stripped_native_libs.zip`: This file has been "stripped", meaning that it no longer contains debugging information, and is therefore smaller in size. Stripped .so files are often used to reduce the size of the APK when distributing an application.

For **debugging** purposes, you can use the first or second file. However, if you intend to **release** or use it in **other Android projects**, it is recommended that you choose the third stripped file as it will be smaller in size and will not contain unnecessary debugging information.

So, for use in other Android projects, choose the `AndroidWhisperCppLibrary-stripped_native_libs.zip` file. If you need to debug in other projects, then consider using the unstripped version.


### 1.2.2.View Java methods in so files
```
# nm libwhisper_v8fp16_va.so |grep Java
                 U AAssetManager_fromJava
0000000000156700 T Java_com_whispercppdemo_whisper_WhisperLib_benchGgmlMulMat
00000000001566b0 T Java_com_whispercppdemo_whisper_WhisperLib_benchMemcpy
0000000000156390 T Java_com_whispercppdemo_whisper_WhisperLib_freeContext
00000000001563c4 T Java_com_whispercppdemo_whisper_WhisperLib_fullTranscribe
0000000000156668 T Java_com_whispercppdemo_whisper_WhisperLib_getSystemInfo
0000000000156608 T Java_com_whispercppdemo_whisper_WhisperLib_getTextSegment
00000000001565d4 T Java_com_whispercppdemo_whisper_WhisperLib_getTextSegmentCount
0000000000156314 T Java_com_whispercppdemo_whisper_WhisperLib_initContext
000000000015617c T Java_com_whispercppdemo_whisper_WhisperLib_initContextFromAsset
0000000000156024 T Java_com_whispercppdemo_whisper_WhisperLib_initContextFromInputStream
```
### 1.2.3.model
https://github.com/litongjava/whisper.cpp.android.java.demo/releases/download/v1.0.0/ggml-tiny.bin

## 1.3.integration project
### 1.3.1.Adding libraries and models
To use:  
1. Download AndroidWhisperCppLibrary-stripped_native_libs.zip unzip it to app\src\main\jniLibs
2. From [whisper.cpp library](https://github.com/ggerganov/whisper.cpp/tree/master/models). [^1] Select a model.
3. Copy the model to the "app/src/main/assets/models" folder.
4. Select a sample audio file (e.g. [jfk.wav] (https://github.com/ggerganov/whisper.cpp/raw/master/samples/jfk.wav)).
5. Copy the sample to the "app/src/main/assets/samples" folder.
6. Modify the modelFilePath in WhisperService.java.
7. Modify the sampleFilePath in WhisperService.java 7.
8. Select the "release" active build variant, then run and deploy to the device using Android Studio.
[^1]: I recommend running `ggml-tiny.bin` on Android devices.

### 1.3.2.添加sourceSets
Modify app/build.gradle to add jniLibs to sourceSets
```
  sourceSets {
    main {
      jniLibs.srcDirs = ['src/main/jniLibs']
    }
  }
```
### 1.3.3.Adding Dependencies
```
//litongjava
implementation 'com.litongjava:android-view-inject:1.0'
implementation 'com.litongjava:jfinal-aop:1.0.1'
implementation 'com.litongjava:litongjava-android-utils:1.0.0'
```