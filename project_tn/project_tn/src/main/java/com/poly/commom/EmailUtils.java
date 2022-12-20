package com.poly.commom;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.poly.entity.Email;

public class EmailUtils {
	//Gửi thông tin email đi
	public static void send(Email email) throws Exception {
		//Cấu hình thông qua class Properties
		Properties pror=new Properties();
		// Địa chỉ host
		pror.put("mail.smtp.host", "smtp.gmail.com");
		//Số hiệu tổng kết nối với host
		pror.put("mail.smtp.port", "587");
		//Bật chế độ xác thực
		pror.put("mail.smtp.auth", "true");
		//Các thông tin cấu hình khác
		pror.put("mail.smtp.starttls.enable", "true");
		//Tạo ra thể hiện của Session và sử dụng chế độ xác thực truyền vào email và pass của email
		Session session=Session.getInstance(pror, new Authenticator() {
		    protected PasswordAuthentication getPasswordAuthentication() {
		    	return new PasswordAuthentication(email.getFrom(), email.getFromPassword());
		    }
		});
		try {
			//Để có thể gửi email thì tạo ra đối tượng session 
			Message message=new MimeMessage(session);
			//Sau đó thiết lập các thông số 
			//Địa chỉ mail gửi đi
			message.setFrom(new InternetAddress(email.getFrom()));
			//Người nhận mail
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(email.getTo()));
			//Thiết lập tiêu đề mail
			message.setSubject(email.getSubject());
			//Thiết lập nội dung mail(có thiết lập nội dung và định dạnh mail)
			message.setContent(email.getContent(),"text/html; charset=utf-8");
			//Cuối cùng thực hiện gửi mail
			Transport.send(message);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
