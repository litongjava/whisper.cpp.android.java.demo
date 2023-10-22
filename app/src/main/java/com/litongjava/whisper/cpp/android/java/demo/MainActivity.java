package com.litongjava.whisper.cpp.android.java.demo;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.ThreadUtils;
import com.litongjava.android.view.inject.annotation.FindViewById;
import com.litongjava.android.view.inject.annotation.FindViewByIdLayout;
import com.litongjava.android.view.inject.annotation.OnClick;
import com.litongjava.android.view.inject.utils.ViewInjectUtils;
import com.litongjava.jfinal.aop.Aop;
import com.litongjava.jfinal.aop.AopManager;
import com.litongjava.whisper.cpp.android.java.services.OutputTextViewService;
import com.litongjava.whisper.cpp.android.java.services.WhisperService;
import com.litongjava.whisper.cpp.android.java.task.TranscriptionTask;
import com.whispercppdemo.whisper.WhisperLib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FindViewByIdLayout(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

  @FindViewById(R.id.sample_text)
  private TextView tv;

  Logger log = LoggerFactory.getLogger(this.getClass());
  OutputTextViewService outputTextViewService;
  private WhisperService whisperService;

  @RequiresApi(api = Build.VERSION_CODES.O)
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //setContentView(R.layout.activity_main);
    ViewInjectUtils.injectActivity(this, this);
    outputTextViewService = new OutputTextViewService(this.tv);
    //add to aop container
    AopManager.me().addSingletonObject(outputTextViewService);
    whisperService= Aop.get(WhisperService.class);
    showSystemInfo();
  }

  @RequiresApi(api = Build.VERSION_CODES.O)
  @OnClick(R.id.loadModelBtn)
  public void loadModelBtn_OnClick(View v) {
    Context context = getBaseContext();
    whisperService.loadModel();
  }

  @OnClick(R.id.transcriptSampleBtn)
  public void transcriptSampleBtn_OnClick(View v) {
    Context context = getBaseContext();
    String sampleFilePath = "samples/jfk.wav";
    ThreadUtils.executeByIo(new TranscriptionTask(context, tv, sampleFilePath));
  }

  @OnClick(R.id.transcriptLongSampleBtn)
  public void transcriptLongSampleBtn_OnClick(View v) {
    Context context = getBaseContext();
    String sampleFilePath = "samples/A1_APartyInvitation__lesson_1368784253.wav";
    ThreadUtils.executeByIo(new TranscriptionTask(context, tv, sampleFilePath));
  }


  @RequiresApi(api = Build.VERSION_CODES.O)
  @OnClick(R.id.systemInfoBtn)
  public void systemInfoBtn_OnClick(View v) {
    showSystemInfo();
  }

  @RequiresApi(api = Build.VERSION_CODES.O)
  public void showSystemInfo() {
    String systemInfo = WhisperLib.getSystemInfo();
    Aop.get(OutputTextViewService.class).println(systemInfo);
  }

  @OnClick(R.id.clearBtn)
  public void clearBtn_OnClick(View v) {
    Aop.get(OutputTextViewService.class).clear();
  }

  @RequiresApi(api = Build.VERSION_CODES.O)
  @Override
  protected void onDestroy() {
    super.onDestroy();
    whisperService.release();
  }
}