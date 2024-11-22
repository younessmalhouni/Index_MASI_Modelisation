# Kakfka Installation

Open Docker : Click on terminal, down of the ui, and pull the kafka image : 

``$ docker pull apache/kafka:3.9.0``

And create the container :

``$ docker run -p 9092:9092 apache/kafka:3.9.0``


# Run a simple Kafka script

In your java IDE , create a Maven project, in pom.xml add the following modify the group and artifact id for your project name:


```<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>here addd group id</groupId>
    <artifactId>here add your artifactId</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <!-- https://mvnrepository.com/artifact/org.apache.kafka/kafka-clients -->
        <dependency>
            <groupId>org.apache.kafka</groupId>
            <artifactId>kafka-clients</artifactId>
            <version>3.8.0</version>
        </dependency>
        <dependency>
            <groupId>org.apache.httpcomponents.client5</groupId>
            <artifactId>httpclient5</artifactId>
            <version>5.4</version> <!-- Replace with the latest version -->
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.15.0</version> <!-- Replace with the latest version -->
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>1.7.5</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-log4j12</artifactId>
            <version>1.7.5</version>
        </dependency>



    </dependencies>

</project> ```


Create a new class in your project and run this script, along with kafka host in docker running:

```package org.example;
import org.apache.kafka.clients.producer.*;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.util.Properties;

public class srt {
    public static void main(String[] args) {
        String topic = "casablanca-ticker";


        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");


        KafkaProducer<String, String> producer = new KafkaProducer<>(props);


        ObjectMapper objectMapper = new ObjectMapper();


        ProducerRecord<String, String> record = new ProducerRecord<>(topic, "key1", "Hello Kafka!");
        producer.send(record, (metadata, exception) -> {
            if (exception == null) {
                System.out.println("Record sent successfully! Metadata: " + metadata.toString());
            } else {
                exception.printStackTrace();
            }
        });

        // Close the producer
        producer.close();
    }
}
```
