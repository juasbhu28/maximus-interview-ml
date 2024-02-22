package com.dictionary.application.model;

import com.dictionary.domain.entity.RoleEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class User {

    private String username;
    private String email;
    private String password;
    private Set<Role> roles = new HashSet<>();

}
