package com.pipocaagil.feedback.occurrences;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String urlFile;

    private String urlName;

    @ManyToOne
    @JoinColumn(name = "occurrence_id")
    private Occurrence occurrence;
}