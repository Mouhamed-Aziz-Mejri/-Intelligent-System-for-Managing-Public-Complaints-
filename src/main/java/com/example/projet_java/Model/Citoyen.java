package com.example.projet_java.Model;

import jakarta.persistence.*;

@Table(name = "Citoyen")
public class Citoyen  extends  User{
    public Citoyen() {
    }

    public Citoyen(long id , String nom , String prenom , String password,String username  , Role role) {
        super(id , nom , prenom ,username ,password , Role.Citoyen);
    }
}
