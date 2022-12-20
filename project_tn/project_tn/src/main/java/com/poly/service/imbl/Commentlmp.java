package com.poly.service.imbl;



import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;
import com.poly.entity.Feedback;
import com.poly.entity.Products;
import com.poly.entity.Users;
import com.poly.repository.ProductsRepo;
import com.poly.repository.UsersRepo;
import com.poly.service.FeedbackService;


@Repository
@Transactional
public class Commentlmp implements FeedbackService{
//	@Autowired(required=true)
//    private LocalSessionFactoryBean sessionFactoryBean;
//    @Autowired
//    private ProductsRepo  productsRepo;
//    @Autowired
//    private  UsersRepo  usersRepo;
//    @Autowired
//    private FeedbackService  feedbackService;
// 
//	@Override
//	public Feedback addComment(Feedback f) {
//		Session session=sessionFactoryBean.getObject().getCurrentSession();
//		try {
//			session.save(f);
//			return f;
//		} catch (HibernateException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	@Override
//	public Feedback addComment(String content, int productId) {
//		Products p=productsRepo.findById(productId).get();
//		Users u=usersRepo.findByusername(content);
//		Feedback feedback=new Feedback();
//		feedback.setContent(content);
//		feedback.setProduct(p);
//		feedback.setUsername(u);
//		return feedbackService.addComment(feedback);
//		
//	}

}
