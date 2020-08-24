package com.banulp.toy.batch;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

public class SstringConverter implements Converter {

    @Override
    public boolean canConvert(Class type) {
        return true;
    }

    @Override
    public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
        // Don't do anything
    }

    @Override
    public Map<String, Object> unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        Map<String, Object> map = new HashMap<>();
        reader.moveDown();
        map.put("FirstName", reader.getValue());
        reader.moveUp();
        return map;
    }
}
