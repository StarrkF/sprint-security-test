package com.example.springsecuritytest.controller;

import com.example.springsecuritytest.model.Role;
import com.example.springsecuritytest.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/role")
public class RoleConctoller extends BaseCrudController<Role, Integer, RoleService> {

    @Autowired
    public RoleConctoller(RoleService service) {
        super(service);
    }
}
