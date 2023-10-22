# whisper.cpp.android.java.demo
[中文](readme_cn.md)|[English](readme.md)  
使用 Java 代码和 [whisper.cpp 库](https://github.com/ggerganov/whisper.cpp/) 进行语音到文本转录的示例 Android 应用程序。
## 1.Android整合whisper.cpp库使用Java代码
### 1.1.目录结构
```
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
## 1.2.准备文件
### 1.2.1.下载AndroidWhisperCppLibrary
https://github.com/litongjava/whisper.cpp.android.java.demo/releases/tag/v1.0.0

1. `cmake\obj\arm64-v8a\libwhisper_v8fp16_va.so`: 这是原始的、未经优化或剥离的 .so 文件。它可能包含调试信息和未使用的代码段。

2. `AndroidWhisperCppLibrary-merged_native_libs.zip: 这个文件是合并后的 .so 文件，它可能会合并多个原生库，但仍然可能包含调试信息。

3. `AndroidWhisperCppLibrary-stripped_native_libs.zip`: 这个文件已被“剥离”，意味着它不再包含调试信息，因此大小会更小。在发布应用时，为了减少 APK 大小，通常会使用剥离后的 .so 文件。

对于 **调试** 目的，您可以使用第一个或第二个文件。但如果您打算 **发布** 或在 **其他 Android 项目中使用**，建议您选择第三个剥离后的文件，因为它的大小会更小，并且不包含不必要的调试信息。

所以，为了在其他 Android 项目中使用，请选择 `AndroidWhisperCppLibrary-stripped_native_libs.zip` 文件。如果您需要在其他项目中进行调试，那么可以考虑使用未剥离的版本。


### 1.2.2.查看so文件中的Java方法
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

## 1.3.整合工程
### 1.3.1.添加库和模型
To use:  
1. 下载AndroidWhisperCppLibrary-stripped_native_libs.zip解压到app\src\main\jniLibs
2. 从 [whisper.cpp 资源库](https://github.com/ggerganov/whisper.cpp/tree/master/models)。[^1] 中选择一个模型。
3. 将模型复制到 "app/src/main/assets/models "文件夹。
4. 选择一个样本音频文件（例如 [jfk.wav](https://github.com/ggerganov/whisper.cpp/raw/master/samples/jfk.wav)）。
5. 将样本复制到 "app/src/main/assets/samples "文件夹。
6. 修改 WhisperService.java 中的 modelFilePath。
7. 修改 WhisperService.java 中的 sampleFilePath 7.
8. 选择 "release "活动构建变量，然后使用 Android Studio 运行并部署到设备上。
[^1]: 我推荐在 Android 设备上运行ggml-tiny.bin。

### 1.3.2.添加sourceSets
修改app/build.gradle添加jniLibs到sourceSets
```
  sourceSets {
    main {
      jniLibs.srcDirs = ['src/main/jniLibs']
    }
  }
```
### 1.3.3.添加依赖
```
//litongjava
implementation 'com.litongjava:android-view-inject:1.0'
implementation 'com.litongjava:jfinal-aop:1.0.1'
implementation 'com.litongjava:litongjava-android-utils:1.0.0'
```