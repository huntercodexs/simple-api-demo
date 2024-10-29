package com.huntercodexs.simpleapidemo;

import com.huntercodexs.simpleapidemo.service.JEasyPdfService;
import org.springframework.boot.SpringApplication;

import java.io.IOException;

public class SimpleApiDemoApplication {

	public static void main(String[] args) {

		JEasyPdfService jEasyPdfService = new JEasyPdfService();
        try {
            jEasyPdfService.pdfCreateTest();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        SpringApplication.run(SimpleApiDemoApplication.class, args);
	}

}
