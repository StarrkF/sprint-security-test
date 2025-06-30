package com.example.springsecuritytest.service;

import com.example.springsecuritytest.model.Permission;
import com.example.springsecuritytest.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionService extends BaseCrudService<Permission, Integer, PermissionRepository> {

    @Autowired
    public PermissionService(PermissionRepository repo) {
        super(repo);
    }
}
