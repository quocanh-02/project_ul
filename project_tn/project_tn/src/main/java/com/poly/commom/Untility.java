package com.poly.commom;

import javax.servlet.http.HttpServletRequest;

public class Untility {
	public static String getSiteURL(HttpServletRequest request) {
		String siteURL= request.getRequestURL().toString();
		return siteURL.replace(request.getServletPath(), "");
	}

}
