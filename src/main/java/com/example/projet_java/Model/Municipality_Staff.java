package com.example.projet_java.Model;

import jakarta.persistence.*;

@Table(name = "Municipality Staff")
public class Municipality_Staff extends User {
    public Municipality_Staff() {
    }

    public Municipality_Staff(long id , String nom , String prenom , String password,String username  , Role role) {
        super(id , nom , prenom ,username ,password , Role.Municipality_Staff);
    }
}
