/**
 * @Title: TestFragment.java
 * @Package com.estate.client.fragment
 * @Description: TODO
 * @author taochao
 * @date 2016年3月15日 下午2:24:06
 * @version V1.0
 */
package com.example.admin.estate_android_app.ui.fragment;

import java.io.IOException;
import java.text.SimpleDateFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.estate_android_app.R;
import com.example.admin.estate_android_app.zbar.lib.camera.CameraManager;
import com.example.admin.estate_android_app.zbar.lib.decode.CaptureActivityHandler;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;


/**
 * @ClassName: ScanningFragment
 * @Description: 二维码扫描
 * @author liyangquan
 * @date 2014年5月25日 下午2:24:06
 */

public class ScaningFragment extends BaseFragment implements Callback {
    private static final String TAG = "ScanningFragment";
    private CaptureActivityHandler handler;
    private boolean hasSurface;
    // private InactivityTimer inactivityTimer;
    private MediaPlayer mediaPlayer;
    private boolean playBeep;
    private static final float BEEP_VOLUME = 0.50f;
    private boolean vibrate;
    private int x = 0;
    private int y = 0;
    private int cropWidth = 0;
    private int cropHeight = 0;
    private RelativeLayout mContainer = null;
    private RelativeLayout mCropLayout = null;
    private View view;
    /** 标题 **/
    private TextView titleText;
    private ImageView rightImageView;
    private JsonElement jsonElement;
    private boolean flag = true;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        CameraManager.init(getActivity());
        hasSurface = false;
        // inactivityTimer = new InactivityTimer(getActivity());
        view = inflater.inflate(R.layout.activity_qr_scan, null);
        mContainer = (RelativeLayout) view
                .findViewById(R.id.capture_containter);
        mCropLayout = (RelativeLayout) view
                .findViewById(R.id.capture_crop_layout);
        titleText = (TextView) view.findViewById(R.id.title_text);
        titleText.setText(getResources().getString(R.string.title_scan_text));
        ImageView mQrLineView = (ImageView) view
                .findViewById(R.id.capture_scan_line);
        ImageView flashImage = (ImageView) view
                .findViewById(R.id.capture_flashlight);

