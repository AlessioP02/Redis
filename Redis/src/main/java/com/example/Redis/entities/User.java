package com.example.Redis.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {


    private String firstName;
    private String lastName;
    private String profilePicture;
    private String email;
    private String passwordEncripted;
    private String domicileAddress;
    private String domicileCity;
    private String domicileNumbler;
    private String domicileState;
    private String fiscalCode;
}