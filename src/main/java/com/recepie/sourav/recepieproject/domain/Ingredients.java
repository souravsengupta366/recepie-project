package com.recepie.sourav.recepieproject.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Ingredients {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private  long id;
    private String description;
    private BigDecimal amount;
    @ManyToOne
    private Recepie recipe;
    @OneToOne
    private UnitOfMeasure unitOfMeasure;

    public Ingredients(String description, BigDecimal amount, UnitOfMeasure uom, Recepie recepie) {
        this.description = description;
        this.amount=amount;
        this.unitOfMeasure = uom;
        this.recipe = recepie;
    }
}