        rightImageView = (ImageView) view.findViewById(R.id.right_image_btn);
        rightImageView.setVisibility(View.VISIBLE);
        rightImageView.setImageResource(R.drawable.scan_flashlight_pressed);
        rightImageView
                .setOnClickListener(new android.view.View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        flashlight();

                    }
                });
        ScaleAnimation animation = new ScaleAnimation(1.0f, 1.0f, 0.0f, 1.0f);
        animation.setRepeatCount(-1);
        animation.setRepeatMode(Animation.RESTART);
        animation.setInterpolator(new LinearInterpolator());
        animation.setDuration(1200);
        mQrLineView.startAnimation(animation);
        return view;

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

    @SuppressWarnings("deprecation")
    @Override
    public void onResume() {
        super.onResume();
        SurfaceView surfaceView = (SurfaceView) view
                .findViewById(R.id.capture_preview);
        SurfaceHolder surfaceHolder = surfaceView.getHolder();
        if (hasSurface) {
            initCamera(surfaceHolder);
        } else {
            surfaceHolder.addCallback(this);
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }
        playBeep = true;
        AudioManager audioService = (AudioManager) getActivity()
                .getSystemService(Context.AUDIO_SERVICE);
        if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
            playBeep = false;
        }
        initBeepSound();
        vibrate = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (handler != null) {
            handler.quitSynchronously();
            handler = null;
        }
        CameraManager.get().closeDriver();
        CameraManager.get().offLight();
        rightImageView.setImageResource(R.drawable.scan_flashlight_pressed);
        flag = true;
    }

    @Override
    public void onDestroy() {
        // inactivityTimer.shutdown();
        super.onDestroy();
    }

    public void handleDecode(String result) {
//        // inactivityTimer.onActivity();
//        playBeepSoundAndVibrate();
//        String resultString = result;
//        Log.i(TAG, "数据内容：" + resultString);
//        SimpleDateFormat sDateFormat = new SimpleDateFormat(
//                "yyyy-MM-dd HH:mm:ss");
//        String scantime = sDateFormat.format(new java.util.Date());
//        if (isValidJson(resultString)) {
//            JsonObject jsonObject = jsonElement.getAsJsonObject();
//            Gson gson = new Gson();
//            if (jsonObject.has("ASSET")) { //仪表
//                JsonObject object = jsonObject.getAsJsonObject("ASSET");
//                Asset asset = gson.fromJson(object.toString(), Asset.class);
//                String description = asset.getDescription();
//                String udismeasure = asset.getUdismeasure();
//                if ("0".equals(udismeasure)) {
//                    Intent intent = new Intent(view.getContext(),
//                            AssetsActivity.class);
//                    Bundle mBundle = new Bundle();
//                    mBundle.putSerializable("assets", asset);
//                    intent.putExtras(mBundle);
//                    startActivity(intent);
//                    Log.i(TAG, "description=" + description);
//                } else {
//                    Toast.makeText(view.getContext(), "量具！", Toast.LENGTH_SHORT)
//                            .show();
//                    handler.sendEmptyMessage(R.id.restart_preview);
//                }
//
//                Log.i(TAG, "ContainsKey=" + "ASSET");
//            } else if (jsonObject.has("LOCATIONS")) { //位置
//                JsonObject object = jsonObject.getAsJsonObject("LOCATIONS");
//                Locations locations = gson.fromJson(object.toString(),
//                        Locations.class);
//                String type = locations.getType();
//                if ("STOREROOM".equals(type)) { //库房
//
//                    Intent intent = new Intent(view.getContext(),
//                            LocInventoryActivity.class);
//                    Bundle mBundle = new Bundle();
//                    mBundle.putSerializable("locations", locations);
//                    intent.putExtras(mBundle);
//                    startActivity(intent);
//
//                } else {
//                    Intent intent = new Intent(view.getContext(),
//                            LocationsActivity.class);
//                    Bundle mBundle = new Bundle();
//                    mBundle.putSerializable("locations", locations);
//                    intent.putExtras(mBundle);
//                    startActivity(intent);
//                }
//
//            } else if (jsonObject.has("ITEM")) {
//            } else if (jsonObject.has("ASSETMETER")) {
//                JsonObject object = jsonObject.getAsJsonObject("ASSETMETER");
//                QrAssetMeter assetMeter = gson.fromJson(object.toString(),
//                        QrAssetMeter.class);
//                assetMeter.setScantime(scantime);
//                QrAssetMeterDaoImpl.getInstance(view.getContext())
//                        .deleteQrAssetMeter(assetMeter);
//                QrAssetMeterDaoImpl.getInstance(view.getContext()).addEntity(
//                        assetMeter);
//                Intent intent = new Intent(view.getContext(),
//                        AssetsMeterActivity.class);
//                Bundle mBundle = new Bundle();
//                mBundle.putSerializable("qrassetsmeter", assetMeter);
//                intent.putExtras(mBundle);
//                startActivity(intent);
//                Log.i(TAG, "ContainsKey=" + "ASSETMETER");
//            } else if (jsonObject.has("LOCATIONMETER")) {
//                JsonObject object = jsonObject.getAsJsonObject("LOCATIONMETER");
//                QrLocationmeter locationmeter = gson.fromJson(
//                        object.toString(), QrLocationmeter.class);
//                locationmeter.setScantime(scantime);
//                QrLocationMeterDaoImpl.getInstance(view.getContext())
//                        .deleteQrLocationMeter(locationmeter);
//                QrLocationMeterDaoImpl.getInstance(view.getContext())
//                        .addEntity(locationmeter);
//                Intent intent = new Intent(view.getContext(),
//                        LocationsMeterActivity.class);
//                Bundle mBundle = new Bundle();
//                mBundle.putSerializable("qrlocationmeter", locationmeter);
//                intent.putExtras(mBundle);
//                startActivity(intent);
//                Log.i(TAG, "ContainsKey=" + "ASSETMETER");
//            } else if (jsonObject.has("COMPANIES")) {
//                Log.i(TAG, "ContainsKey=" + "COMPANIES");
//            } else if (jsonObject.has("WORKORDER")) {
//                Log.i(TAG, "ContainsKey=" + "WORKORDER");
//            } else if (jsonObject.has("PR")) {
//                Log.i(TAG, "ContainsKey=" + "PR");
//            } else if (jsonObject.has("PO")) {
//                Log.i(TAG, "ContainsKey=" + "PO");
//            } else if (jsonObject.has("INVOICE")) {
//                Log.i(TAG, "ContainsKey=" + "INVOICE");
//            } else if (jsonObject.has("UDPROJECT")) {
//                Log.i(TAG, "ContainsKey=" + "UDPROJECT");
//            } else if (jsonObject.has("UDCHECKBILL")) {
//                Log.i(TAG, "ContainsKey=" + "UDCHECKBILL");
//            } else if (jsonObject.has("COMPANIES")) {
//                Log.i(TAG, "ContainsKey=" + "COMPANIES");
//            } else if (jsonObject.has("UDDRILLRECORD")) {
//                Log.i(TAG, "ContainsKey=" + "UDDRILLRECORD");
//            } else if (jsonObject.has("CONTRACT")) {
//                Log.i(TAG, "ContainsKey=" + "CONTRACT");
//            } else if (jsonObject.has("PERSON")) {
//                Log.i(TAG, "ContainsKey=" + "PERSON");
//            } else if (jsonObject.has("CRAFT")) {
//                Log.i(TAG, "ContainsKey=" + "CRAFT");
//            } else {
//                showAlertDialog(resultString);
//                Log.i(TAG, "ContainsKey=" + "OTHER");
//            }
//
//        } else {
//            showAlertDialog(resultString);
//            Log.i(TAG, "无效的格式！！！");
//        }
//
//        Intent resultIntent = new Intent();
//        Bundle bundle = new Bundle();
//        bundle.putString("result", resultString);
//        resultIntent.putExtras(bundle);
//        getActivity();
//        getActivity().setResult(Activity.RESULT_OK, resultIntent);
//        // getActivity().finish();
    }

    private void showAlertDialog(String resultString) {
//        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
//                .create();
//        alertDialog.setCanceledOnTouchOutside(false);
//        alertDialog.show();
//        Window window = alertDialog.getWindow();
//        LayoutInflater inflater = getActivity().getLayoutInflater();
//        View layout = inflater.inflate(R.layout.dialog_normal_layout, null);
//        window.setContentView(layout);
//        ((TextView) layout.findViewById(R.id.title)).setText("无效数据");
//        ((TextView) layout.findViewById(R.id.message)).setText(resultString);
//        Button button = (Button) layout.findViewById(R.id.positiveButton);
//        button.setText("继续扫描");
//        ((Button) layout.findViewById(R.id.negativeButton))
//                .setVisibility(View.GONE);
//        button.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                handler.sendEmptyMessage(R.id.restart_preview);
//                alertDialog.dismiss();
//            }
//        });

    }

    /** 二维码其他数据数据提示 */
    private AlertDialog displayDialog(String resultString, int i) {
        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setIcon(android.R.drawable.ic_dialog_info)
                .setTitle("无效数据")
                .setMessage(resultString)
                .setPositiveButton("继续扫描",
                        new android.content.DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                handler.sendEmptyMessage(R.id.restart_preview);
                            }
                        }).create();
        dialog.setCanceledOnTouchOutside(false);
        return dialog;

    }

    /** 判断是否为有效的json格式数据 */
    public boolean isValidJson(String json) {
        if (json.equals("")) {
            Toast.makeText(view.getContext(), "扫描数据为空！！！", Toast.LENGTH_SHORT)
                    .show();
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
            return;
        } catch (RuntimeException e) {
            return;
        }
        if (handler == null) {
            handler = new CaptureActivityHandler(ScaningFragment.this);
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
            getActivity().setVolumeControlStream(AudioManager.STREAM_MUSIC);
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
            Vibrator vibrator = (Vibrator) getActivity().getSystemService(
                    Context.VIBRATOR_SERVICE);
            vibrator.vibrate(VIBRATE_DURATION);
        }
    }

    private final OnCompletionListener beepListener = new OnCompletionListener() {
        public void onCompletion(MediaPlayer mediaPlayer) {
            mediaPlayer.seekTo(0);
        }
    };

}
