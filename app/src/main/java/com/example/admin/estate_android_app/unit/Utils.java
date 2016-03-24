package com.example.admin.estate_android_app.unit;

import java.io.File;

import android.content.Context;
import android.os.Environment;

import com.example.admin.estate_android_app.constant.Constant;


/**
 * 
 * @ClassName: Utils
 * @Description: TODO单元工具类
 * @author king
 * @date 2014年4月22日 上午9:41:11
 */
public class Utils {

	/**
	 * 
	 * @Title: isDBFileExist
	 * @Description: TODO判断数据库文件是否存在
	 * @param @param context
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 */
	public static boolean isDBFileExist(Context context) {
		boolean isSdCard = isSdCard();
		String path = null;
		if (isSdCard) {
			path = Constant.PATH_DB + context.getPackageName() + File.separator;
		} else {
			path = Constant.NOT_SDCARD_PATH_DB + context.getPackageName() + File.separator;
		}
		String DB_NAME = path + "DB.db";
		File file = new File(DB_NAME);
		long length = file.length();
		if (file.exists()) {

			if (length < 10240) {
				file.delete();
				return false;
			}

			return true;
		}

		else {
			return false;
		}
	}

	/**
	 * 
	 * @Title: getFilePath
	 * @Description: TODO获取文件路径
	 * @param @param context
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String getFilePath(Context context) {
		boolean isSdCard = isSdCard();
		String path = null;
		if (isSdCard) {
			path = Constant.PATH_DB + context.getPackageName() + File.separator;
		} else {
			path = Constant.NOT_SDCARD_PATH_DB + context.getPackageName() + File.separator;
		}
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		String DB_NAME = path + "DB.db";
		return DB_NAME;
	}

	// 首先判断sdcard是否插入
	public static boolean isSdCard() {
		String status = Environment.getExternalStorageState();
		if (status.equals(Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}

}
