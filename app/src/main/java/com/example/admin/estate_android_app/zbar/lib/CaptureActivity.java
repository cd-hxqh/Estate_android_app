package com.example.admin.estate_android_app.zbar.lib;

import java.io.IOException;
import java.text.SimpleDateFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.Window;
import android.view.SurfaceHolder.Callback;
import android.view.View.OnClickListener;
import android.view.SurfaceView;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.estate_android_app.R;
import com.example.admin.estate_android_app.ui.activity.BaseActivity;
import com.example.admin.estate_android_app.zbar.lib.camera.CameraManager;
import com.example.admin.estate_android_app.zbar.lib.decode.CaptureActivityHandler;
import com.example.admin.estate_android_app.zbar.lib.decode.FinishListener;
import com.example.admin.estate_android_app.zbar.lib.decode.InactivityTimer;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;


/**
 * 描述: 扫描界面
 */
public class CaptureActivity extends BaseActivity implements Callback {
	private static final String TAG = "CaptureActivity";
	private CaptureActivityHandler handler;
	private boolean hasSurface;
	private InactivityTimer inactivityTimer;
	private MediaPlayer mediaPlayer;
	private boolean playBeep;
	private static final float BEEP_VOLUME = 0.50f;
	private boolean vibrate;
	private boolean isPause = false;
	private int x = 0;
	private int y = 0;
	private int cropWidth = 0;
	private int cropHeight = 0;
	private RelativeLayout mContainer = null;
	private RelativeLayout mCropLayout = null;
	boolean flag = true;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getCropWidth() {
		return cropWidth;
	}

	public void setCropWidth(int cropWidth) {
		this.cropWidth = cropWidth;
	}

	public int getCropHeight() {
		return cropHeight;
	}

	public void setCropHeight(int cropHeight) {
		this.cropHeight = cropHeight;
	}

