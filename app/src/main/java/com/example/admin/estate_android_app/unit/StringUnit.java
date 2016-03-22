package com.example.admin.estate_android_app.unit;

/**
 * 
 * @ClassName: StringUnit
 * @Description: TODO字符串单元类
 * @author king
 * @date 2014年5月6日 下午5:22:31
 */
public class StringUnit {
	/** 去掉首尾字符 **/
	public static String trimFirstAndLastChar(String source, char start,
			char end) {
		boolean beginIndexFlag = true;
		boolean endIndexFlag = true;
		do {
			int beginIndex = source.indexOf(start) == 0 ? 1 : 0;
			int endIndex = source.lastIndexOf(end) + 1 == source.length() ? source
					.lastIndexOf(end) : source.length();
			source = source.substring(beginIndex, endIndex);
			beginIndexFlag = (source.indexOf(start) == 0);
			endIndexFlag = (source.lastIndexOf(end) + 1 == source.length());
		} while (beginIndexFlag || endIndexFlag);
		return source;
	}
}
