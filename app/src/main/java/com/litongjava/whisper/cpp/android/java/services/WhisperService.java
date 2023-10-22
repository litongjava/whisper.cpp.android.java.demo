package com.litongjava.whisper.cpp.android.java.services;

import android.content.Context;
import android.os.Build;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.litongjava.jfinal.aop.Aop;
import com.litongjava.whisper.cpp.android.java.bean.WhisperSegment;
import com.litongjava.whisper.cpp.android.java.media.WaveEncoder;
import com.litongjava.whisper.cpp.android.java.single.LocalWhisper;
import com.litongjava.whisper.cpp.android.java.utils.AssetUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class WhisperService {
  private Logger log = LoggerFactory.getLogger(this.getClass());


  @RequiresApi(api = Build.VERSION_CODES.O)
  public void loadModel() {

    String modelFilePath = LocalWhisper.modelFilePath;
    String msg = "load model from :" + modelFilePath ;
    outputMsg(msg);

    long start = System.currentTimeMillis();
    LocalWhisper.INSTANCE.init();
    long end = System.currentTimeMillis();
    msg = "model load successful:" + (end - start) + "ms";
    outputMsg(msg);

  }

  public void transcribeSample(Context context, TextView tv, String sampleFilePath) {
    String msg = "";
    long start = System.currentTimeMillis();

    File filesDir = context.getFilesDir();
    File sampleFile = AssetUtils.copyFileIfNotExists(context, filesDir, sampleFilePath);
    long end = System.currentTimeMillis();
    msg = "copy file:" + (end - start) + "ms";
    outputMsg(msg);

    msg = "transcribe file from :" + sampleFile.getAbsolutePath();
    outputMsg(msg);

    start = System.currentTimeMillis();
    float[] audioData = new float[0];  // 读取音频样本
    try {
      audioData = WaveEncoder.decodeWaveFile(sampleFile);
    } catch (IOException e) {
      e.printStackTrace();
      return;
    }
    end = System.currentTimeMillis();
    msg = "decode wave file:" + (end - start) + "ms";
    outputMsg(msg);

    start = System.currentTimeMillis();
    List<WhisperSegment> transcription = null;
    try {

      //transcription = LocalWhisper.INSTANCE.transcribeData(audioData);
      transcription = LocalWhisper.INSTANCE.transcribeDataWithTime(audioData);
    } catch (ExecutionException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    end = System.currentTimeMillis();
    msg = "Transcript successful:" + (end - start) + "ms";
    outputMsg(msg);

    msg = "Transcription:" + transcription.toString();
    outputMsg(msg);
  }

  private void outputMsg(String msg) {
    Aop.get(OutputTextViewService.class).println(msg);
    log.info(msg);
  }

  @RequiresApi(api = Build.VERSION_CODES.O)
  public void release() {
    //noting to do
  }
}
