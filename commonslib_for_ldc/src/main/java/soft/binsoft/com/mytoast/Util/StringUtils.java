package soft.binsoft.com.mytoast.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

public class StringUtils {
	private final static Pattern emailer = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
	//private final static SimpleDateFormat dateFormater = newcode SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//private final static SimpleDateFormat dateFormater2 = newcode SimpleDateFormat("yyyy-MM-dd");

	private final static ThreadLocal<SimpleDateFormat> dateFormater = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
		}
	};

	private final static ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd",Locale.CHINA);
		}
	};

	public static boolean isBlank(String str){
		boolean result = false;
		if(str==null || str.trim().length()==0 || str.equalsIgnoreCase("null")){
			result = true;
		}
		return result;
	}

	public static boolean isNotBlank(String str){
		boolean result = true;
		if(str==null || str.trim().length()==0 || str.equalsIgnoreCase("null")){
			result = false;
		}
		return result;
	}


	/**
	 * 将字符串转位日期类型
	 * @param sdate
	 * @return
	 */
	public static Date toDate(String sdate) {
		try {
			return dateFormater.get().parse(sdate);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 以友好的方式显示时间
	 * @param sdate
	 * @return
	 */
	public static String friendly_time(String sdate) {
		Date time = toDate(sdate);
		if(time == null) {
			return "Unknown";
		}
		String ftime = "";
		Calendar cal = Calendar.getInstance();

		//判断是否是同一天
		String curDate = dateFormater2.get().format(cal.getTime());
		String paramDate = dateFormater2.get().format(time);
		if(curDate.equals(paramDate)){
			int hour = (int)((cal.getTimeInMillis() - time.getTime())/3600000);
			if(hour == 0)
				ftime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000,1)+"分钟前";
			else
				ftime = hour+"小时前";
			return ftime;
		}

		long lt = time.getTime()/86400000;
		long ct = cal.getTimeInMillis()/86400000;
		int days = (int)(ct - lt);
		if(days == 0){
			int hour = (int)((cal.getTimeInMillis() - time.getTime())/3600000);
			if(hour == 0)
				ftime = Math.max((cal.getTimeInMillis() - time.getTime()) / 60000,1)+"分钟前";
			else
				ftime = hour+"小时前";
		}
		else if(days == 1){
			ftime = "昨天";
		}
		else if(days == 2){
			ftime = "前天";
		}
		else if(days > 2 && days <= 10){
			ftime = days+"天前";
		}
		else if(days > 10){
			ftime = dateFormater2.get().format(time);
		}
		return ftime;
	}

	/**
	 * 判断给定字符串时间是否为今日
	 * @param sdate
	 * @return boolean
	 */
	public static boolean isToday(String sdate){
		boolean b = false;
		Date time = toDate(sdate);
		Date today = new Date();
		if(time != null){
			String nowDate = dateFormater2.get().format(today);
			String timeDate = dateFormater2.get().format(time);
			if(nowDate.equals(timeDate)){
				b = true;
			}
		}
		return b;
	}

	/**
	 * 判断给定字符串是否空白串。
	 * 空白串是指由空格、制表符、回车符、换行符组成的字符串
	 * 若输入字符串为null或空字符串，返回true
	 * @param input
	 * @return boolean
	 */
	public static boolean isEmpty( String input )
	{
		if ( input == null || "".equals( input ) )
			return true;

		for ( int i = 0; i < input.length(); i++ )
		{
			char c = input.charAt( i );
			if ( c != ' ' && c != '\t' && c != '\r' && c != '\n' )
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断是不是一个合法的电子邮件地址
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email){
		if(email == null || email.trim().length()==0)
			return false;
		return emailer.matcher(email).matches();
	}
	/**
	 * 字符串转整数
	 * @param str
	 * @param defValue
	 * @return
	 */
	public static int toInt(String str, int defValue) {
		try{
			return Integer.parseInt(str);
		}catch(Exception e){}
		return defValue;
	}
	/**
	 * 对象转整数
	 * @param obj
	 * @return 转换异常返回 0
	 */
	public static int toInt(Object obj) {
		if(obj==null) return 0;
		return toInt(obj.toString(),0);
	}
	/**
	 * 对象转整数
	 * @param obj
	 * @return 转换异常返回 0
	 */
	public static long toLong(String obj) {
		try{
			return Long.parseLong(obj);
		}catch(Exception e){}
		return 0;
	}
	/**
	 * 字符串转布尔值
	 * @param b
	 * @return 转换异常返回 false
	 */
	public static boolean toBool(String b) {
		try{
			return Boolean.parseBoolean(b);
		}catch(Exception e){}
		return false;
	}

	public static String convertStreamToString(InputStream is, String encode) {
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, encode), 8 * 1024);
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			sb.delete(0, sb.length());
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				return null;
			}
		}
		return sb.toString();
	}
	/**
	 * 判断字符串是否为json格式
	 * @param str
	 * @return
	 */
	public static boolean isJson(String str){
		boolean result = false;
		if(StringUtils.isNotBlank(str)){
			if(str.trim().startsWith("{") && str.trim().endsWith("}")){
				result = true;
			}else if(str.trim().startsWith("[") && str.trim().endsWith("]")){
				result = true;
			}
		}
		return result;
	}

	/**
	 * 获取返回结果json内的success 及 msg
	 * @param str
	 * @return
	 */
	public static Map<String,Object> parseJsonResult(String str){
		Map<String,Object> result = new HashMap<String,Object>();
		boolean success = false;
		String msg = "数据提交失败";
		String params = "";
		try{
			if(isJson(str)){
				JSONObject obj = null;
				JSONArray array = null;
				try {
					obj = new JSONObject(str);
				} catch (JSONException e) {
					try {
						array = new JSONArray(str);
					} catch (JSONException e1) {
					}
				}
				if(obj!=null){
					if(obj.has("msg")){
						msg = obj.getString("msg");
					}else if(obj.has("messsage")){
						msg = obj.getString("messsage");
					}
					if(obj.has("success")){
						success = obj.getBoolean("success");
					}
					if(obj.has("params")){
						params = obj.getString("params");
					}
				}else if(array!=null){
					for(int i=0;i<array.length();i++){
						JSONObject jobj = array.getJSONObject(i);
						if(jobj!=null){
							if(jobj.has("msg")){
								msg = jobj.getString("msg");
							}else if(jobj.has("messsage")){
								msg = jobj.getString("messsage");
							}
							if(jobj.has("success")){
								success = jobj.getBoolean("success");
							}
							if(jobj.has("params")){
								params = jobj.getString("params");
							}
						}
					}
				}
			}else{
				msg = str;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		result.put("success",success);
		result.put("msg",msg);
		result.put("params",params);
		return result;
	}

	/**
	 * 32位 大写
	 * @param s
	 * @return
	 */
	public  static String MD5(String s) {
		char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
