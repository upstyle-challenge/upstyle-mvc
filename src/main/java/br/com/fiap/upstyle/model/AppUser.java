package br.com.fiap.upstyle.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "app_user")
@Data
@NoArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private Integer age;
    private Double income;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles;

    public AppUser(String username, String password, String email, String firstName, String lastName, String gender, Integer age, Double income, Set<String> roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.income = income;
        this.roles = roles;
    }

    public void edit(AppUser user) {
        if(user.getUsername() != null) this.username = user.getUsername();
        if(user.getPassword() != null) this.password = user.getPassword();
        if(user.getEmail() != null) this.email = user.getEmail();
        if(user.getFirstName() != null) this.firstName = user.getFirstName();
        if(user.getLastName() != null) this.lastName = user.getLastName();
        if(user.getGender() != null) this.gender = user.getGender();
        if(user.getAge() != null) this.age = user.getAge();
        if(user.getIncome() != null) this.income = user.getIncome();
    }
}

