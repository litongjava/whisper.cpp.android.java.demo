package com.litongjava.whisper.cpp.android.java.recoder;

import android.annotation.SuppressLint;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;

import com.litongjava.whisper.cpp.android.java.media.WaveEncoder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class Recorder {

  private final ExecutorService executorService = Executors.newSingleThreadExecutor();
  private AudioRecordThread recorder;

  public void startRecording(File outputFile, OnErrorListener onErrorListener) {
    executorService.submit(() -> {
      recorder = new AudioRecordThread(outputFile, onErrorListener);
      recorder.start();
    });
  }

  public void stopRecording() {
    executorService.submit(() -> {
      if (recorder != null) {
        recorder.stopRecording();
        try {
          recorder.join();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        recorder = null;
      }
    });
  }

  private static class AudioRecordThread extends Thread {
    private final File outputFile;
    private final OnErrorListener onErrorListener;
    private final AtomicBoolean quit = new AtomicBoolean(false);

    AudioRecordThread(File outputFile, OnErrorListener onErrorListener) {
      super("AudioRecorder");
      this.outputFile = outputFile;
      this.onErrorListener = onErrorListener;
    }

    @SuppressLint("MissingPermission")
    @Override
    public void run() {
      try {
        int bufferSize = AudioRecord.getMinBufferSize(
          16000,
          AudioFormat.CHANNEL_IN_MONO,
          AudioFormat.ENCODING_PCM_16BIT
        ) * 4;
        short[] buffer = new short[bufferSize / 2];

        AudioRecord audioRecord = new AudioRecord(
          MediaRecorder.AudioSource.MIC,
          16000,
          AudioFormat.CHANNEL_IN_MONO,
          AudioFormat.ENCODING_PCM_16BIT,
          bufferSize
        );

        try {
          audioRecord.startRecording();

          List<Short> allData = new ArrayList<>();

          while (!quit.get()) {
            int read = audioRecord.read(buffer, 0, buffer.length);
            if (read > 0) {
              for (int i = 0; i < read; i++) {
                allData.add(buffer[i]);
              }
            } else {
              throw new RuntimeException("audioRecord.read returned " + read);
            }
          }

          audioRecord.stop();

          short[] dataArr = new short[allData.size()];
          for (int i = 0; i < allData.size(); i++) {
            dataArr[i] = allData.get(i);
          }

          WaveEncoder.encodeWaveFile(outputFile, dataArr);
        } finally {
          audioRecord.release();
        }
      } catch (Exception e) {
        onErrorListener.onError(e);
      }
    }

    void stopRecording() {
      quit.set(true);
    }
  }

  public interface OnErrorListener {
    void onError(Exception e);
  }
}
