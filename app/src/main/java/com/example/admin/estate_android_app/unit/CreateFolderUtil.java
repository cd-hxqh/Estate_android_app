package com.example.admin.estate_android_app.unit;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import android.os.Environment;
import android.util.Log;

public class CreateFolderUtil {

	/**
	 * 检测SD卡是否存在 存在则在SD卡上创建文件夹资源
	 * 
	 * @return
	 */
	public static String getSDPath(String folderName) {
		String path = "";
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			File sdPath = Environment.getExternalStorageDirectory();
			path = sdPath.getPath() + File.separator + folderName;
			File file = new File(path);
			if (!file.exists()) {
				boolean result = file.mkdirs();
				if (result) {
				} else {
				}
			}
		} else {

		}
		return path;
	}

	/**
	 * 新建文件
	 * 
	 * @param filePathAndName
	 *            文件路径及名称,如D:/Test.txt
	 * @param fileContent
	 *            文件内容
	 */
	public void newFile(String filePathAndName, String fileContent) {
		try {
			String filePath = filePathAndName;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			if (!myFilePath.exists()) {
				myFilePath.createNewFile();
			}
			FileWriter resultFile = new FileWriter(myFilePath);
			PrintWriter myFile = new PrintWriter(resultFile);
			String strContent = fileContent;
			myFile.println(strContent);
			resultFile.close();
		} catch (Exception e) {
			System.out.println("新建文件操作出错");
			e.printStackTrace();
		}
	}

	public static void newFile(String filePathAndName) {
		try {
			File myFilePath = new File(filePathAndName);
			if (!myFilePath.exists()) {
				myFilePath.createNewFile();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 删除文件夹
	// param folderPath 文件夹完整绝对路径

	public static boolean delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			myFilePath.delete(); // 删除空文件夹
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 删除指定文件夹下所有文件
	// param path 文件夹完整绝对路径
	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}

}
