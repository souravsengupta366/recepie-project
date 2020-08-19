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
public class Recepie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    @Lob
    private String directions;
    @Lob//this will create a blob field in the database
    Byte[] image;
    @OneToOne(cascade = CascadeType.ALL)
    private Note note;
    @OneToMany(cascade = CascadeType.ALL, mappedBy ="recipe")//mapped by and casescadetypes are normally used only in owner class
    private Set<Ingredients> ingredients = new HashSet<>();
    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;
    @ManyToMany
    @JoinTable(name = "RECEPIE_CATEGORY",
        joinColumns = @JoinColumn(name = "recepie_fk"),
        inverseJoinColumns = @JoinColumn(name = "category_fk"))
    private Set<Category> categories = new HashSet<>();

}
