package com.thinkpad.autolayout.utils;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: 校验工具类
 */
public class ValidUtil {

	/**
	 * @Description: 校验手机号码
	 * @return
	 */
	public static String validLXFS(String phone) {
		String message = "";
		if (TextUtil.stringIsNull(phone.replace(" ", ""))) {
			message = "请输入联系方式！";
		} else if (phone.length() < 11) {
			message = "请输入11位手机号码！";
		} else {
			String phoneRule = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$";
			Pattern p = Pattern.compile(phoneRule);
			Matcher match = p.matcher(phone);
			if (!match.matches()) {
				message = "请输入正确的联系方式！";
			}
		}
		return message;
	}

	public static String validPhone(String phone) {
		String message = "";
		if (TextUtil.stringIsNull(phone.replace(" ", ""))) {
			message = "啊哦~手机号不能为空";
		} else if (phone.length() != 11) {
			message = "啊哦~不是11位手机号手机号";
		} else {
			String phoneRule = "^((14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$";
			Pattern p = Pattern.compile(phoneRule);
			Matcher match = p.matcher(phone);
			if (!match.matches()) {
				message = "啊哦~不是正确的手机号";
			}
		}
		return message;
	}

	/**
	 * 
	 * @author lms
	 * @methods validIdentifyingCode
	 * @description 校验验证码
	 * @date 2015-4-30 下午2:53:56
	 * @param identifyingCodeStr
	 * @return 参数说明
	 */
	public static String validCheckCode(String identifyingCodeStr) {
		String message = "";
		if (TextUtil.stringIsNull(identifyingCodeStr.replace(" ", ""))) {
			message = "啊哦~验证码不能为空";
		} else if (identifyingCodeStr.length() < 6) {
			message = "啊哦~验证码错误";
		} else if (identifyingCodeStr.length() > 6) {
			message = "啊哦~验证码错误";
		}
		return message;
	}

	/**
	 * @Description: 校验密码
	 * @return
	 */
	public static String validPassword(String pwd) {
		String message = "";
		if (pwd.contains(" ")) {
			message = "密码是6到16位的数字、字母和下划线组成";
			return message;
		}
		if (TextUtil.stringIsNull(pwd)) {
			message = "密码是6到16位的数字、字母和下划线组成";
		} else if (pwd.length() < 6) {
			message = "密码是6到16位的数字、字母和下划线组成";
		} else if (pwd.length() > 16) {
			message = "密码是6到16位的数字、字母和下划线组成";
		} else {
			String rule = "[a-zA-Z0-9_]*$";
			Matcher match = Pattern.compile(rule).matcher(pwd);
			if (!match.matches()) {
				message = "密码是6到16位的数字、字母和下划线组成";
			} else {
				message = "";
			}
		}
		return message;
	}

	/**
	 * @Description: 校验邮箱
	 * @param email
	 * @return
	 */
	public static String validEmail(String email) {
		String message = "";
		String emailRule = "^\\w+@\\w+\\.\\w+$";
		Matcher match = Pattern.compile(emailRule).matcher(email);
		if (TextUtil.stringIsNull(email.replace(" ", ""))) {
			message = "请输入邮箱！";
		} else if (!match.matches()) {
			message = "请输入正确的邮箱！";
		}
		return message;
	}

	/**
	 * @Description: 校验姓名 中文，英文，中英文，中文少两个字符，纯英文 至少三个字符
	 * @param name
	 * @return
	 */
	public static String validName(String name) {
		String message = "";
		String englishNameRule = "^[A-Za-z]{3,}|[\u4e00-\u9fa5]{1,}[A-Za-z]+$";
		String chineseNameRule = "^[\u4e00-\u9fa5]{2,}$";
		Matcher mat = Pattern.compile(englishNameRule).matcher(name.replace(" ", ""));
		Matcher match = Pattern.compile(chineseNameRule).matcher(name.replace(" ", ""));
		if (TextUtil.stringIsNotNull(name)) {
			if (!mat.matches() && !match.matches()) {
				message = "请输入真实的联系人姓名！";
			} else if (match.matches() && name.length() > 30) {
				message = "联系人姓名最长30个汉字！";
			} else if (mat.matches() && name.length() > 60) {
				message = "联系人姓名最长60个字符！";
			}
		} else {
			message = "姓名不能为空";
		}
		return message;
	}

