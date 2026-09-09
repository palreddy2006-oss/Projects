package com.telusko.quizapp.Controller;

import com.telusko.quizapp.Entity.Question;
import com.telusko.quizapp.Entity.QuestionWrapper;
import com.telusko.quizapp.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz") // Changed to lowercase "quiz"
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category,
                                             @RequestParam int numQ,
                                             @RequestParam String title) { // Changed to lowercase "title"
        return quizService.createQuiz(category, numQ, title);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(@PathVariable Integer id)  { // Changed to lowercase "title"
    return quizService.getQuizQuestion(id);
    }



}