package com.pipocaagil.feedback.repository;

import com.pipocaagil.feedback.occurrences.Occurrence;
import com.pipocaagil.feedback.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OccurrenceRepository extends JpaRepository<Occurrence, Long> {
    List<Occurrence> findByUserEmailOrderByDateNowDesc(String email);
    Occurrence findFirstByUserEmailOrderByDateNowDesc(String email);
}
