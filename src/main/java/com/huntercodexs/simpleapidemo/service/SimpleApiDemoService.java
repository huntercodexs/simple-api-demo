package com.huntercodexs.simpleapidemo.service;

import com.huntercodexs.quickjson.QuickJson;
import com.huntercodexs.quickjson.core.QuickJsonBuilder;
import com.huntercodexs.quickjson.core.QuickJsonExtractor;
import com.huntercodexs.simpleapidemo.dto.QuickJsonDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;

@Service
public class SimpleApiDemoService {

    public ResponseEntity<?> service() {
        return ResponseEntity.ok().body("OK, it is working fine !");
    }

    public String welcome(String name) {
        return "Welcome " + name;
    }

    public void quickJsonTest() {

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

        System.out.println(jsonFinal);

    }

}
