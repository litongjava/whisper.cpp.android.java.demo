package com.litongjava.whisper.cpp.android.java.services;

import android.widget.TextView;

public class OutputTextViewService {
  private TextView tv;

  public OutputTextViewService(TextView tv) {
    this.tv = tv;
  }

  public void println(String text) {
    tv.append(text + "\n");
  }

  public void clear(){
    tv.setText("");
  }
}
