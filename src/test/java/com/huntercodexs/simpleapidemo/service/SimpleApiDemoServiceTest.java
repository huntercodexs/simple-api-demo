package com.huntercodexs.simpleapidemo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class SimpleApiDemoServiceTest {

    SimpleApiDemoService simpleApiDemoService;

    @BeforeEach
    public void setUp() {
        this.simpleApiDemoService = new SimpleApiDemoService();
    }

    @Test
    void service() {
        Assertions.assertNotNull(simpleApiDemoService.service());
    }

    @Test
    void welcome() {
        Assertions.assertEquals("Welcome John", simpleApiDemoService.welcome("John"));
    }

    @Test
    void quickJsonTest() {
        Assertions.assertNotNull(simpleApiDemoService.quickJsonTest());
    }
}