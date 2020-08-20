package com.banulp.toy.batch;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

@Slf4j
public class TestWriter implements ItemWriter<Customer>, InitializingBean {

    @Override
    public void write(final List<? extends Customer> items) throws Exception {
        for (Customer c : items) {
            log.info("My f name is {}", c.getFirstName());
            log.info("My l name is {}", c.getLastName());
        }

    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
