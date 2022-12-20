package com.poly.controller.rest;


import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.poly.entity.Feedback;
import com.poly.service.FeedbackService;

//@CrossOrigin("*")
//@RestController
public class FeedbackRestController {
//	@Autowired
//	FeedbackService feedbackService;
//    
//	
//	@PostMapping(path="/api/add-comment", produces= {
//			MediaType.APPLICATION_JSON_VALUE
//	})
//	public ResponseEntity<Feedback> addComment(@RequestBody Map<String, String> params){
//		
//			String content=params.get("content");
//			int productId=Integer.parseInt(params.get("productId"));
//			Feedback feedback=feedbackService.addComment(content, productId);
//			//return new ResponseEntity<>(feedback,HttpStatus.CREATED);
//		
//		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//	}

}
