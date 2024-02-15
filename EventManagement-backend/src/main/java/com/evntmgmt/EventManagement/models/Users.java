package com.evntmgmt.EventManagement.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "Users")
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    private String id;
    @NotBlank
    @Size(max=15)
    private String username;
    @NotBlank
    @Size(max=50)
    @Email
    private String email;
    @NotBlank
    @Size(max=16)
    private String password;

    @DBRef
    private Set<Role> roles = new HashSet<>();

    public Users(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

}
