package com.telusko.quizapp.Service;

import com.telusko.quizapp.Entity.QuestionWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface QuizService {
     ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String Title);

    ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer id);
}
