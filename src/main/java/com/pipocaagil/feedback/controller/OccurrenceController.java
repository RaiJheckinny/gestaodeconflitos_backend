package com.pipocaagil.feedback.controller;

import com.pipocaagil.feedback.occurrences.dto.CreateOccurrenceDto;
import com.pipocaagil.feedback.occurrences.dto.RecoveryOccurrenceDto;
import com.pipocaagil.feedback.service.OccurrenceService;
import com.pipocaagil.feedback.users.dto.CreateUserDto;
import com.pipocaagil.feedback.users.dto.EmailUserDTO;
import com.pipocaagil.feedback.users.dto.RecoveryUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class OccurrenceController {

    @Autowired
    OccurrenceService occurrenceService;

    @PostMapping("/perfil/occurrence/create")
    public ResponseEntity<Void> createUser(@RequestBody CreateOccurrenceDto createOccurrence) {
        occurrenceService.createOccurrence(createOccurrence);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/perfil/occurrence/getRecent")
    public ResponseEntity<RecoveryOccurrenceDto> getOccurrenceRecent(EmailUserDTO emailUserDTO) {
        RecoveryOccurrenceDto recoveryOccurrenceDto = new RecoveryOccurrenceDto(occurrenceService.getOccurrenceRecent(emailUserDTO));
        return new ResponseEntity<>(recoveryOccurrenceDto, HttpStatus.OK);
    }

    @GetMapping("/perfil/occurrence/getAll")
    public ResponseEntity<List<RecoveryOccurrenceDto>> getAllOccurrence(EmailUserDTO emailUserDTO) {
        List<RecoveryOccurrenceDto> recoveryOccurrenceDto = occurrenceService.getOccurrenceAll(emailUserDTO.email());
        return new ResponseEntity<>(recoveryOccurrenceDto, HttpStatus.OK);
    }
}
