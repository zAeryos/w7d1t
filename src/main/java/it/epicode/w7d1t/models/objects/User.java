package it.epicode.w7d1t.models.objects;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_sequence")
    @SequenceGenerator(name = "users_sequence", initialValue = 1, allocationSize = 1)
    private  int     id;
    private  String  name;
    private  String  surname;
    @Column(unique = true)
    private  String  username;
    private  String  password;
}
