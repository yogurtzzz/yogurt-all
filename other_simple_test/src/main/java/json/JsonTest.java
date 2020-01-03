package json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.*;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class JsonTest {


    @Getter
    @Setter
    @Builder
    public static class Student{
        private String name;
        private Integer age;
        private Integer score;
    }

    @Getter
    @Setter
    @Builder
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonSerialize(using = AnimalSerializer.class)
    public static class Animal{
        private String species;
        private String scale;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Flight implements Serializable {
        private String flightNo;
        private String depDate;
        private String depTime;
        private String planeStyle;
        private Boolean codeShare;
        private String carrier;
        private String orgAirport;
        private String dstAirport;
        private String arriveDate;
        private String arriveTime;
        private Price price;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Price implements Serializable{
        /**
         * 这里将价格用String表示
         */
        private String price;
        private String tax;
        private String totalPrice;
        private String currencyCode;
        private String supplierBasePrice;
    }

    public static class AnimalSerializer extends JsonSerializer<Animal>{
        @Override
        public void serialize(Animal animal, JsonGenerator jgen, SerializerProvider sp) throws IOException {
            jgen.writeStartObject();
            jgen.writeBooleanField("what",true);
            jgen.writeNumberField("num?",1);
            jgen.writeStringField("str?",animal.getScale()+animal.getSpecies());
            jgen.writeFieldName("arr?");
            jgen.writeStartArray();
            jgen.writeString("arr1");
            jgen.writeString("arr2");
            jgen.writeString("arr3");
            jgen.writeEndArray();
            jgen.writeEndObject();
        }
    }
    @Test
    public void testIgonre() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        Student student = Student.builder()
                .name("yogurt")
                .age(16)
                .score(95)
                .build();
        String s = mapper.writeValueAsString(student);
        System.out.println(s);
        JsonNode node = mapper.readTree(s);

        JsonNode oNode = mapper.valueToTree(student);

        if (node.equals(oNode)){
            System.out.println("equals");
        }
        System.out.println("yes");
    }

    /**
     * 简单的json串与 JAVA 对象转换
     * @throws IOException
     */
    @Test
    public void jsonTest() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = "{\"name\":\"Yogurt\",\"age\":23}";
        //ObjectMapper # readValue() 反序列化JSON串到对象
        Student student = objectMapper.readValue(jsonStr,Student.class);
        System.out.println(student);

        //ObjectMapper # writeValueAsString() 序列化对象到JSON串
        String studentStr = objectMapper.writeValueAsString(student);
        System.out.println(studentStr);
    }
}
