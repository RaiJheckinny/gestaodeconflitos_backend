package com.pipocaagil.feedback.occurrences;

import com.pipocaagil.feedback.occurrences.dto.FileDTO;
import com.pipocaagil.feedback.users.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Table(name = "occurrence")
@Entity(name = "occurrence")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Occurrence {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID protocol;

    @Column(nullable = false)
    private LocalDateTime dateEvent;

    @Column(nullable = false)
    private LocalDateTime dateNow;

    private String location;

    @Column(nullable = false)
    private List<String> involvedEmployee;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "occurrence", cascade = CascadeType.ALL)
    private List<File> listFile;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Boolean priorit;

    @ManyToOne
    @JoinColumn(name = "user_email", referencedColumnName = "email")
    private User user;
}
