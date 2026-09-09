package com.telusko.quizapp.Service;

import com.telusko.quizapp.Entity.Question;
import com.telusko.quizapp.Entity.QuestionWrapper;
import com.telusko.quizapp.Entity.Quiz;
import com.telusko.quizapp.Repostries.QuestionRepostries;
import com.telusko.quizapp.Repostries.QuizRepostries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    QuestionRepostries questionRepostries;
    @Autowired
    QuizRepostries quizRepostries;


    @Override
    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {


        List<Question> questions = questionRepostries.findRandomQuestionsByCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizRepostries.save(quiz);

        return new ResponseEntity<>("Quiz created successfully", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer id) {
        Optional<Quiz> quiz = quizRepostries.findById(id);
        List<Question> questionFromDb = quiz.get().getQuestions();
        List<QuestionWrapper> questionToUser = new ArrayList<>();
        for(Question q : questionFromDb){
            QuestionWrapper qw = new QuestionWrapper(
                    q.getId(),
                    q.getQuestionTitle(),
                    q.getOption1(),
                    q.getOption2(),
                    q.getOption3(),
                    q.getOption4()
            );
            questionToUser.add(qw);
        }

        return  new ResponseEntity<>(questionToUser,HttpStatus.OK);
    }
}
