package wsy.userLogin.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityPassword {
	  /*加密*/
		public static String String2Md5(String str) throws NoSuchAlgorithmException{
				MessageDigest m = MessageDigest.getInstance("MD5");
				str = new BigInteger(1,m.digest(str.getBytes())).toString(32);
				str = new String(str.getBytes());
				return str;
		}
}
