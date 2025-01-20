package com.example.librarymanagement.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "roles")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role {
    @Id
    String name;

    String description;
}
