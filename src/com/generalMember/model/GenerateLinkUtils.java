package com.generalMember.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletRequest;

public class GenerateLinkUtils {
	private static final String CHECK_CODE ="checkCode";
	
	
	public static String generateResetPwdLink(GeneralMemberVO gmVO) {
		return "http://localhost:8081/GeneralMember/restPasswordUI?accout="+gmVO.getAccount() + "&" +CHECK_CODE +"="+generateCheckcode(gmVO);
	}
	
	public static String generateCheckcode(GeneralMemberVO gmVO) {
		String account = gmVO.getAccount();
		String randomCode = gmVO.getRandomCode();
		return md5(account +":"+randomCode);
	}
	
	public static boolean verifyCheckcode(GeneralMemberVO gmVO,ServletRequest request) {
		String checkCode = request.getParameter(CHECK_CODE);
		return generateCheckcode(gmVO).equals(checkCode);
	}
	
	private static String md5(String string) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("md5");
			md.update(string.getBytes());
			byte[] md5Bytes = md.digest();
			return byte2Hex(md5Bytes);
			
		} catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static String byte2Hex(byte[] byteArray) {
		StringBuffer strBuf = new StringBuffer();
		for(int i = 0; i<byteArray.length;i++) {
			if(byteArray[i] >=0 && byteArray[i] <16)
			{
				strBuf.append("0");
			}
			strBuf.append(Integer.toHexString(byteArray[i] & 0xFF));
		}
		return strBuf.toString();
	}
}
