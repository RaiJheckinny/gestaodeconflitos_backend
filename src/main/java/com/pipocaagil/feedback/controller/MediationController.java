package com.pipocaagil.feedback.controller;

import com.pipocaagil.feedback.occurrences.Occurrence;
import com.pipocaagil.feedback.occurrences.dto.OccurrenceUUIDDTO;
import com.pipocaagil.feedback.service.MediationService;
import com.pipocaagil.feedback.users.dto.EmailUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/perfil/occurrence/getAllOccurrence")
    public ResponseEntity<List<Occurrence>> getOccurrenceRecent() {
        List<Occurrence> occurrences = mediationService.occurrencesAll();
        return new ResponseEntity<>(occurrences, HttpStatus.OK);
    }

    @PostMapping("/perfil/upateOcurrenceAtivo")
    public ResponseEntity<Void> upateOcurrenceAtivo(@RequestBody OccurrenceUUIDDTO id) {
        mediationService.upateOcurrenceAtivo(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
