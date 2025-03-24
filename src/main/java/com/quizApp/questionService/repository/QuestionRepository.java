package com.quizApp.questionService.repository;


import com.quizApp.questionService.model.Questions;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Questions,Integer> {


    List<Questions> findByCategory(String category);
    List<Questions> findByCategoryAndLevel(String category, String level);

    @Query("SELECT q FROM Questions q WHERE q.category = :category ORDER BY function('RANDOM')")
    List<Questions> findRandomQuestionByCategory(@Param("category") String category, Pageable pageable);


}
