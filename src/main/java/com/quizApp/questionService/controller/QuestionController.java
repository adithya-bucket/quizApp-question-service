package com.quizApp.questionService.controller;


import com.quizApp.questionService.model.Questions;
import com.quizApp.questionService.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public List<Questions> getAllQuestion() {
        return questionService.getAllQuestion();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Questions>> getCategoryBasedQuestionAndLevel(@PathVariable String category, @RequestParam(required = false) Optional<String> levels) {
        List<Questions> questions = levels.map(level -> questionService.getCategoryBasedQuestionAndLevel(category, level))
                .orElseGet(() -> questionService.getCategoryBasedQuestion(category));
        if (questions.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(questions);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Questions questions) {
        questionService.addQuestion(questions);
        return ResponseEntity.status(HttpStatus.CREATED).body("Created Successfully");
    }

    @PutMapping("update/{id}")
    public String updateQuestion(@RequestBody Questions updatedquestions) {
        return questionService.updateQuestion(updatedquestions);
    }

    @DeleteMapping("delete/{id}")
    public String deleteQuestion(@PathVariable int id) {
        return questionService.deleteQuestion(id);
    }
}
