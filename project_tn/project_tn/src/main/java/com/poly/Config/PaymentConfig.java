package com.poly.Config;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
public class PaymentConfig {

	public static final String vnp_Version ="2.0.1";
	public static final String vnp_Command="2.0.2";
	public static final String vnp_TmnCode="BX5ON4E6";
	public static final String vnp_CurrCode="VND";
	public static final String vnp_IpAddr="13.160.92.202";
	public static final String vnp_Locale="vn";
	public static final String vnp_OrderInfo="Thanh toan dơn hang";
	public static final String vnp_OrderType="110000";
	public static final String vnp_HashSecret="NRMPDZLWAQNKMSCZXQDMLLBGXJYJMEUI";
	public static final String vnp_PayUrl="https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
	public static final String vnp_Returnurl="http://localhost:8080/oneshop/pay";
	public static final String vnp_ReturnCreateUrl="http://localhost:8080/oneshop/pay";
	
	

	
	
}