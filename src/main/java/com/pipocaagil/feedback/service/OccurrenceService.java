package com.pipocaagil.feedback.service;

import com.pipocaagil.feedback.occurrences.Occurrence;
import com.pipocaagil.feedback.occurrences.dto.CreateOccurrenceDto;
import com.pipocaagil.feedback.occurrences.dto.RecoveryOccurrenceDto;
import com.pipocaagil.feedback.repository.OccurrenceRepository;
import com.pipocaagil.feedback.repository.UserRepository;
import com.pipocaagil.feedback.users.User;
import com.pipocaagil.feedback.users.dto.CreateUserDto;
import com.pipocaagil.feedback.users.dto.EmailUserDTO;
import com.pipocaagil.feedback.users.dto.LoginUserDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class OccurrenceService {
    @Autowired
    private OccurrenceRepository occurrenceRepository;

    @Autowired
    private UserRepository userRepository;

    // Cria um novo ocorrencia com os dados fornecidos
    public void createOccurrence(CreateOccurrenceDto createOccurrenceDto) {
        Occurrence occurrence = Occurrence.builder()
                .dateEvent(createOccurrenceDto.dateEvent())
                .location(createOccurrenceDto.location())
                .involvedEmployee(createOccurrenceDto.involvedEmployee())
                .description(createOccurrenceDto.description())
                .urlFile(createOccurrenceDto.urlFile())
                .user(userRepository.findByEmail(createOccurrenceDto.email()).orElse(null))
                .dateNow(LocalDateTime.now().minusHours(3))
                .status("Aguardando mediação")
                .title(createOccurrenceDto.title())
                .build();

        // Salva o novo ocorrencia no banco de dados
        occurrenceRepository.save(occurrence);
    }

    // Pega uma list das ocorrencias do usuario que passou o email
    public List<RecoveryOccurrenceDto> getOccurrenceAll(String email) {

        List<Occurrence> occurrences = occurrenceRepository.findByUserEmailOrderByDateNowDesc(email);

        return occurrences.stream()
                .map(RecoveryOccurrenceDto::new)
                .toList();
    }

    //Pega a Ocorrencia mais recente cadastrada no banco
    public Occurrence getOccurrenceRecent(EmailUserDTO emailUserDTO){
        return occurrenceRepository.findFirstByUserEmailOrderByDateNowDesc(emailUserDTO.email());
    }
}