	/**
	 * @Description: 邮寄地址联系人姓名校验
	 * @param name
	 * @return
	 */
	public static String validPostName(String name) {
		String message = "";
		if (name.contains(" ")) {
			message = "收件人姓名中不能含有空格！";
			return message;
		}
		String englishNameRule = "[A-Za-z]{2,}|[\u4e00-\u9fa5]{1,}[A-Za-z]+$";
		String chineseNameRule = "^[\u4e00-\u9fa5]{2,}$";
		Matcher mat = Pattern.compile(englishNameRule).matcher(name.replace(" ", ""));
		Matcher match = Pattern.compile(chineseNameRule).matcher(name.replace(" ", ""));
		if (TextUtil.stringIsNotNull(name)) {
			if (!mat.matches() && !match.matches()) {
				message = "请输入正确的收件人姓名！";
			} else if (mat.matches() && !match.matches()) {
				if (name.replace("/", "").trim().length() < 3) {
					message = "英文字母位数至少为三位！";
				}
			}
		} else {
			message = "请输入姓名！";
		}
		return message;
	}

	/**
	 * @Description: 乘机人姓名校验
	 * @param name
	 * @return
	 */
	public static String validPassengersName(String name) {
		String message = "";
		if (name.contains(" ")) {
			message = "乘机人姓名中不能含有空格！";
			return message;
		}
		String chineseNameRule = "^[\u4e00-\u9fa5]{2,}$";
		String englishNameRule = "[A-Za-z]{2,}[/][A-Za-z]+$";
		Matcher mat = Pattern.compile(englishNameRule).matcher(name.replace(" ", ""));
		Matcher match = Pattern.compile(chineseNameRule).matcher(name.replace(" ", ""));
		if (TextUtil.stringIsNotNull(name)) {
			if (match.matches() || mat.matches()) {
				int count = 0;
				String regEx = "[\\u4e00-\\u9fa5]";
				Pattern p = Pattern.compile(regEx);
				Matcher m = p.matcher(name.replace(" ", ""));
				while (m.find()) {
					for (int i = 0; i <= m.groupCount(); i++) {
						count = count + 1;
					}
				}
				if (count > 11) {
					message = "乘机人姓名不能超过11个汉字！";
				}
			}
			if (!mat.matches() && !match.matches()) {
				message = "请输入正确的乘机人姓名！";
			} else if (mat.matches() && !match.matches()) {
				if (name.replace("/", "").length() < 3) {
					message = "英文字母位数至少为三位！";
				}
			}
		} else {
			message = "请填写乘机人姓名！";
		}
		return message;
	}

	public static String validUserIdCodeChild(String birthday) {
		String message = "";
		if (TextUtil.stringIsNull(birthday)) {
			message = "请选择出生日期！";
			return message;
		}
		return message;
	}

	/**
	 * 验证身份证是否是
	 * 
	 * @param userIdCode
	 * @return
	 */
	public static String validUserLong(String userIdCode) {
		String message = "";
		String subNumber = userIdCode.substring(6, 10);
		int parseInt = Integer.parseInt(subNumber);
		if (parseInt > 2001) {
			message = "儿童请选择儿童";
		}
		return message;
	}

	/**
	 * @author miaoxin.ye
	 * @createdate 2012-9-27 下午3:34:17
	 * @Description: 校验邮编
	 * @param postcode
	 * @return
	 */
	public static String validPostcode(String postcode) {
		String message = "";
		if (TextUtil.stringIsNull(postcode)) {
			message = "请输入邮政编码！";
		} else if (postcode.length() != 6) {
			message = "请输入6位邮政编码！";
		}
		return message;
	}

	/**
	 * @author ty
	 * @createdate 2012-10-12 下午4:47:40
	 * @Description: 校验地址
	 * @param address
	 * @return
	 */
	public static String validAddress(String address) {
		String message = "";
		String rule;
		if (TextUtil.stringIsNotNull(address)) {
			// 地址只能输入汉字、字母和数字和标点符号
			rule = "^[(A-Za-z0-9)*(\u4e00-\u9fa5)*(,|\\.|，|。|\\:|;|：|；|!|！|\\*|\\×|\\(|\\)|\\（|\\）|#|#|\\$|&|\\^|@|＠|＆|\\￥|\\……)*]+$";
			Matcher match = Pattern.compile(rule).matcher(address);
			if (!match.matches()) {
				message = "邮寄地址只能输入汉字、字母、数字和标点符号！";
			}
		} else {
			message = "请输入邮寄地址！";
		}
		return message;
	}

	public static String validOther(String other) {
		String message = "";
		String rule;
		if (TextUtil.stringIsNotNull(other.replace(" ", ""))) {
			// 其他证件只能输入数字、字母和"-"
			rule = "^[(A-Za-z0-9)]*+(-)*+[(A-Za-z0-9)]*+$";
			Matcher match = Pattern.compile(rule).matcher(other);
			if (!match.matches()) {
				message = "只能输入数字、字母和'-'！";
			}
		} else {
			message = "请输入证件号码！";
		}
		return message;
	}

