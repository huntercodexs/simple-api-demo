package com.huntercodexs.simpleapidemo;

import com.huntercodexs.simpleapidemo.service.JeasyPdfBarcodeFormTestService;
import com.huntercodexs.simpleapidemo.service.JeasyPdfTestService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimpleApiDemoApplication {

	public static void main(String[] args) {
		//hardsysJson();
		//hardsysDto();
		//barcodeForm();
		slimPdf();
		//lambdaAwsDemo();
		SpringApplication.run(SimpleApiDemoApplication.class, args);
	}

	private static void hardsysJson() {
		//Java 8 - Project
		//HardSys hardSys = new HardSys(INXI);
		//hardSys.json();
		//System.out.println(hardSys.resources().all());
	}

	private static void hardsysDto() {
		//Java 8 - Project
		//HardSys hardSysDto = new HardSys(INXI);
		//HardSysResourcesDto result = hardSysDto.resources().builder();
		//System.out.println(result);
	}

	private static void barcodeForm() {
		JeasyPdfBarcodeFormTestService jeasyForm = new JeasyPdfBarcodeFormTestService();
		jeasyForm.barcodeForm();
	}

	private static void slimPdf() {
		JeasyPdfTestService jeasyPdf = new JeasyPdfTestService();
		jeasyPdf.slimPdfLetter();
		jeasyPdf.slimPdfA4();
	}

	private static void lambdaAwsDemo() {
		//Java 11 - Project
		//Help4DevsAwsLambdaDemo help4DevsAwsLambdaDemo = new Help4DevsAwsLambdaDemo();
		//System.out.println(help4DevsAwsLambdaDemo.handleRequestName("Huntercodexs"));
	}

}
