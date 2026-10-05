package com.pipocaagil.feedback.service;

import com.pipocaagil.feedback.occurrences.File;
import com.pipocaagil.feedback.occurrences.Occurrence;
import com.pipocaagil.feedback.occurrences.dto.*;
import com.pipocaagil.feedback.repository.FileRepository;
import com.pipocaagil.feedback.repository.OccurrenceRepository;
import com.pipocaagil.feedback.repository.UserRepository;
import com.pipocaagil.feedback.security.Role;
import com.pipocaagil.feedback.security.RoleName;
import com.pipocaagil.feedback.users.User;
import com.pipocaagil.feedback.users.dto.CreateUserDto;
import com.pipocaagil.feedback.users.dto.EmailUserDTO;
import com.pipocaagil.feedback.users.dto.LoginUserDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
public class OccurrenceService {
    @Autowired
    private OccurrenceRepository occurrenceRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FileRepository fileRepository;

    // Cria um novo ocorrencia com os dados fornecidos
    public RecoveryUUIDDto createOccurrence(CreateOccurrenceDto createOccurrenceDto) {

        Occurrence occurrence = Occurrence.builder()
                .dateEvent(createOccurrenceDto.dateEvent())
                .location(createOccurrenceDto.location())
                .numberConflit(1)
                .involvedEmployee(createOccurrenceDto.involvedEmployee())
                .description(createOccurrenceDto.description())
                .user(userRepository.findByEmail(createOccurrenceDto.email()).orElse(null))
                .title(createOccurrenceDto.title())
                .status(List.of(DateStatus.builder().name(DateStatusName.Rascunho).date(dateNow()).build()))
                .build();

        if (createOccurrenceDto.protocol() != null) {
            occurrence.setProtocol(createOccurrenceDto.protocol());
        }
        occurrenceRepository.save(occurrence);
        List<File> files = createOccurrenceDto.listFile().stream()
                .map(fileDto -> {
                    File file = File.builder()
                            .urlFile(fileDto.urlFile())
                            .urlName(fileDto.urlName())
                            .occurrence(occurrence)
                            .build();

                    return fileRepository.save(file);
                })
                .toList();
        return new RecoveryUUIDDto(occurrence.getProtocol());
    }

    // Pega uma list das ocorrencias do usuario que passou o email
    public List<RecoveryOccurrenceDto> getOccurrenceAll(String email) {

        List<Occurrence> occurrences = occurrenceRepository.findByUserEmailOrderByStatusDateDesc(email);

        return occurrences.stream()
                .map(RecoveryOccurrenceDto::new)
                .toList();
    }

    //Pega a Ocorrencia mais recente cadastrada no banco
    public Occurrence getOccurrenceRecent(EmailUserDTO emailUserDTO){
        return occurrenceRepository.findFirstByUserEmailOrderByStatusDateDesc(emailUserDTO.email()).orElseThrow(() -> new RuntimeException("O usuario nao foi encontrado"));
    }

    public Occurrence getOccurrenceUUid(UuidOccurrenceDto uuidOccurrenceDtoDTO) {
        return occurrenceRepository.findByProtocol(uuidOccurrenceDtoDTO.protocol())
                .orElseThrow(() -> new RuntimeException("Ocorrência não encontrada com o protocolo informado."));
    }

    public LocalDateTime dateNow(){
        return LocalDateTime.now().minusHours(3);
    }

    public void updateOcurrenceAnalise(UuidOccurrenceDto protocolo) {
        Occurrence occurrence = getOccurrenceUUid(protocolo);

        occurrence.getStatus().add(
                DateStatus.builder()
                        .name(DateStatusName.Aguardando_mediação)
                        .date(dateNow())
                        .build()
        );

        List<User> users = userRepository.findDistinctByRolesName(RoleName.ROLE_ADMINISTRATOR);

        // 1. Validação para evitar erro se não houver administradores cadastrados
        if (users.isEmpty()) {
            throw new RuntimeException("Nenhum usuário administrador encontrado.");
        }

        // 2. Busca o usuário com a menor lista usando Stream
        User userComMenorLista = users.stream()
                .min(Comparator.comparingInt(user ->
                        user.getList_mediation() != null ? user.getList_mediation().size() : 0
                ))
                .orElseThrow();

        // 3. Garante que a lista não seja nula antes de adicionar
        if (userComMenorLista.getList_mediation() == null) {
            userComMenorLista.setList_mediation(new ArrayList<>());
        }

        // 4. Adiciona a ocorrência e salva
        userComMenorLista.getList_mediation().add(occurrence);
        userRepository.save(userComMenorLista);
    }
}
