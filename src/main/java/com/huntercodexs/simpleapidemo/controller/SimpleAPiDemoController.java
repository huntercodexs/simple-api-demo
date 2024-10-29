package com.huntercodexs.simpleapidemo.controller;

import com.huntercodexs.simpleapidemo.service.SimpleApiDemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@ControllerAdvice
@CrossOrigin(origins = "*")
@RequestMapping("${api.prefix}")
public class SimpleAPiDemoController {

    @Autowired
    SimpleApiDemoService simpleApiDemoService;

    @GetMapping(path = "/api/professional/details")
    public ResponseEntity<?> controller() {
        return ResponseEntity.ok().body("OK");
    }
}
