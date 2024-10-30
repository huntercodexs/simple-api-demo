package com.huntercodexs.simpleapidemo;

import com.huntercodexs.simpleapidemo.service.JEasyPdfService;
import com.huntercodexs.simpleapidemo.service.JEasyPdfSlimTemplateService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class SimpleApiDemoApplication {

	public static void main(String[] args) {

		JEasyPdfService jEasyPdfService = new JEasyPdfService();
        try {
            jEasyPdfService.pdfCreateTest();
            jEasyPdfService.pdfAddBarcodeFormTest();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JEasyPdfSlimTemplateService jEasyPdfSlimTemplateService = new JEasyPdfSlimTemplateService();
        jEasyPdfSlimTemplateService.easyPdfTemplateSlimLetterSample1Test();
        jEasyPdfSlimTemplateService.easyPdfTemplateSlimA4Sample1Test();

        SpringApplication.run(SimpleApiDemoApplication.class, args);
	}

}
