package com.pipocaagil.feedback.repository;

import com.pipocaagil.feedback.occurrences.Occurrence;
import com.pipocaagil.feedback.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OccurrenceRepository extends JpaRepository<Occurrence, UUID> {
    List<Occurrence> findByUserEmailOrderByStatusDateDesc(String email);
    Optional<Occurrence> findFirstByUserEmailOrderByStatusDateDesc(String email);
    Optional<Occurrence> findByProtocol(UUID protocol);
    List<Occurrence> findByUserInAndStatusNot(List<User> users, String status);
    List<Occurrence> findByUserIn(List<User> users);
}
