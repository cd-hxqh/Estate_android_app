package com.example.admin.estate_android_app.zbar.lib.decode;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.example.admin.estate_android_app.R;
import com.example.admin.estate_android_app.ui.fragment.ScaningFragment;
import com.example.admin.estate_android_app.zbar.lib.CaptureActivity;
import com.example.admin.estate_android_app.zbar.lib.ZbarManager;


/**
 * 描述: 接受消息后解码
 */
final class DecodeHandler extends Handler {

	ScaningFragment activity = null;

	CaptureActivity activity1 = null;

	DecodeHandler(CaptureActivity activity) {
		this.activity1 = activity;
	}

	DecodeHandler(ScaningFragment activity) {
		this.activity = activity;
	}


	@Override
	public void handleMessage(Message message) {
		switch (message.what) {
		case R.id.decode:
			decode((byte[]) message.obj, message.arg1, message.arg2);
			break;
		case R.id.quit:
			Looper.myLooper().quit();
			break;
		}
	}

	private void decode(byte[] data, int width, int height) {
		byte[] rotatedData = new byte[data.length];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++)
				rotatedData[x * height + height - y - 1] = data[x + y * width];
		}
		int tmp = width;// Here we are swapping, that's the difference to #11
		width = height;
		height = tmp;

		ZbarManager manager = new ZbarManager();
		if(activity!=null){
			String result = manager.decode(rotatedData, width, height, true,
					activity.getX(), activity.getY(), activity.getCropWidth(),
					activity.getCropHeight());

			if (result != null) {
				if (null != activity.getHandler()) {
					Message msg = new Message();
					msg.obj = result;
					msg.what = R.id.decode_succeeded;
					activity.getHandler().sendMessage(msg);
				}
			} else {
				if (null != activity.getHandler()) {
					activity.getHandler().sendEmptyMessage(R.id.decode_failed);
				}
			}
		}else{

			String result = manager.decode(rotatedData, width, height, true,
					activity1.getX(), activity1.getY(), activity1.getCropWidth(),
					activity1.getCropHeight());

			if (result != null) {
				if (null != activity1.getHandler()) {
					Message msg = new Message();
					msg.obj = result;
					msg.what = R.id.decode_succeeded;
					activity1.getHandler().sendMessage(msg);
				}
			} else {
				if (null != activity1.getHandler()) {
					activity1.getHandler().sendEmptyMessage(R.id.decode_failed);
				}
			}
		
		}
		
	}

}
