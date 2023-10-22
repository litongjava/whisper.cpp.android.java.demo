package com.litongjava.whisper.cpp.android.java.task;

import android.content.Context;
import android.widget.TextView;

import com.blankj.utilcode.util.ThreadUtils;
import com.litongjava.jfinal.aop.Aop;
import com.litongjava.whisper.cpp.android.java.services.WhisperService;

public class TranscriptionTask extends ThreadUtils.Task<Object> {
  private final Context context;
  private final TextView tv;
  private final String sampleFilePath;

  public TranscriptionTask(Context context, TextView tv,String sampleFilePath) {
    this.context = context;
    this.tv = tv;
    this.sampleFilePath = sampleFilePath;

  }

  @Override
  public Object doInBackground() {
    Aop.get(WhisperService.class).transcribeSample(context, tv, sampleFilePath);
    return null;
  }

  @Override
  public void onSuccess(Object result) {
  }

  @Override
  public void onCancel() {
  }

  @Override
  public void onFail(Throwable t) {
  }
}