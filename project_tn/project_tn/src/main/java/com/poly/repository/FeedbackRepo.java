package com.poly.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poly.entity.Feedback;


@Repository
@Transactional
public interface FeedbackRepo extends JpaRepository<Feedback, Integer>
{

}
