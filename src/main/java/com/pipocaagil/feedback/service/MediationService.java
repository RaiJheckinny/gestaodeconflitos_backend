package com.pipocaagil.feedback.service;

import com.pipocaagil.feedback.occurrences.Occurrence;
import com.pipocaagil.feedback.occurrences.dto.DateStatus;
import com.pipocaagil.feedback.occurrences.dto.DateStatusName;
import com.pipocaagil.feedback.repository.FileRepository;
import com.pipocaagil.feedback.repository.OccurrenceRepository;
import com.pipocaagil.feedback.repository.UserRepository;
import com.pipocaagil.feedback.users.User;
import com.pipocaagil.feedback.users.dto.EmailUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class MediationService {

    @Autowired
    private OccurrenceRepository occurrenceRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OccurrenceService occurrenceService;

    @Autowired
    private FileRepository fileRepository;

    public List<Occurrence> occurrencesDepartment(EmailUserDTO emailDto) {
        return userRepository.findByEmail(emailDto.email()).orElseThrow(() -> new RuntimeException("User Nao Encontrado")).getList_mediation();
    }

    public List<User> userDepartment(String department){
        return userRepository.findByDepartment(department);
    }
}
