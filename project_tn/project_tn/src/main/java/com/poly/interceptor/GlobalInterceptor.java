package com.poly.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.poly.service.CategoriesService;


@Component
public class GlobalInterceptor implements HandlerInterceptor {
	//Không thể viết trong controller tại vì các controller đều sử dụng layout nên phải viết trong interceptor
	@Autowired
	CategoriesService service;
	//Sau khi thực hiện trong controller postHandle ms chạy
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		//lấy tất các loại đặt tên cates
		request.setAttribute("cates", service.findAll());
	}

}
