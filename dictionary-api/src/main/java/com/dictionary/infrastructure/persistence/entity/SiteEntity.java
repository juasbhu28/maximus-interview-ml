package com.dictionary.infrastructure.persistence.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "sites")
public class SiteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 50)
    private String code;

    @Column(nullable = false, length = 100)
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "site_words",
            joinColumns = @JoinColumn(name = "site_id"),
            inverseJoinColumns = @JoinColumn(name = "word_id"))
    private Set<WordEntity> words = new HashSet<>();
}
