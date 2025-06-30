package com.example.springsecuritytest.service;

import com.example.springsecuritytest.model.Role;
import com.example.springsecuritytest.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends BaseCrudService<Role, Integer, RoleRepository> {

    @Autowired
    public RoleService(RoleRepository repo) {
        super(repo);
    }
}
