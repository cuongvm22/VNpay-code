package com.vnpay.fraud.consumer;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.vnpay.fraud.consumer.actor.ActorMessage;
import com.vnpay.fraud.consumer.actor.IpnNotifyActor;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

    final static Logger log = LoggerFactory.getLogger("fraudapi");
    public static Properties props;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Init actor container
        final ActorSystem system = ActorSystem.create("fraud_consumer");
        try {
            //Load config Properties
            String cfgFileName = "application.properties";
            File cfgFile = new File(cfgFileName).getAbsoluteFile();
            props = new Properties();
            if (cfgFile.exists() && cfgFile.isFile()) {
                //Config file ok
                log.info("Load config from:" + cfgFile.getAbsolutePath());
                FileInputStream file = new FileInputStream(cfgFile);
                props.load(file);
            } else {
                //Config file not exits, load default from classpath 
                log.warn("Load config from ClassPath");
                System.err.println("Load config from ClassPath:" + cfgFileName);
                InputStream resourceConfig = Thread.currentThread().getContextClassLoader().getResourceAsStream(cfgFileName);
                if (resourceConfig != null) {
                    props.load(resourceConfig);
                } else {
                    System.err.println("Can not get from resouce:" + cfgFileName);
                    log.warn("Can not read resouce file: {}", cfgFileName);
                }
            }
            //Register kill event
            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    system.terminate();
                    log.warn("System shutting down !...");
                    System.err.println("System shutting down ...");

                }

            });
            System.err.println("Kafka topic:" + props.getProperty("kafka.topic"));
            //Bind properties to 
            Properties kafkaProps = new Properties();
            kafkaProps.put("bootstrap.servers", props.getProperty("kafka.bootstrap.servers"));
            kafkaProps.put("group.id", props.getProperty("kafka.group.id"));
            kafkaProps.put("enable.auto.commit", props.getProperty("kafka.enable.auto.commit"));
            kafkaProps.put("auto.commit.interval.ms", props.getProperty("kafka.auto.commit.interval.ms"));
            kafkaProps.put("session.timeout.ms", props.getProperty("kafka.session.timeout.ms"));
            kafkaProps.put("key.deserializer", props.getProperty("kafka.key.deserializer"));
            kafkaProps.put("value.deserializer", props.getProperty("kafka.value.deserializer"));
            KafkaConsumer<String, String> consumer = new KafkaConsumer<>(kafkaProps);
            String[] topicList = props.getProperty("kafka.topic").split(",");
            consumer.subscribe(Arrays.asList(topicList));
            ActorRef ipnActor = system.actorOf(Props.create(IpnNotifyActor.class));
            ActorMessage msg;

            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(100);
                for (ConsumerRecord<String, String> record : records) {
                    msg = new ActorMessage();
                    msg.setMsg_type(record.topic());
                    msg.setMsg_content(record.value());
                    ipnActor.tell(msg, ActorRef.noSender());
                }
            }
        } catch (IOException ex) {
            log.error("Start app exception:", ex);
            System.out.println(ex);
        }
    }

//    static void logbackConfig() {
//        try {
//            String logConfigFile = "logback.xml";
//            File cfgFile = new File(logConfigFile).getAbsoluteFile();
//            LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
//            JoranConfigurator jc = new JoranConfigurator();
//            if (cfgFile.exists() && cfgFile.isFile()) {
//                jc.doConfigure(cfgFile);
//            } else {
//                //Load from ClassPath
//                //InputStream resourceConfig = Thread.currentThread().getContextClassLoader().getResourceAsStream(logConfigFile);
//                InputStream resourceConfig = App.class.getClassLoader().getResourceAsStream(logConfigFile);
//                jc.doConfigure(resourceConfig);
//            }
//            System.out.println("Load logback.xml success");
//            
//        } catch (Exception ex) {
//            System.err.println("Load logback.xml error:{}" + ex.getStackTrace());
//        }
//        
//    }
}
