package com.huntercodexs.simpleapidemo.controller;

import com.huntercodexs.quickjson.QuickJson;
import com.huntercodexs.simpleapidemo.service.SimpleApiDemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@ControllerAdvice
@CrossOrigin(origins = "*")
public class SimpleAPiDemoController {

    @Autowired
    SimpleApiDemoService simpleApiDemoService;

    @GetMapping(path = "/api/professional/details")
    public ResponseEntity<?> controller() {
        QuickJson professionalDetails = new QuickJson();
        professionalDetails.add("Name", "Jereelton de Oliveira Teixeira");
        professionalDetails.add("GitHUB", "https://github.com/jereelton-devel");
        professionalDetails.add("Language", "Java");
        professionalDetails.add("Role", "Backend Developer | Java Developer | Spring Boot | Infrastructure Architect | AWS Services");
        return ResponseEntity.ok().body(professionalDetails.json());
    }
}
