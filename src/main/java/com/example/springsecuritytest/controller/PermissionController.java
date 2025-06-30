package com.example.springsecuritytest.controller;

import com.example.springsecuritytest.model.Permission;
import com.example.springsecuritytest.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/permission")
public class PermissionController extends BaseCrudController<Permission, Integer, PermissionService> {

    @Autowired
    public PermissionController(PermissionService service) {
        super(service);
    }
}
