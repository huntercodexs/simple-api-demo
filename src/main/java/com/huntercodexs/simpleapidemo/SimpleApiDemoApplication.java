package com.huntercodexs.simpleapidemo;

import com.huntercodexs.quickjson.QuickJson;
import com.huntercodexs.quickjson.core.QuickJsonBuilder;
import com.huntercodexs.quickjson.core.QuickJsonExtractor;
import com.huntercodexs.simpleapidemo.dto.QuickJsonDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.HashMap;

@SpringBootApplication
public class SimpleApiDemoApplication {

	public static void main(String[] args) {

		//hardsysJson();
		//hardsysDto();
		//barcodeForm();
		//slimPdf();
		//lambdaAwsDemo();
		quickJson1();
		quickJson2();

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
//		JeasyPdfBarcodeFormTestService jeasyForm = new JeasyPdfBarcodeFormTestService();
//		jeasyForm.barcodeForm();
	}

	private static void slimPdf() {
//		JeasyPdfTestService jeasyPdf = new JeasyPdfTestService();
//		jeasyPdf.slimPdfLetter();
//		jeasyPdf.slimPdfA4();
	}

	private static void lambdaAwsDemo() {
		//Java 11 - Project
		//Help4DevsAwsLambdaDemo help4DevsAwsLambdaDemo = new Help4DevsAwsLambdaDemo();
		//System.out.println(help4DevsAwsLambdaDemo.handleRequestName("Huntercodexs"));
	}

	private static void quickJson1() {

		QuickJson qj = new QuickJson();
		QuickJsonBuilder qjBuilder = new QuickJsonBuilder();
		QuickJsonExtractor qjExtractor = new QuickJsonExtractor();

		HashMap<String, Object> map = new HashMap<>();
		map.put("map1", "Map 1 Value Test");
		map.put("map2", 345);
		map.put("map3", Arrays.asList("Array 1", "Array 2", 222, "Array 3"));
		map.put("map4", "{\"name\":\"Sarah Wiz\",\"parental\":\"friend\"}");

		qj.setStrictMode(false);
		qj.add("type", "Person");
		qj.add("name", "John");
		qj.add("lastname", "Smith");
		qj.add("fullname", "John Smith Viz");
		qj.add("age", 35);
		qj.add("address", Arrays.asList("Street 1", "200", "New York City"));
		qj.add("contacts", Arrays.asList("12345678", "98789789", "12424242"));
		qj.add("numbers", Arrays.asList(1, 2, 3, 4, 5, 6));
		qj.add("reference", "{\"name\":\"Sarah Wiz\",\"parental\":\"friend\"}");
		qj.add("family",
				Arrays.asList(
						"mother", "July Smith",
						"father", "Luis Smith",
						Arrays.asList(
								"sister", "Elen Smith", "age", 22
						),
						Arrays.asList(
								"brother", "Igor Smith", "age", 24
						)
				)
		);
		qj.add("map", map);

		String jsonResult = qj.json();

		qjBuilder.setStrictMode(false);
		QuickJsonDto build = (QuickJsonDto) qjBuilder.build(jsonResult, QuickJsonDto.class);

		System.out.println("QUICK JSON 1");
		System.out.println(build);
	}

	private static void quickJson2() {

		QuickJson qj = new QuickJson();
		QuickJsonBuilder qjBuilder = new QuickJsonBuilder();
		QuickJsonExtractor qjExtractor = new QuickJsonExtractor();

		HashMap<String, Object> map = new HashMap<>();
		map.put("map1", "Map 1 Value Test");
		map.put("map2", 345);
		map.put("map3", Arrays.asList("Array 1", "Array 2", 222, "Array 3"));
		map.put("map4", "{\"name\":\"Sarah Wiz\",\"parental\":\"friend\"}");

		qj.setStrictMode(false);
		qj.add("type", "Person");
		qj.add("name", "John");
		qj.add("lastname", "Smith");
		qj.add("fullname", "John Smith Viz");
		qj.add("age", 35);
		qj.add("address", Arrays.asList("Street 1", "200", "New York City"));
		qj.add("contacts", Arrays.asList("12345678", "98789789", "12424242"));
		qj.add("numbers", Arrays.asList(1, 2, 3, 4, 5, 6));
		qj.add("reference", "{\"name\":\"Sarah Wiz\",\"parental\":\"friend\"}");
		qj.add("family",
				Arrays.asList(
						"mother", "July Smith",
						"father", "Luis Smith",
						Arrays.asList(
								"sister", "Elen Smith", "age", 22
						),
						Arrays.asList(
								"brother", "Igor Smith", "age", 24
						)
				)
		);
		qj.add("map", map);

		String jsonResult = qj.json();

		qjBuilder.setStrictMode(false);
		QuickJsonDto quickJsonDto = (QuickJsonDto) qjBuilder.build(jsonResult, QuickJsonDto.class);

		Object jsonFinal = qjBuilder.build(quickJsonDto);

		System.out.println("QUICK JSON 2");
		System.out.println(jsonFinal);

	}

}
