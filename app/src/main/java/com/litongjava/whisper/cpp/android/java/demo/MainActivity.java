package com.litongjava.whisper.cpp.android.java.demo;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.litongjava.android.view.inject.annotation.FindViewById;
import com.litongjava.android.view.inject.annotation.FindViewByIdLayout;
import com.litongjava.android.view.inject.utils.ViewInjectUtils;
import com.litongjava.whisper.cpp.android.java.demo.media.WaveEncoder;
import com.litongjava.whisper.cpp.android.java.demo.utils.AssetUtils;
import com.whispercppdemo.whisper.WhisperContext;
import com.whispercppdemo.whisper.WhisperCpuConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

@FindViewByIdLayout(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
  private Logger log = LoggerFactory.getLogger(this.getClass());
  private WhisperContext whisperContext;

  @FindViewById(R.id.textHelloWorld)
  private TextView textHelloWorld;

  @RequiresApi(api = Build.VERSION_CODES.O)
  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    ///setContentView(R.layout.activity_main);
    ViewInjectUtils.injectActivity(this, this);
    int preferredThreadCount = WhisperCpuConfig.getPreferredThreadCount();
    log.info("preferredThreadCount:{}", preferredThreadCount);

    Context baseContext = getBaseContext();
    File filesDir = baseContext.getFilesDir();
    log.info("filesDir:{}", filesDir);
    AssetManager assets = baseContext.getAssets();
    try {
      String[] models = assets.list("models");
      log.info("models:{}", Arrays.toString(models));
    } catch (IOException e) {
      e.printStackTrace();
    }

    try {
      String[] samples = assets.list("samples");
      log.info("samples:{}", Arrays.toString(samples));
    } catch (IOException e) {
      e.printStackTrace();
    }
    String modelFilePath = "models/ggml-tiny.bin";
    File modelFile = AssetUtils.copyFileIfNotExists(baseContext, filesDir, modelFilePath);
    modelFilePath = modelFile.getAbsolutePath();
    // 加载模型
    loadModel(modelFilePath);


    String sampleFilePath = "samples/jfk.wav";
    File sampleFile = AssetUtils.copyFileIfNotExists(baseContext, filesDir, sampleFilePath);
    // 识别样本
    transcribeSample(sampleFile);
  }

  @RequiresApi(api = Build.VERSION_CODES.O)
  private void loadModel(String modelPath) {
    log.info("load model from :{}", modelPath);
    whisperContext = WhisperContext.createContextFromFile(modelPath);
  }

  private void transcribeSample(File sampleFile) {
    log.info("transcribe file from :{}", sampleFile.getAbsolutePath());
    float[] audioData = new float[0];  // 读取音频样本
    try {
      audioData = WaveEncoder.decodeWaveFile(sampleFile);
    } catch (IOException e) {
      e.printStackTrace();
    }

    String transcription = null;  // 转录音频数据
    try {
      transcription = whisperContext.transcribeData(audioData);
    } catch (ExecutionException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    log.info("Transcription: {}", transcription);  // 打印转录结果
    textHelloWorld.setText(transcription);
  }

  @RequiresApi(api = Build.VERSION_CODES.O)
  @Override
  protected void onDestroy() {
    super.onDestroy();
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
