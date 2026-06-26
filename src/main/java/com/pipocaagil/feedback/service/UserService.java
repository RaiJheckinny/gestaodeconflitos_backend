package com.pipocaagil.feedback.service;

import com.pipocaagil.feedback.repository.UserRepository;
import com.pipocaagil.feedback.security.Role;
import com.pipocaagil.feedback.security.UserDetailsImpl;
import com.pipocaagil.feedback.security.configuration.SecurityConfiguration;
import com.pipocaagil.feedback.users.User;
import com.pipocaagil.feedback.users.dto.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityConfiguration securityConfiguration;

    // Método responsável por autenticar um usuário e retornar um token JWT
    public RecoveryJwtTokenDto authenticateUser(LoginUserDto loginUserDto) {
        // Cria um objeto de autenticação com o email e a senha do usuário
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginUserDto.email(), loginUserDto.password());

        // Autentica o usuário com as credenciais fornecidas
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        // Obtém o objeto UserDetails do usuário autenticado
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        // Gera um token JWT para o usuário autenticado
        return new RecoveryJwtTokenDto(jwtTokenService.generateToken(userDetails));
    }
    // Método exclusivo do Service para testar o e-mail
    public boolean verificarSeEmailExiste(LoginUserDto loginUserDto) {
        // Procura o usuário pelo e-mail enviado no DTO
        User user = userRepository.findByEmail(loginUserDto.email()).orElse(null);

        // Retorna true se o usuário existir, ou false se for nulo (não existe)
        return user != null;
    }

    @Transactional
    public void atualizarAcessoUsuario(LoginUserDto loginUserDto) {
        // 1. Busca o usuário pelo e-mail do DTO
        User user = userRepository.findByEmail(loginUserDto.email())
                .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(
                        org.springframework.http.HttpStatus.NOT_FOUND, "Usuário não encontrado no atualizar utimo login."
                ));

        // 2. Pega o LocalDateTime travado no fuso de São Paulo
        LocalDateTime localDateTimeSaoPaulo = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));

        // 3. Atualiza o campo e salva no banco de dados
        user.setLast_accessed(localDateTimeSaoPaulo);
        userRepository.save(user);
    }

    // Método responsável por criar um usuário
    public void createUser(CreateUserDto createUserDto) {

        // Cria um novo usuário com os dados fornecidos
        User newUser = User.builder()
                .email(createUserDto.email())
                // Codifica a senha do usuário com o algoritmo bcrypt
                .password(securityConfiguration.passwordEncoder().encode(createUserDto.password()))
                .position(createUserDto.position())
                .url_photo("https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png")
                .name(createUserDto.name())
                .department(createUserDto.department())
                .last_accessed(LocalDateTime.now().minusHours(3))
                // Atribui ao usuário uma permissão específica
                .roles(List.of(Role.builder().name(createUserDto.role()).build()))
                .build();

        // Salva o novo usuário no banco de dados
        userRepository.save(newUser);
    }

    // Método responsável por retorna um usuário e retornar
    public RecoveryUserDto getUser(EmailUserDTO emailUserDTO){
        User user = userRepository.findByEmail(emailUserDTO.email()).orElse(null);;

        //Usuario nao encontrado retorna null
        if (user == null) {
            throw new RuntimeException("Usuário não encontrado");
        }

        return new RecoveryUserDto(user);
    };
}