	/** 标题 **/
	private TextView titleText;
	private ImageView rightImageView;
    /**标志**/
	private int codeMark;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_qr_scan);
		getData();
		
		
		// 初始化 CameraManager
		CameraManager.init(getApplication());
		CameraManager.get().openLight();
		light();
		hasSurface = false;
		inactivityTimer = new InactivityTimer(this);

		mContainer = (RelativeLayout) findViewById(R.id.capture_containter);
		mCropLayout = (RelativeLayout) findViewById(R.id.capture_crop_layout);
		titleText = (TextView) findViewById(R.id.title_text);
		titleText.setText(getResources().getString(R.string.title_scan_text));
		ImageView flashImage = (ImageView) findViewById(R.id.capture_flashlight);
		rightImageView = (ImageView) findViewById(R.id.right_image_btn);
		rightImageView.setVisibility(View.VISIBLE);
		rightImageView.setImageResource(R.drawable.scan_flashlight_pressed);
		rightImageView
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						flashlight();

					}
				});
		ImageView mQrLineView = (ImageView) findViewById(R.id.capture_scan_line);
		ScaleAnimation animation = new ScaleAnimation(1.0f, 1.0f, 0.0f, 1.0f);
		animation.setRepeatCount(-1);
		animation.setRepeatMode(Animation.RESTART);
		animation.setInterpolator(new LinearInterpolator());
		animation.setDuration(1200);
		mQrLineView.startAnimation(animation);
	}

	private void getData() {
		codeMark=getIntent().getExtras().getInt("code_mark");
		Log.i(TAG, "codeMark="+codeMark);
	}

	protected void flashlight() {
		if (flag == true) {
			// 开闪光灯
			CameraManager.get().openLight();
			rightImageView.setImageResource(R.drawable.scan_flashlight_normal);
			flag = false;
		} else {
			// 关闪光灯
			CameraManager.get().offLight();
			rightImageView.setImageResource(R.drawable.scan_flashlight_pressed);
			flag = true;
		}

	}

	protected void light() {
		if (flag == true) {
			// flag = false;
			// 开闪光灯
			CameraManager.get().openLight();
		} else {
			flag = true;
			// 关闪光灯
			CameraManager.get().offLight();
		}

	}

	@SuppressWarnings("deprecation")
	@Override
	protected void onResume() {
		super.onResume();
		isPause = false;
		SurfaceView surfaceView = (SurfaceView) findViewById(R.id.capture_preview);
		SurfaceHolder surfaceHolder = surfaceView.getHolder();
		if (hasSurface) {
			initCamera(surfaceHolder);
		} else {
			surfaceHolder.addCallback(this);
			surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		}
		playBeep = true;
		AudioManager audioService = (AudioManager) getSystemService(AUDIO_SERVICE);
		if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
			playBeep = false;
		}
		initBeepSound();
		vibrate = true;
	}

	@Override
	protected void onPause() {

		try {
			super.onPause();
			if (this.handler != null) {
				this.handler.quitSynchronously();
				this.handler = null;
			}
			// decodeOrStoreSavedBitmap(null, null);
		} catch (RuntimeException e) {
			displayFrameworkBugMessageAndExit();
			CameraManager.get().closeDriver();
			isPause = true;
			return;
		} catch (Exception localException) {
			localException.printStackTrace();
		}

	}

	/**
	 * 若摄像头被占用或者摄像头有问题则跳出提示对话框
	 */
	private void displayFrameworkBugMessageAndExit() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setIcon(R.mipmap.ic_launcher);
		builder.setTitle(getString(R.string.app_name));
		builder.setMessage(getString(R.string.app_name));
		builder.setPositiveButton("ok", new FinishListener(this));
		builder.setOnCancelListener(new FinishListener(this));
		builder.show();
	}

	@Override
	protected void onDestroy() {
		inactivityTimer.shutdown();
		CameraManager.get().closeDriver();
		super.onDestroy();
	}

	@Override
	protected void findViewById() {

	}

	@Override
	protected void initView() {

	}

	private JsonElement jsonElement;

	public void handleDecode(String result) {

		playBeepSoundAndVibrate();
		String resultString = result;
		Log.i(TAG, "数据内容：" + resultString);


	}


	/** 判断是否为有效的json格式数据 */
	public boolean isValidJson(String json) {
		if (json.equals("")) {
			Toast.makeText(CaptureActivity.this, "扫描数据为空！！！",
					Toast.LENGTH_SHORT).show();
			return false;
		}
		try {
			jsonElement = new JsonParser().parse(json);
			return jsonElement.isJsonObject();
		} catch (JsonSyntaxException e) {
			Log.i(TAG, json + "is invalid json str", e);
		}

		return false;
	}

	private void initCamera(SurfaceHolder surfaceHolder) {
		try {
			CameraManager.get().openDriver(surfaceHolder);

			Point point = CameraManager.get().getCameraResolution();
			int width = point.y;
			int height = point.x;

			int x = mCropLayout.getLeft() * width / mContainer.getWidth();
			int y = mCropLayout.getTop() * height / mContainer.getHeight();

			int cropWidth = mCropLayout.getWidth() * width
					/ mContainer.getWidth();
			int cropHeight = mCropLayout.getHeight() * height
					/ mContainer.getHeight();

			setX(x);
			setY(y);
			setCropWidth(cropWidth);
			setCropHeight(cropHeight);

		} catch (IOException ioe) {
			displayFrameworkBugMessageAndExit();
		} catch (RuntimeException e) {
			displayFrameworkBugMessageAndExit();
		}
		if (handler == null) {
			handler = new CaptureActivityHandler(CaptureActivity.this);
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		if (!hasSurface) {
			hasSurface = true;
			if (!this.isPause)
				initCamera(holder);
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		hasSurface = false;

	}

	public Handler getHandler() {
		return handler;
	}

	private void initBeepSound() {
		if (playBeep && mediaPlayer == null) {
			setVolumeControlStream(AudioManager.STREAM_MUSIC);
			mediaPlayer = new MediaPlayer();
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mediaPlayer.setOnCompletionListener(beepListener);

			AssetFileDescriptor file = getResources().openRawResourceFd(
					R.raw.beep);
			try {
				mediaPlayer.setDataSource(file.getFileDescriptor(),
						file.getStartOffset(), file.getLength());
				file.close();
				mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
				mediaPlayer.prepare();
			} catch (IOException e) {
				mediaPlayer = null;
			}
		}
	}

	private static final long VIBRATE_DURATION = 200L;

	private void playBeepSoundAndVibrate() {
		if (playBeep && mediaPlayer != null) {
			mediaPlayer.start();
		}
		if (vibrate) {
			Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
			vibrator.vibrate(VIBRATE_DURATION);
		}
	}

	private final OnCompletionListener beepListener = new OnCompletionListener() {
		public void onCompletion(MediaPlayer mediaPlayer) {
			mediaPlayer.seekTo(0);
		}
	};

}