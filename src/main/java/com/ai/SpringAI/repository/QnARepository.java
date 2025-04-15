package com.ai.SpringAI.repository;

import com.ai.SpringAI.entity.QnA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QnARepository extends JpaRepository<QnA, Long> {
    List<QnA> findByCategoryName(String name);
}
