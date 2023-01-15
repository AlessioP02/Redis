package com.example.Redis.entities.jpa;

import com.example.Redis.entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class UserJpa extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;


}