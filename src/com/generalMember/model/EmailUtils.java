package com.generalMember.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;



import sun.rmi.transport.Transport;

public class EmailUtils {
	private static final String FROM = "awye.chou@gmail.com";
	
	
	
	//重設密碼
	public static void sendResetPasswordEmail(GeneralMemberVO gmpVO) {
		Session session = getSession();
		MimeMessge message = new MimeMessage(session);
		try {
			messge.setSubject("找回您的帳號與密碼");
			message.setSentDate(new Date());
			message.setFrom(new InternetAddress(FROM));
			message.setRecipient(RecipientType.TO,new InternetAddress(gmpVO.getEmail()));
			message.setContent("要使用新的密碼,請使用以下連結啟用密碼<br/><a href=""+ GenerateLinkUtils.generateResetPwdLink() +"">點擊重新設定密碼</a>","text/html;charset=utf-8");
			//發送郵件
			Transport.send(message);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Session getSession() {
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol","smtp");
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.port","587");
		props.setProperty("mail.smtp.auth","true");
		Session session = Session.getInstance(props,new Authenticator()) {
			protected PasswordAuthentication getPasswordAuthentication() {
				String password = null;
				InputStream is = EmailUtils.class.getResourceAsStream("password.dat");
				byte[] b = new byte[1024];
				try {
					int len = is.read(b);
					password = new String(b,0,len);
				} catch(IOException e) {
					e.printStackTrace();
				}
				return new PasswordAuthentication(FROM,password);
			}
		});
		return session;
		
	}
}
