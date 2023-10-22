package com.litongjava.whisper.cpp.android.java.demo.app;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

public class App extends Application {
  @Override
  public void onCreate() {
    super.onCreate();
    Utils.init(this);
  }
}
