package fr.host_dcode.vlib.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "users")
public class User {
    public enum Status {
        USER,
        ADMIN
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDateTime created_at;


    public User(){}

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    public String getEmail(){
        return this.email;
    }

    public Status getStatus(){
        return this.status;
    }



}
