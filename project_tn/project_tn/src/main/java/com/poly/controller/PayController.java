package com.poly.controller;

import java.io.IOException;
import java.io.ObjectInputFilter.Config;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.unit.DataUnit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poly.Config.PaymentConfig;
import com.poly.dto.CartDto;
import com.poly.dto.PaymentDto;
import com.poly.dto.PaymentResDto;
import com.poly.entity.OrderDetails;
import com.poly.entity.Orders;
import com.poly.entity.Users;
import com.poly.service.OrderService;
import com.poly.util.ConfigUtil;
import com.poly.util.SessionUtil;

@RestController
public class PayController {
	

	@PostMapping("/oneshop/vnpay_payment")
		public ResponseEntity<?> doPostVNPay(@RequestBody PaymentDto requetParam) throws UnsupportedEncodingException {
			
		int amount = requetParam.getAmount() * 100;
		
		Map<String, String> vnp_Params = new HashMap<>();
		
		vnp_Params.put("vnp_Version", PaymentConfig.vnp_Version);
        vnp_Params.put("vnp_Command", PaymentConfig.vnp_Command);
        vnp_Params.put("vnp_TmnCode", PaymentConfig.vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_BankCode", requetParam.getBankCode());
       
        vnp_Params.put("vnp_TxnRef", "123123");
        vnp_Params.put("vnp_OrderInfo", requetParam.getDecription());
        vnp_Params.put("vnp_OrderType", PaymentConfig.vnp_OrderType);
        
        vnp_Params.put("vnp_Locale", PaymentConfig.vnp_Locale);
        vnp_Params.put("vnp_ReturnUrle", PaymentConfig.vnp_Returnurl);
        vnp_Params.put("vnp_IpAddr", PaymentConfig.vnp_IpAddr);
        
        Date dt = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(dt);
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
        
        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        
        Iterator itr = fieldNames.iterator();
        
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(fieldValue);
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        
        String queryUrl = query.toString();
        String vnp_SecureHash = ConfigUtil.hmacSHA512(PaymentConfig.vnp_HashSecret, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = PaymentConfig .vnp_PayUrl + "?" + queryUrl;
        
       PaymentResDto result = new PaymentResDto();
       result.setStatus("00");
       result.setMessage("success");
       result.setUrl(paymentUrl);
       
			return ResponseEntity.status(HttpStatus.OK).body(result);
		}
	}
	
	

