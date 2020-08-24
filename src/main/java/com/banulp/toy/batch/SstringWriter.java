package com.banulp.toy.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.InitializingBean;

import java.util.List;
import java.util.Map;

@Slf4j
public class SstringWriter implements ItemWriter<Map<String, Object>>, InitializingBean {

    @Override
    public void write(final List<? extends Map<String, Object>> items) throws Exception {
        for (Map c : items) {
            log.info("My f name is {}", c.get("FirstName"));
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
