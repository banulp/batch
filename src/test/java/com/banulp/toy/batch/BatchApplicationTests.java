package com.banulp.toy.batch;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EntityReference;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@SpringBootTest
class BatchApplicationTests {

    @Test
    void replaceEntityTest() {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();

        // true 면 &hellip; 를 replace text ( event type 4 ) 로 인식
        // false 면 Entity Reference ( event type 9 ) 로 인식
//        inputFactory.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, false);
//        inputFactory.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, true);

        System.out.println("IS_REPLACING_ENTITY_REFERENCES: " + inputFactory.getProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES));
        System.out.println("---------------------------");

        XMLEventReader reader;
        try {
            reader = inputFactory.createXMLEventReader(new FileInputStream("src/main/resources/test.xml"));
            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                System.out.println("Event type: " + "("+ event.getEventType() +")"+  event.toString()  );
                if (event.isEntityReference()) {
                    EntityReference ref = (EntityReference) event;
                    System.out.println("    Entity Reference name: " + ref.getName());
                    System.out.println("    Entity Reference replacement: " + ref.getDeclaration().getReplacementText());
                } else if (event.isCharacters()) {
                    Characters ref = (Characters) event;
                    System.out.println("    Characters data: " + ref.getData());
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
