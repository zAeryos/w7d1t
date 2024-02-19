package it.epicode.w7d1t.models.objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employees_sequence")
    @SequenceGenerator(name = "employees_sequence", initialValue = 1, allocationSize = 1)
    private int             id;

    private String          name;
    private String          surname;
    private String          username;
    private String          pfp;
    @Column(unique = true)
    private String          email;
    @JsonIgnore
    @OneToMany(mappedBy = "employee")
    private List<Device>    devices;


    public Employee(String name, String surname, String username, String email) {
        this.name       = name;
        this.surname    = surname;
        this.email      = email;
        this.username   = username;
        this.devices    = new ArrayList<>();
    }

    public Employee(){}
}
