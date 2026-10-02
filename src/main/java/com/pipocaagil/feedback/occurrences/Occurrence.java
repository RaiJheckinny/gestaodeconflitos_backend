package com.pipocaagil.feedback.occurrences;

import com.pipocaagil.feedback.occurrences.dto.DateStatus;
import com.pipocaagil.feedback.occurrences.dto.DateStatusName;
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

    private String location;

    @Column(nullable = false)
    private List<String> involvedEmployee;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "occurrence", cascade = CascadeType.ALL)
    private List<File> listFile;

    @Column(nullable = false)
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name="occurence_date_status",
            joinColumns = @JoinColumn(name = "occurence_id"),
            inverseJoinColumns = @JoinColumn(name="date_status_id"))
    private List<DateStatus> status;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Integer numberConflit;

    @ManyToOne
    @JoinColumn(name = "user_mediation", referencedColumnName = "email")
    private User user_mediation;

    @ManyToOne
    @JoinColumn(name = "user_email", referencedColumnName = "email")
    private User user;
}
