package org.example;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.kafka.clients.producer.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import org.json.*;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.Properties;

public class srt {
    public static void main(String[] args) throws IOException {

        Properties prop = new Properties();

        prop.put("bootstrap.servers", "localhost:9092");
        prop.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        prop.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");


        String topic = "casa-bourse";

        Producer<String, byte[]> producer = new KafkaProducer<>(prop);





        JSONArray data = getData.aralya_data();
        JSONObject first_dt = (JSONObject) data.get(0);


        BourseData bourseData = new BourseData();
        for(String key : first_dt.keySet()){
            bourseData.put(key, first_dt.get(key));
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DatumWriter<BourseData> datumWriter = new SpecificDatumWriter<>(BourseData.class);
        BinaryEncoder encoder = EncoderFactory.get().binaryEncoder(out, null);
        datumWriter.write(bourseData, encoder);
        encoder.flush();
        byte[] avroData = out.toByteArray();


        ProducerRecord<String, byte[]> record = new ProducerRecord<>(topic, "ADH",  avroData);
        producer.send(record);

        producer.close();

        System.out.println("Message sent to topic: " + topic);











    }
}
