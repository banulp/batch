package com.banulp.toy.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.batch.item.xml.builder.StaxEventItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.xstream.XStreamMarshaller;

import javax.xml.stream.*;
import javax.xml.stream.util.XMLEventAllocator;
import javax.xml.transform.Source;
import java.io.InputStream;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class JobConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

//    @Bean
//    public StaxEventItemReader<Customer> customerItemReader(){
//        Map<String, Class> aliases = new HashMap<>();
//        aliases.put("customer", Customer.class);
//
//        CustomerConverter converter = new CustomerConverter();
//        XStreamMarshaller unmarshaller = new XStreamMarshaller();
//        unmarshaller.setAliases(aliases);
//        unmarshaller.setConverters(converter);
//
//        StaxEventItemReader<Customer> reader = new StaxEventItemReader<>();
//        reader.setResource(new ClassPathResource("customer.xml"));
//        reader.setFragmentRootElementName("customer");
//        reader.setUnmarshaller(unmarshaller);
//
//        return reader;
//    }
//
//    @Bean
//    public Step step1() {
//        return stepBuilderFactory.get("step1")
//                .<Customer, Customer>chunk(200)
//                .reader(customerItemReader())
//                .writer((ItemWriter<? super Customer>) new TestWriter())
//                .build();
//    }

    @Bean
    public StaxEventItemReader<Map<String, Object>> sstringItemReader(){
        Map<String, String> aliases = new HashMap<>();
        //aliases.put("sstring", Sstring.class);
        aliases.put("sstring", "java.util.HashMap");

        SstringConverter converter = new SstringConverter();
        XStreamMarshaller unmarshaller = new XStreamMarshaller();
        unmarshaller.setAliases(aliases);
        unmarshaller.setConverters(converter);

        ClassPathResource classPathResource = new ClassPathResource("sstring.xml");

        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        xmlInputFactory.setProperty(XMLInputFactory.SUPPORT_DTD, true);
        xmlInputFactory.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, true);

        StaxEventItemReader<Map<String, Object>> reader = new StaxEventItemReaderBuilder<Map<String, Object>>()
                .name("entitySupportReader")
                .resource(classPathResource)
                .addFragmentRootElements("sstring")
                .unmarshaller(unmarshaller)
                .xmlInputFactory(xmlInputFactory)
                .build();

//        StaxEventItemReader<Map<String, Object>> reader = new StaxEventItemReader<>();
//        reader.setResource(classPathResource);
//        reader.setFragmentRootElementName("sstring");
//        reader.setUnmarshaller(unmarshaller);

        return reader;
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .<Map<String, Object>, Map<String, Object>>chunk(200)
                .reader(sstringItemReader())
                .writer((ItemWriter<? super Map<String, Object>>) new SstringWriter())
                .build();
    }

    @Bean
    public Job job() {
        return jobBuilderFactory.get("job")
//                .start(step1())
                .start(step2())
                .build();
    }

}
