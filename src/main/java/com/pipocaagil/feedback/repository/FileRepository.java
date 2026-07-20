package com.pipocaagil.feedback.repository;

import com.pipocaagil.feedback.occurrences.File;
import com.pipocaagil.feedback.occurrences.Occurrence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}
