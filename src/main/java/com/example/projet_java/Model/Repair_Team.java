package com.example.projet_java.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Table(name = "Repair_Team")
public class Repair_Team  extends User{
    public Repair_Team() {
    }

    public Repair_Team(long id , String nom , String prenom , String password,String username  , Role role) {
        super(id , nom , prenom ,username ,password , Role.Repair_Team);
    }
}
