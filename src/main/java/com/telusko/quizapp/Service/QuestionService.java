package com.telusko.quizapp.Service;

import com.telusko.quizapp.Entity.Question;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuestionService {
    ResponseEntity<List<Question>> getAllQuestion();

    ResponseEntity<List<Question>> getAllQuestionByCategory(String category);

    ResponseEntity<String> insertQuestion(Question question);
    ResponseEntity<String> updateQuestion(Integer id, Question question);
    ResponseEntity<String> deleteQuestion(Integer id);
}