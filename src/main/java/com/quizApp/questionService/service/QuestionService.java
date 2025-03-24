package com.quizApp.questionService.service;


import com.quizApp.questionService.model.Questions;
import com.quizApp.questionService.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;
    public List<Questions> getAllQuestion() {
            return questionRepository.findAll(Sort.by("id"));
    }

    public List<Questions> getCategoryBasedQuestion(String category) {
            return questionRepository.findByCategory(category);

    }

    public void addQuestion(Questions questions) {
        questionRepository.save(questions);
    }

    public List<Questions> getCategoryBasedQuestionAndLevel(String category, String level) {
        return questionRepository.findByCategoryAndLevel(category, level);
    }

    public String updateQuestion(Questions updatedquestions) {
        questionRepository.save(updatedquestions);
        return  "updated successfully";
    }

    public String deleteQuestion(int id) {
        questionRepository.deleteById(id);
        return "delete successfully";
    }
}
