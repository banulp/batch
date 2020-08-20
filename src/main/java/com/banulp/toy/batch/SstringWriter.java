package com.banulp.toy.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.InitializingBean;

import java.util.List;

@Slf4j
public class SstringWriter implements ItemWriter<Sstring>, InitializingBean {

    @Override
    public void write(final List<? extends Sstring> items) throws Exception {
        for (Sstring c : items) {
            log.info("My f name is {}", c.getFirstName());
            log.info("My l name is {}", c.getLastName());
        }

    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
