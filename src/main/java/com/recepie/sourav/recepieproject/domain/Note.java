package com.recepie.sourav.recepieproject.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob //this will create a CLOB field in the database
    private String notes;
    @OneToOne
    private Recepie recepie;

    public Note(String notes){
        this.notes = notes;
    }
}
