# whisper.cpp.android.java.demo
[中文](readme_cn.md)|[English](readme.md)  
使用 Java 代码和 [whisper.cpp 库](https://github.com/ggerganov/whisper.cpp/) 进行语音到文本转录的示例 Android 应用程序。
## 1.Android整合whisper.cpp库使用Java代码
### 1.1.目录结构
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
│   │   │   │   │   │   │   │   │   │   ├── demo
│   │   │   │   │   │   │   │   │   │   │   ├── MainActivity.java
│   │   │   │   │   │   │   │   │   │   │   ├── ui
│   │   │   │   │   │   │   │   │   │   ├── media
│   │   │   │   │   │   │   │   │   │   │   ├── WaveEncoder.java
│   │   │   │   │   │   │   │   │   │   ├── recoder
│   │   │   │   │   │   │   │   │   │   │   ├── Recorder.java
│   │   │   │   │   │   │   │   │   │   ├── services
│   │   │   │   │   │   │   │   │   │   │   ├── WhisperService.java
│   │   │   │   │   │   │   │   │   │   ├── utils
│   │   │   │   │   │   │   │   │   │   │   ├── AssetUtils.java
│   │   │   │   │   ├── whispercppdemo
│   │   │   │   │   │   ├── whisper
│   │   │   │   │   │   │   ├── CpuInfo.java
│   │   │   │   │   │   │   ├── Utils.java
│   │   │   │   │   │   │   ├── WhisperContext.java
│   │   │   │   │   │   │   ├── WhisperCpuConfig.java
│   │   │   │   │   │   │   ├── WhisperLib.java
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
├── readmd.md
├── readmd_files
│   ├── 1.jpg
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

所以，为了在其他 Android 项目中使用，请选择 `app\build\intermediates\stripped_native_libs\debug\out\lib\arm64-v8a\libwhisper_v8fp16_va.so` 文件。如果您需要在其他项目中进行调试，那么可以考虑使用未剥离的版本。


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
### 1.3.4.Whisper Cpp Java代码
#### 1.3.4.1.CpuInfo
```
package com.whispercppdemo.whisper;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CpuInfo {
  private static final String LOG_TAG = "WhisperCpuConfig";

  private List<String> lines;

  public CpuInfo(List<String> lines) {
    this.lines = lines;
  }

  @RequiresApi(api = Build.VERSION_CODES.N)
  public int getHighPerfCpuCount0() {
    try {
      return getHighPerfCpuCountByFrequencies();
    } catch (Exception e) {
      Log.d(LOG_TAG, "Couldn't read CPU frequencies", e);
      return getHighPerfCpuCountByVariant();
    }
  }

  @RequiresApi(api = Build.VERSION_CODES.N)
  private int getHighPerfCpuCountByFrequencies() {
    List<Integer> frequencies = getCpuValues("processor", line -> {
        try {
          return getMaxCpuFrequency(Integer.parseInt(line.trim()));
        } catch (IOException e) {
          e.printStackTrace();
        }
        return 0;
      }
    );
    Log.d(LOG_TAG, "Binned cpu frequencies (frequency, count): " + binnedValues(frequencies));
    return countDroppingMin(frequencies);
  }

  @RequiresApi(api = Build.VERSION_CODES.N)
  private int getHighPerfCpuCountByVariant() {
    List<Integer> variants = getCpuValues("CPU variant", line -> Integer.parseInt(line.trim().substring(line.indexOf("0x") + 2), 16));
    Log.d(LOG_TAG, "Binned cpu variants (variant, count): " + binnedValues(variants));
    return countKeepingMin(variants);
  }

  @RequiresApi(api = Build.VERSION_CODES.N)
  private Map<Integer, Integer> binnedValues(List<Integer> values) {
    Map<Integer, Integer> countMap = new HashMap<>();
    for (int value : values) {
      countMap.put(value, countMap.getOrDefault(value, 0) + 1);
    }
    return countMap;
  }

  @RequiresApi(api = Build.VERSION_CODES.N)
  private List<Integer> getCpuValues(String property, Mapper mapper) {
    List<Integer> values = new ArrayList<>();
    for (String line : lines) {
      if (line.startsWith(property)) {
        values.add(mapper.map(line.substring(line.indexOf(':') + 1)));
      }
    }
    values.sort(Integer::compareTo);
    return values;
  }

  @RequiresApi(api = Build.VERSION_CODES.N)
  private int countDroppingMin(List<Integer> values) {
    int min = values.stream().mapToInt(i -> i).min().orElse(Integer.MAX_VALUE);
    return (int) values.stream().filter(value -> value > min).count();
  }

  @RequiresApi(api = Build.VERSION_CODES.N)
  private int countKeepingMin(List<Integer> values) {
    int min = values.stream().mapToInt(i -> i).min().orElse(Integer.MAX_VALUE);
    return (int) values.stream().filter(value -> value.equals(min)).count();
  }

  @RequiresApi(api = Build.VERSION_CODES.N)
  public static int getHighPerfCpuCount() {
    try {
      return readCpuInfo().getHighPerfCpuCount0();
    } catch (Exception e) {
      Log.d(LOG_TAG, "Couldn't read CPU info", e);
      return Math.max(Runtime.getRuntime().availableProcessors() - 4, 0);
    }
  }

  private static CpuInfo readCpuInfo() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader("/proc/cpuinfo"))) {
      List<String> lines = new ArrayList<>();
      String line;
      while ((line = reader.readLine()) != null) {
        lines.add(line);
      }
      return new CpuInfo(lines);
    }
  }

  private static int getMaxCpuFrequency(int cpuIndex) throws IOException {
    String path = "/sys/devices/system/cpu/cpu" + cpuIndex + "/cpufreq/cpuinfo_max_freq";
    try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
      return Integer.parseInt(reader.readLine());
    }
  }

  private interface Mapper {
    int map(String line);
  }
}
```
#### 1.3.4.2.Utils
```
package com.whispercppdemo.whisper;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.nio.file.Path;

public class Utils {
  private static final String LOG_TAG = "LibWhisper";


  public static boolean isArmEabiV7a() {
    return Build.SUPPORTED_ABIS[0].equals("armeabi-v7a");
  }

  public static boolean isArmEabiV8a() {
    return Build.SUPPORTED_ABIS[0].equals("arm64-v8a");
  }

  @RequiresApi(api = Build.VERSION_CODES.O)
  public static String cpuInfo() {
    try {
      Path path = new File("/proc/cpuinfo").toPath();
      return new String(java.nio.file.Files.readAllBytes(path));
    } catch (Exception e) {
      Log.w(LOG_TAG, "Couldn't read /proc/cpuinfo", e);
      return null;
    }

  }
}
#### 1.3.4.3.WhisperContext
package com.whispercppdemo.whisper;

import android.content.res.AssetManager;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.InputStream;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WhisperContext {

  private static final String LOG_TAG = "LibWhisper";
  private long ptr;
  private final ExecutorService executorService;

  private WhisperContext(long ptr) {
    this.ptr = ptr;
    this.executorService = Executors.newSingleThreadExecutor();
  }

  public String transcribeData(float[] data) throws ExecutionException, InterruptedException {
    return executorService.submit(new Callable<String>() {
      @RequiresApi(api = Build.VERSION_CODES.O)
      @Override
      public String call() throws Exception {
        if (ptr == 0L) {
          throw new IllegalStateException();
        }
        int numThreads = WhisperCpuConfig.getPreferredThreadCount();
        Log.d(LOG_TAG, "Selecting " + numThreads + " threads");
        WhisperLib.fullTranscribe(ptr, numThreads, data);
        int textCount = WhisperLib.getTextSegmentCount(ptr);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < textCount; i++) {
          result.append(WhisperLib.getTextSegment(ptr, i));
        }
        return result.toString();
      }
    }).get();
  }

  @RequiresApi(api = Build.VERSION_CODES.O)
  public String benchMemory(int nthreads) throws ExecutionException, InterruptedException {
    return executorService.submit(() -> WhisperLib.benchMemcpy(nthreads)).get();
  }

  @RequiresApi(api = Build.VERSION_CODES.O)
  public String benchGgmlMulMat(int nthreads) throws ExecutionException, InterruptedException {
    return executorService.submit(() -> WhisperLib.benchGgmlMulMat(nthreads)).get();
  }

  @RequiresApi(api = Build.VERSION_CODES.O)
  public void release() throws ExecutionException, InterruptedException {
    executorService.submit(() -> {
      if (ptr != 0L) {
        WhisperLib.freeContext(ptr);
        ptr = 0;
      }
    }).get();
  }

  @RequiresApi(api = Build.VERSION_CODES.O)
  public static WhisperContext createContextFromFile(String filePath) {
    long ptr = WhisperLib.initContext(filePath);
    if (ptr == 0L) {
      throw new RuntimeException("Couldn't create context with path " + filePath);
    }
    return new WhisperContext(ptr);
  }

  @RequiresApi(api = Build.VERSION_CODES.O)
  public static WhisperContext createContextFromInputStream(InputStream stream) {
    long ptr = WhisperLib.initContextFromInputStream(stream);
    if (ptr == 0L) {
      throw new RuntimeException("Couldn't create context from input stream");
    }
    return new WhisperContext(ptr);
  }

  @RequiresApi(api = Build.VERSION_CODES.O)
  public static WhisperContext createContextFromAsset(AssetManager assetManager, String assetPath) {
    long ptr = WhisperLib.initContextFromAsset(assetManager, assetPath);
    if (ptr == 0L) {
      throw new RuntimeException("Couldn't create context from asset " + assetPath);
    }
    return new WhisperContext(ptr);
  }

  @RequiresApi(api = Build.VERSION_CODES.O)
  public static String getSystemInfo() {
    return WhisperLib.getSystemInfo();
  }
}
```
#### 1.3.4.4.WhisperCpuConfig
```
package com.whispercppdemo.whisper;

import android.os.Build;

import androidx.annotation.RequiresApi;

public class WhisperCpuConfig {
  @RequiresApi(api = Build.VERSION_CODES.N)
  public static int getPreferredThreadCount() {
    return Math.max(CpuInfo.getHighPerfCpuCount(), 2);
  }
}
```
#### 1.3.4.5.WhisperLib
```
package com.whispercppdemo.whisper;

import android.content.res.AssetManager;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

@RequiresApi(api = Build.VERSION_CODES.O)
public class WhisperLib {
  private static final String LOG_TAG = "LibWhisper";
  private static Logger log = LoggerFactory.getLogger(WhisperLib.class);

  static {

    Log.d(LOG_TAG, "Primary ABI: " + Build.SUPPORTED_ABIS[0]);
    log.info("Primary ABI: " + Build.SUPPORTED_ABIS[0]);
    boolean loadVfpv4 = false;
    boolean loadV8fp16 = false;
    if (Utils.isArmEabiV7a()) {
      String cpuInfo = Utils.cpuInfo();
      if (cpuInfo != null) {
        Log.d(LOG_TAG, "CPU info: " + cpuInfo);
        log.info("CPU info: " + cpuInfo);
        if (cpuInfo.contains("vfpv4")) {
          Log.d(LOG_TAG, "CPU supports vfpv4");
          log.info("CPU supports vfpv4");
          loadVfpv4 = true;
        }
      }
    } else if (Utils.isArmEabiV8a()) {
      String cpuInfo = Utils.cpuInfo();
      if (cpuInfo != null) {
        Log.d(LOG_TAG, "CPU info: " + cpuInfo);
        log.info("CPU info: " + cpuInfo);
        if (cpuInfo.contains("fphp")) {
          Log.d(LOG_TAG, "CPU supports fp16 arithmetic");
          log.info("CPU supports fp16 arithmetic");
          loadV8fp16 = true;
        }
      }
    }

    if (loadVfpv4) {
      Log.d(LOG_TAG, "Loading libwhisper_vfpv4.so");
      log.info("Loading libwhisper_vfpv4.so");
      System.loadLibrary("whisper_vfpv4");
    } else if (loadV8fp16) {
      Log.d(LOG_TAG, "Loading libwhisper_v8fp16_va.so");
      log.info("Loading libwhisper_v8fp16_va.so");
      System.loadLibrary("whisper_v8fp16_va");
    } else {
      Log.d(LOG_TAG, "Loading libwhisper.so");
      log.info("Loading libwhisper.so");
      System.loadLibrary("whisper");
    }
  }

  public static native long initContextFromInputStream(InputStream inputStream);

  public static native long initContextFromAsset(AssetManager assetManager, String assetPath);

  public static native long initContext(String modelPath);

  public static native void freeContext(long contextPtr);

  public static native void fullTranscribe(long contextPtr, int numThreads, float[] audioData);

  public static native int getTextSegmentCount(long contextPtr);

  public static native String getTextSegment(long contextPtr, int index);

  public static native String getSystemInfo();

  public static native String benchMemcpy(int nthread);

  public static native String benchGgmlMulMat(int nthread);
}
```
### 1.3.5.添加工具类
#### 1.3.5.1.AssetUtils
```
package com.litongjava.whisper.cpp.android.java.utils;

import android.content.Context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class AssetUtils {
  private static Logger log = LoggerFactory.getLogger(AssetUtils.class);

  public static File copyFileIfNotExists(Context context, File distDir, String filename) {
    File dstFile = new File(distDir, filename);
    if (dstFile.exists()) {
      return dstFile;
    } else {
      File parentFile = dstFile.getParentFile();
      log.info("parentFile:{}", parentFile);
      if (!parentFile.exists()) {
        parentFile.mkdirs();
      }
      AssetUtils.copyFileFromAssets(context, filename, dstFile);
    }
    return dstFile;
  }

  public static void copyDirectoryFromAssets(Context appCtx, String srcDir, String dstDir) {
    if (srcDir.isEmpty() || dstDir.isEmpty()) {
      return;
    }
    try {
      if (!new File(dstDir).exists()) {
        new File(dstDir).mkdirs();
      }
      for (String fileName : appCtx.getAssets().list(srcDir)) {
        String srcSubPath = srcDir + File.separator + fileName;
        String dstSubPath = dstDir + File.separator + fileName;
        if (new File(srcSubPath).isDirectory()) {
          copyDirectoryFromAssets(appCtx, srcSubPath, dstSubPath);
        } else {
          copyFileFromAssets(appCtx, srcSubPath, dstSubPath);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void copyFileFromAssets(Context appCtx, String srcPath, String dstPath) {
    File dstFile = new File(dstPath);
    copyFileFromAssets(appCtx, srcPath, dstFile);
  }

  public static void copyFileFromAssets(Context appCtx, String srcPath, File dstFile) {
    if (srcPath.isEmpty()) {
      return;
    }
    InputStream is = null;
    OutputStream os = null;
    try {
      is = new BufferedInputStream(appCtx.getAssets().open(srcPath));

      os = new BufferedOutputStream(new FileOutputStream(dstFile));
      byte[] buffer = new byte[1024];
      int length = 0;
      while ((length = is.read(buffer)) != -1) {
        os.write(buffer, 0, length);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        os.close();
        is.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

  }
}
```
### 1.3.5.2.WaveEncoder
```
package com.litongjava.whisper.cpp.android.java.media;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;

public class WaveEncoder {

  public static float[] decodeWaveFile(File file) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    try (FileInputStream fis = new FileInputStream(file)) {
      byte[] buffer = new byte[1024];
      int bytesRead;
      while ((bytesRead = fis.read(buffer)) != -1) {
        baos.write(buffer, 0, bytesRead);
      }
    }
    ByteBuffer byteBuffer = ByteBuffer.wrap(baos.toByteArray());
    byteBuffer.order(ByteOrder.LITTLE_ENDIAN);

    int channel = byteBuffer.getShort(22);
    byteBuffer.position(44);

    ShortBuffer shortBuffer = byteBuffer.asShortBuffer();
    short[] shortArray = new short[shortBuffer.limit()];
    shortBuffer.get(shortArray);

    float[] output = new float[shortArray.length / channel];

    for (int index = 0; index < output.length; index++) {
      if (channel == 1) {
        output[index] = Math.max(-1f, Math.min(1f, shortArray[index] / 32767.0f));
      } else {
        output[index] = Math.max(-1f, Math.min(1f, (shortArray[2 * index] + shortArray[2 * index + 1]) / 32767.0f / 2.0f));
      }
    }
    return output;
  }

  public static void encodeWaveFile(File file, short[] data) throws IOException {
    try (FileOutputStream fos = new FileOutputStream(file)) {
      fos.write(headerBytes(data.length * 2));

      ByteBuffer buffer = ByteBuffer.allocate(data.length * 2);
      buffer.order(ByteOrder.LITTLE_ENDIAN);
      buffer.asShortBuffer().put(data);

      byte[] bytes = new byte[buffer.limit()];
      buffer.get(bytes);

      fos.write(bytes);
    }
  }

  private static byte[] headerBytes(int totalLength) {
    if (totalLength < 44)
      throw new IllegalArgumentException("Total length must be at least 44 bytes");

    ByteBuffer buffer = ByteBuffer.allocate(44);
    buffer.order(ByteOrder.LITTLE_ENDIAN);

    buffer.put((byte) 'R');
    buffer.put((byte) 'I');
    buffer.put((byte) 'F');
    buffer.put((byte) 'F');

    buffer.putInt(totalLength - 8);

    buffer.put((byte) 'W');
    buffer.put((byte) 'A');
    buffer.put((byte) 'V');
    buffer.put((byte) 'E');

    buffer.put((byte) 'f');
    buffer.put((byte) 'm');
    buffer.put((byte) 't');
    buffer.put((byte) ' ');

    buffer.putInt(16);
    buffer.putShort((short) 1);
    buffer.putShort((short) 1);
    buffer.putInt(16000);
    buffer.putInt(32000);
    buffer.putShort((short) 2);
    buffer.putShort((short) 16);

    buffer.put((byte) 'd');
    buffer.put((byte) 'a');
    buffer.put((byte) 't');
    buffer.put((byte) 'a');

    buffer.putInt(totalLength - 44);
    buffer.position(0);

    byte[] bytes = new byte[buffer.limit()];
    buffer.get(bytes);

    return bytes;
  }
}
```
### 1.3.6.识别wav文件
#### 1.3.6.1.WhisperService
```
加载模型
whisperContext = WhisperContext.createContextFromFile(modelPath);
识别文件
transcription = whisperContext.transcribeData(audioData);
```

```
package com.litongjava.whisper.cpp.android.java.services;

import android.content.Context;
import android.os.Build;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.litongjava.whisper.cpp.android.java.media.WaveEncoder;
import com.litongjava.whisper.cpp.android.java.utils.AssetUtils;
import com.whispercppdemo.whisper.WhisperContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class WhisperService {
  private Logger log = LoggerFactory.getLogger(this.getClass());
  private WhisperContext whisperContext;

  @RequiresApi(api = Build.VERSION_CODES.O)
  public void loadModel(Context context, TextView tv) {

    File filesDir = context.getFilesDir();
    String modelFilePath = "models/ggml-tiny.bin";
    File modelFile = AssetUtils.copyFileIfNotExists(context, filesDir, modelFilePath);
    modelFilePath = modelFile.getAbsolutePath();

    String msg = "load model from :" + modelFilePath + "\n";
    log.info(msg);
    tv.append(msg);
    if (whisperContext == null) {
      long start = System.currentTimeMillis();
      whisperContext = WhisperContext.createContextFromFile(modelFilePath);
//      AopManager.me().addSingletonObject(whisperContext);
      long end = System.currentTimeMillis();
      msg = "model load successful:" + (end - start) + "ms\n";
      log.info(msg);
      tv.append(msg);
    } else {
      msg = "model loaded\n";
      log.info(msg);
      tv.append(msg);
    }
  }

  public void transcribeSample(Context context, TextView tv) {
    String sampleFilePath = "samples/jfk.wav";
    File filesDir = context.getFilesDir();
    File sampleFile = AssetUtils.copyFileIfNotExists(context, filesDir, sampleFilePath);
    log.info("transcribe file from :{}", sampleFile.getAbsolutePath());
    float[] audioData = new float[0];  // 读取音频样本
    try {
      audioData = WaveEncoder.decodeWaveFile(sampleFile);
    } catch (IOException e) {
      e.printStackTrace();
    }

    String transcription = null;  // 转录音频数据

    String msg = "";
    try {
      if (whisperContext == null) {
        msg = "please load model or wait model loaded";
        log.info(msg);

      } else {
        long start = System.currentTimeMillis();
        transcription = whisperContext.transcribeData(audioData);
        long end = System.currentTimeMillis();
        msg = "Transcript successful:" + (end - start) + "ms";
        log.info(msg);
        tv.append(msg + "\n");

        msg = "Transcription:" + transcription;
        log.info(msg);
        tv.append(msg + "\n");
      }


    } catch (ExecutionException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }


  }

  @RequiresApi(api = Build.VERSION_CODES.O)
  public void release() {
    if (whisperContext != null) {
      try {
        whisperContext.release();
      } catch (ExecutionException e) {
        e.printStackTrace();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      whisperContext = null;
    }
  }
}
```

#### 1.3.6.2 MainActivity 
activity_main.xml
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
  xmlns:app="http://schemas.android.com/apk/res-auto"
  xmlns:tools="http://schemas.android.com/tools"
  android:layout_width="match_parent"
  android:layout_height="match_parent"
  android:orientation="vertical"
  tools:context=".MainActivity">

  <LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content">

    <Button
      android:id="@+id/systemInfoBtn"
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      android:text="System Info" />

    <Button
      android:id="@+id/loadModelBtn"
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      android:text="Load model" />

  </LinearLayout>

  <LinearLayout
    android:layout_width="wrap_content"
    android:layout_height="wrap_content">

    <Button
      android:id="@+id/transcriptSampleBtn"
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      android:text="Transcribe sample" />

    <Button
      android:id="@+id/clearBtn"
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      android:text="Clear" />
  </LinearLayout>

  <TextView
    android:id="@+id/sample_text"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Hello World!"
    app:layout_constraintBottom_toBottomOf="parent"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintRight_toRightOf="parent"
    app:layout_constraintTop_toTopOf="parent" />

</LinearLayout>

```
MainActivity
```
package com.litongjava.whisper.android.java;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.litongjava.android.view.inject.annotation.FindViewById;
import com.litongjava.android.view.inject.annotation.FindViewByIdLayout;
import com.litongjava.android.view.inject.annotation.OnClick;
import com.litongjava.android.view.inject.utils.ViewInjectUtils;
import com.litongjava.jfinal.aop.Aop;
import com.litongjava.whisper.android.java.services.WhisperService;
import com.litongjava.whisper.android.java.utils.AssetUtils;
import com.litongjava.whisper.android.java.utils.WaveEncoder;
import com.whispercppdemo.whisper.WhisperContext;
import com.whispercppdemo.whisper.WhisperLib;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;


@FindViewByIdLayout(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

  @FindViewById(R.id.sample_text)
  private TextView tv;

  Logger log = LoggerFactory.getLogger(this.getClass());
  private WhisperService whisperService = Aop.get(WhisperService.class);

  @RequiresApi(api = Build.VERSION_CODES.O)
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //setContentView(R.layout.activity_main);
    ViewInjectUtils.injectActivity(this, this);
    showSystemInfo();
  }

  @RequiresApi(api = Build.VERSION_CODES.O)
  @OnClick(R.id.loadModelBtn)
  public void loadModelBtn_OnClick(View v) {
    Context context = getBaseContext();
    whisperService.loadModel(context, tv);
  }

  @OnClick(R.id.transcriptSampleBtn)
  public void transcriptSampleBtn_OnClick(View v) {
    Context context = getBaseContext();
    whisperService.transcribeSample(context, tv);
  }


  @RequiresApi(api = Build.VERSION_CODES.O)
  @OnClick(R.id.systemInfoBtn)
  public void systemInfoBtn_OnClick(View v) {
    showSystemInfo();
  }

  @RequiresApi(api = Build.VERSION_CODES.O)
  public void showSystemInfo() {
    String systemInfo = WhisperLib.getSystemInfo();
    tv.append(systemInfo + "\n");
  }

  @OnClick(R.id.clearBtn)
  public void clearBtn_OnClick(View v) {
    tv.setText("");
  }

  @RequiresApi(api = Build.VERSION_CODES.O)
  @Override
  protected void onDestroy() {
    super.onDestroy();
    whisperService.release();
  }
}
```