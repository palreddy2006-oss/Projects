package com.telusko.quizapp.Service;

import com.telusko.quizapp.Entity.Question;
import com.telusko.quizapp.Repostries.QuestionRepostries;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class questionServiceImpl implements QuestionService {

    private final QuestionRepostries questionRepostries;

    // Constructor injection: Spring automatically injects the bean here
    public questionServiceImpl(QuestionRepostries questionRepostries) {
        this.questionRepostries = questionRepostries;
    }
    @Override
    public ResponseEntity<List<Question>> getAllQuestion() {
        try {
            List<Question> questions = questionRepostries.findAll();
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<Question>> getAllQuestionByCategory(String category) {
        try {
            List<Question> questions = questionRepostries.getAllQuestionByCategory(category);
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<String> insertQuestion(Question question) {
        try {
            questionRepostries.save(question);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to add question: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @Override
    public ResponseEntity<String> updateQuestion(Integer id, Question updatedQuestion) {
        try {
            // Check if the record exists in MySQL
            if (!questionRepostries.existsById(id)) {
                return new ResponseEntity<>("Question with ID " + id + " not found", HttpStatus.NOT_FOUND);
            }

            // Set the ID so Hibernate knows to execute an UPDATE instead of an INSERT
            updatedQuestion.setId(id);
            questionRepostries.save(updatedQuestion);

            return new ResponseEntity<>("Updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating question: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<String> deleteQuestion(Integer id) {
        try {
            if (!questionRepostries.existsById(id)) {
                return new ResponseEntity<>("Question with ID " + id + " not found", HttpStatus.NOT_FOUND);
            }

            questionRepostries.deleteById(id);
            return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting question: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}