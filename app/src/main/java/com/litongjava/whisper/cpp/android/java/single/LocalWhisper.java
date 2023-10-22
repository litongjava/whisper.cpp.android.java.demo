package com.litongjava.whisper.cpp.android.java.single;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.blankj.utilcode.util.Utils;
import com.litongjava.whisper.cpp.android.java.bean.WhisperSegment;
import com.litongjava.whisper.cpp.android.java.utils.AssetUtils;
import com.whispercppdemo.whisper.WhisperContext;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutionException;

//import com.litongjava.whisper.android.java.bean.WhisperSegment;
//import com.litongjava.whisper.android.java.utils.AssetUtils;

public enum LocalWhisper {
  INSTANCE;

  public static final String modelFilePath = "models/ggml-tiny.bin";
  private WhisperContext whisperContext;

  @RequiresApi(api = Build.VERSION_CODES.O)
  LocalWhisper() {
    Application context = Utils.getApp();
    File filesDir = context.getFilesDir();
    File modelFile = AssetUtils.copyFileIfNotExists(context, filesDir, modelFilePath);
    String realModelFilePath = modelFile.getAbsolutePath();
    whisperContext = WhisperContext.createContextFromFile(realModelFilePath);
  }

  public synchronized String transcribeData(float[] data) throws ExecutionException, InterruptedException {
    return whisperContext.transcribeData(data);
  }

  public List<WhisperSegment> transcribeDataWithTime(float[] audioData) throws ExecutionException, InterruptedException {
    return whisperContext.transcribeDataWithTime(audioData);
  }

  public void init() {
    //noting to do.but init
  }


}
