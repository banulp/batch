package com.banulp.toy.batch;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EntityReference;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@SpringBootTest
class BatchApplicationTests {

    @Test
    void contextLoads() {
        String a = "Jo&hellip;hn";
        String b = a.replaceAll("&hellip;", "...");
        assert (b.equals("Jo...hn"));
    }

    @Test
    void replaceEntityTest() {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
//        inputFactory.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, false);
        inputFactory.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, true);
        XMLEventReader reader;
        try {
            reader = inputFactory
                    .createXMLEventReader(new FileInputStream("src/main/resources/test.xml"));
            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                if (event.isEntityReference()) {
                    EntityReference ref = (EntityReference) event;
                    System.out.println("Entity Reference: " + ref.getName());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

        assert (true);
    }

}
