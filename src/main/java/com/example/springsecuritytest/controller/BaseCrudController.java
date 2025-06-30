package com.example.springsecuritytest.controller;

import com.example.springsecuritytest.service.BaseCrudService;
import com.example.springsecuritytest.util.ReflectionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public abstract class BaseCrudController<T, ID, S extends BaseCrudService<T, ID, ?>> {

    private final S service;

    public BaseCrudController(S service) {
        this.service = service;
    }

    public S getService() {
        return service;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        Iterable<T> all = service.findAll();
        List<T> list = StreamSupport.stream(all.spliterator(), false)
                .collect(Collectors.toList());
        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable ID id) {
        T entity = service.findById(id);
        if (entity == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(entity);
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody T entity) {
        T saved = service.save(entity);
        if (saved == null) {
            return ResponseEntity.badRequest().body("Kayıt işlemi başarısız.");
        }
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable ID id, @RequestBody T request) {
        T entity = service.findById(id);

        if (entity == null) return ResponseEntity.notFound().build();
        ReflectionUtils.copyNonNullProperties(request, entity);
        T saved = service.save(entity);

        if (saved == null) return ResponseEntity.badRequest().body("Güncelleme başarısız.");
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable ID id) {
        T entity = service.findById(id);
        if (entity == null) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
