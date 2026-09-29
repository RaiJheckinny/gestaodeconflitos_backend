package com.pipocaagil.feedback.controller;

import com.pipocaagil.feedback.occurrences.Occurrence;
import com.pipocaagil.feedback.occurrences.dto.RecoveryOccurrenceDto;
import com.pipocaagil.feedback.service.MediationService;
import com.pipocaagil.feedback.service.OccurrenceService;
import com.pipocaagil.feedback.users.dto.EmailUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mediation")
public class MediationController {

    @Autowired
    MediationService mediationService;

    @GetMapping("/perfil/occurrence/getUser")
    public ResponseEntity<List<Occurrence>> getOccurrenceRecent(EmailUserDTO emailUserDTO) {
        List<Occurrence> occurrences = mediationService.occurrencesDepartment(emailUserDTO);
        return new ResponseEntity<>(occurrences, HttpStatus.OK);
    }
}
