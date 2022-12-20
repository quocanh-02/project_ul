package com.poly.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.commom.Untility;
import com.poly.entity.Users;
import com.poly.service.SessionService;
import com.poly.service.UsersService;
import com.poly.service.imbl.UsersNotFoundException;
import net.bytebuddy.utility.RandomString;

@Controller
public class ForgotPasswordController {
	@Autowired
    private JavaMailSender mailSender;
     

    @Autowired
    UsersService service;
    @Autowired
 
     
    @GetMapping("/forgot_password")
    public String showForgotPasswordForm() {
        return "user_html/forgotpassword";
    }
 
    
    @PostMapping("/forgot_password")
    public String processForgotPassword(HttpServletRequest request, Model model) throws UsersNotFoundException  {
        String email = request.getParameter("email");
        String token = RandomString.make(30);
         
        try {
        	service.updateResetPass(token, email);
            String resetPasswordLink = Untility.getSiteURL(request) + "/reset_password?token=" + token;
            sendEmail(email, resetPasswordLink);
            model.addAttribute("message", "Chúng tôi đã gửi một liên kết đặt lại mật khẩu đến email của bạn. Vui l kiểm tra email");
             
        } catch (UsersNotFoundException ex) {
            model.addAttribute("error", ex.getMessage());
        } catch (UnsupportedEncodingException | MessagingException e) {
            model.addAttribute("error", "Error while sending email");
        }
             
        return "user_html/forgotpassword";
    }
    
    @GetMapping("/reset_password")
    public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
        Users customer = service.get(token);
        model.addAttribute("token", token);
         
        if (customer == null) {
            model.addAttribute("message", "Invalid Token");
            return "user_html/product";
        }
         
        return "user_html/changepassword";
    }
    
    @PostMapping("/reset_password")
    public String processResetPassword(HttpServletRequest request, Model model,  HttpSession session) throws UsersNotFoundException {
        String token = request.getParameter("token");
        String password = request.getParameter("password");
         
        Users customer = service.get(token);
        model.addAttribute("title", "Reset your password");
       
        if (customer == null) {
            model.addAttribute("message", "Invalid Token");
            return "user_html/changepassword";
        } else {           
        	service.updatePassword(customer, password);
            model.addAttribute("message", "You have successfully changed your password.");
            session.setAttribute("currentUser",customer);
        }
       
        return "redirect:/oneshop/index";
    }
//    @PostMapping("/forgot_password")
//    public String processForgotPassword() {
//    }
//     
//    public void sendEmail(){
// 
//    }  
//     
//     
//    @GetMapping("/reset_password")
//    public String showResetPasswordForm() {
// 
//    }
//     
//    @PostMapping("/reset_password")
//    public String processResetPassword() {
// 
//    }

    public void sendEmail(String recipientEmail, String link)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();              
        MimeMessageHelper helper = new MimeMessageHelper(message);
         
        helper.setFrom("uyendao2310", "Đổi mật khẩu");
        helper.setTo(recipientEmail);
         
        String subject = "Here's the link to reset your password";
         
        String content = "<p>Xin chào ,</p>"
                + "<p>Bạn đã gửi yêu cầu đổi mật khẩu.</p>"
                + "<p>Nhấp vào link bên dưới để thay đổi mật khẩu:</p>"
                + "<p><a href=\"" + link + "\">Đổi mật khẩu</a></p>"
                + "<br>"
                + "<p>Bỏ qua email này nếu bạn nhớ mật khẩu của mình hoặc bạn chưa thực hiện yêu cầu "
           ;
         
        helper.setSubject(subject);
         
        helper.setText(content, true);
         
        mailSender.send(message);
    }
}
