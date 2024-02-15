package com.evntmgmt.EventManagement.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Roles")
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    private String id;
    private ERole name;
}