	public static String validTrainNumber(String trainNumber) {
		String message = "";
		String rule;
		if (TextUtil.stringIsNotNull(trainNumber)) {
			// 其他证件只能输入数字、字母
			rule = "^[(A-Za-z0-9)]+$";
			Matcher match = Pattern.compile(rule).matcher(trainNumber);
			if (!match.matches()) {
				message = "只能输入字母或数字！";
			}
		} else {
			message = "请输入车次！";
		}
		return message;
	}

	/**
	 * 判断号码是联通，移动，电信中的哪个, 在使用本方法前，请先验证号码的合法性 规则：
	 * 
	 * 中国移动拥有号码段为:139,138,137,136,135,134,147,159,158,157(3G),151,152,150,182(3G
	 * ),188(3G),187(3G);16个号段
	 * 中国联通拥有号码段为:130,131,132,145,155,156(3G),186(3G),185(3G);8个号段
	 * 中国电信拥有号码段为:133,1349,153,189(3G),180(3G);5个号码段
	 * 
	 * @param mobile要判断的号码
	 * @return 返回相应类型：1代表联通；2代表移动；3代表电信
	 */
	public static String getMobileType(String mobile) {

		if (mobile.startsWith("0") || mobile.startsWith("+860")) {
			mobile = mobile.substring(mobile.indexOf("0") + 1, mobile.length());
		}
		List chinaUnicom = Arrays.asList(new String[] { "130", "131", "132", "145", "155", "156", "186", "185" });
		List chinaMobile1 = Arrays.asList(new String[] { "135", "136", "137", "138", "139", "147", "150", "151", "152", "157", "158",
				"159", "182", "187", "188" });
		List chinaMobile2 = Arrays.asList(new String[] { "1340", "1341", "1342", "1343", "1344", "1345", "1346", "1347", "1348" });

		boolean bolChinaUnicom = (chinaUnicom.contains(mobile.substring(0, 3)));
		boolean bolChinaMobile1 = (chinaMobile1.contains(mobile.substring(0, 3)));
		boolean bolChinaMobile2 = (chinaMobile2.contains(mobile.substring(0, 4)));
		if (bolChinaUnicom)
			return "1";// 联通
		if (bolChinaMobile1 || bolChinaMobile2)
			return "2"; // 移动
		return "3"; // 其他为电信

	}

	public static String validNickname(String string) {
		String message = "";
		if (TextUtil.stringIsNull(string)) {
			message = "昵称不能为空!";
			return message;
		}
		if (string.length() < 1 && string.length() > 12) {
			message = "昵称为1~12位!";
			return message;
		}
		return "";
	}

	public static String validRealName(String name) {
		String message = "";
		String englishNameRule = "^[A-Za-z]{3,}|[\u4e00-\u9fa5]{1,}[A-Za-z]+$";
		String chineseNameRule = "^[\u4e00-\u9fa5]{2,}$";
		Matcher mat = Pattern.compile(englishNameRule).matcher(name.replace(" ", ""));
		Matcher match = Pattern.compile(chineseNameRule).matcher(name.replace(" ", ""));
		if (TextUtil.stringIsNotNull(name)) {
			if (!mat.matches() && !match.matches()) {
				message = "请输入真实的姓名！";
			} else if (match.matches() && name.length() > 6) {
				message = "姓名最长6个汉字！";
			} else if (mat.matches() && name.length() > 12) {
				message = "姓名最长12个字符！";
			}
		} else {
			message = "姓名不能为空";
		}
		return message;
	}

	public static String validMySynopsis(String mySynopsis) {
		String message = "";
		if (TextUtil.stringIsNull(mySynopsis)) {
			message = "个人简介不能为空!";
			return message;
		}
		return "";
	}

	public static String validCity(String nickname) {
		String message = "";
		if (TextUtil.stringIsNull(nickname)) {
			message = "城市不能为空!";
			return message;
		}
		return "";
	}

	/**
	 * 
	 * @author limeishu
	 * @createdate 2014-8-1 上午11:45:24
	 * @Description: (校验网址)
	 * @param url
	 * @return
	 * @throws UnsupportedEncodingException
	 * 
	 */
	public static String validUrl(String url) {
		String message = "";
		if (url.contains(" ")) {
			message = "网址无效,网址不能有空格";
			return message;
		}
		String urlRule = "(HTTP|http)s?://[^,， ]+";
		if (TextUtil.stringIsNull(url)) {
			message = "网址信息丢失";
			return message;
		}
		Matcher match = Pattern.compile(urlRule).matcher(url);
		if (!match.matches()) {
			message = "网址无效";
		} else {
			message = "";
		}
		return message;
	}
}
