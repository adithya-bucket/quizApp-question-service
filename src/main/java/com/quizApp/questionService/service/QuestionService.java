package com.quizApp.questionService.service;


import com.quizApp.questionService.DTO.QuestionDTO;
import com.quizApp.questionService.model.Questions;
import com.quizApp.questionService.model.Response;
import com.quizApp.questionService.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<Integer> getQuestionForQuiz(String category, Integer numofQue) {
        return questionRepository.findRandomQuestionByCategory(category, PageRequest.of(0,numofQue));
    }

    public List<QuestionDTO> getQuestionFromId(List<Integer> id) {
        List<QuestionDTO> questionDto =new ArrayList<>();
        List<Questions> questions =new ArrayList<>();
        for(Integer qId :id) {
           questions.add(questionRepository.findById(qId).get());

        }
        for(Questions q:questions){
            QuestionDTO qDTO = new QuestionDTO(q.getId(),q.getLevel(),q.getQuestion(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionDto.add(qDTO);
        }
        return questionDto;
    }

    public Integer getScore(List<Response> response) {
        int score =0;
        for(Response responses:response){
            Questions question =questionRepository.findById(responses.getId()).get();
            if(responses.getAnswer().equals(question.getAnswer()))
                score++;
        }
        return score;
    }
}
