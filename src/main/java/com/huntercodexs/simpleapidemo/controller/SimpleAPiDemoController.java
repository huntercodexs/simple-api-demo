package com.huntercodexs.simpleapidemo.controller;

import com.huntercodexs.simpleapidemo.service.SimpleApiDemoService;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
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
    public ResponseEntity<JSONObject> controller() {
        JSONObject professionalDetails = new JSONObject();
        professionalDetails.put("Name", "Jereelton de Oliveira Teixeira");
        professionalDetails.put("GitHUB", "https://github.com/jereelton-devel");
        professionalDetails.put("Language", "Java");
        professionalDetails.put("Role", "Backend Developer | Java Developer | Spring Boot | Infrastructure Architect | AWS Services");
        professionalDetails.put("Java", "17");
        return ResponseEntity.ok().body(professionalDetails);
    }
}
