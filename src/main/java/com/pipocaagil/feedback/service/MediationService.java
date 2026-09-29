package com.pipocaagil.feedback.service;

import com.pipocaagil.feedback.occurrences.Occurrence;
import com.pipocaagil.feedback.repository.FileRepository;
import com.pipocaagil.feedback.repository.OccurrenceRepository;
import com.pipocaagil.feedback.repository.UserRepository;
import com.pipocaagil.feedback.users.User;
import com.pipocaagil.feedback.users.dto.EmailUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MediationService {

    @Autowired
    private OccurrenceRepository occurrenceRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FileRepository fileRepository;

    public List<Occurrence> occurrencesDepartment(EmailUserDTO emailDto){

        User user = userRepository.findByEmail(emailDto.email()).orElseThrow(() -> new RuntimeException("User nao encotrado"));
        List<User> users = userDepartment(user.getDepartment());
        return occurrenceRepository.findByUserInAndStatusNot(users,"Rascunho");
    }

    public List<User> userDepartment(String department){
        return userRepository.findByDepartment(department);
    }
}
