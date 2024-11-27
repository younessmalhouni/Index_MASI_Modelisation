package org.example;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.*;


import java.time.Instant;
import java.util.Properties;

public class srt {
    public static void main(String[] args) {

        Properties prop = new Properties();

        prop.put("bootstrap.servers", "localhost:9092");
        prop.put("key.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
        prop.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
        prop.put("schema.registry.url", "http://localhost:8081");

        String topic = "casa-bourse";

        Producer<String, BourseData> producer = new KafkaProducer<>(prop);



        JSONArray data = getData.aralya_data();
        JSONObject first_dt = (JSONObject) data.get(0);

        BourseData bourseData = new BourseData();
        for(String key : first_dt.keySet()){
            bourseData.put(key, first_dt.get(key));
        }










    }
}
