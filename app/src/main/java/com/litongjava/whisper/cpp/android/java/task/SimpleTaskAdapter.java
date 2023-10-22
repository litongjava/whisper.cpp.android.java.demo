package com.litongjava.whisper.cpp.android.java.task;

import com.blankj.utilcode.util.ThreadUtils;

public abstract class SimpleTaskAdapter<T> extends ThreadUtils.Task<T> {
  @Override
  public void onSuccess(T result) {
  }

  @Override
  public void onCancel() {
  }

  @Override
  public void onFail(Throwable t) {
  }
}
