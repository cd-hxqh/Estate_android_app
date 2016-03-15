package com.example.admin.estate_android_app.zbar.lib.decode;

import java.util.concurrent.CountDownLatch;

import android.os.Handler;
import android.os.Looper;

import com.example.admin.estate_android_app.ui.fragment.ScaningFragment;
import com.example.admin.estate_android_app.zbar.lib.CaptureActivity;


/**
 * 描述: 解码线程
 */
final class DecodeThread extends Thread {
	CaptureActivity activity1 = null;
	ScaningFragment activity = null;
	private Handler handler;
	private final CountDownLatch handlerInitLatch;

	DecodeThread(ScaningFragment activity) {
		this.activity = activity;
		handlerInitLatch = new CountDownLatch(1);
	}

	DecodeThread(CaptureActivity activity) {
		this.activity1 = activity;
		handlerInitLatch = new CountDownLatch(1);
	}

	Handler getHandler() {
		try {
			handlerInitLatch.await();
		} catch (InterruptedException ie) {
			// continue?
		}
		return handler;
	}

	@Override
	public void run() {
		Looper.prepare();
		if (activity != null) {
			handler = new DecodeHandler(activity);
		} else {
			handler = new DecodeHandler(activity1);
		}

		handlerInitLatch.countDown();
		Looper.loop();
	}

}
