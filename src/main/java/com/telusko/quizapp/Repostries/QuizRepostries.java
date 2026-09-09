package com.telusko.quizapp.Repostries;

import com.telusko.quizapp.Entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepostries extends JpaRepository<Quiz,Integer> {
}
