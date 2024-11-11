package com.huntercodexs.simpleapidemo.controller;

import com.huntercodexs.quickjson.QuickJson;
import com.huntercodexs.simpleapidemo.service.SimpleApiDemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@ControllerAdvice
@CrossOrigin(origins = "*")
public class SimpleAPiDemoController {

    @Autowired
    SimpleApiDemoService simpleApiDemoService;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to SIMPLE API DEMO Updated";
    }

    @ResponseBody
    @GetMapping(path = "/api/professional/details", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> controller() {
        QuickJson professionalDetails = new QuickJson();
        professionalDetails.add("Name", "Jereelton de Oliveira Teixeira");
        professionalDetails.add("GitHUB", "https://github.com/jereelton-devel");
        professionalDetails.add("Language", "Java");
        professionalDetails.add("Role", "Software Engineering | Backend Developer | Java Developer | Spring Boot | Infrastructure Architect | AWS Services");
        professionalDetails.add("Java", "17");
        professionalDetails.add("Packing", "Jar File");
        return ResponseEntity.ok().body(professionalDetails.json());
    }
}