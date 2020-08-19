package com.recepie.sourav.recepieproject.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String description;
    @ManyToMany(mappedBy = "categories")
    private Set<Recepie> recepies = new HashSet<>();

}
