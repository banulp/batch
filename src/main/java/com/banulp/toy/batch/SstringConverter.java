package com.banulp.toy.batch;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import lombok.extern.slf4j.Slf4j;

public class SstringConverter implements Converter {

    @Override
    public boolean canConvert(Class type) {
        return type.equals(Sstring.class);
    }

    @Override
    public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
        // Don't do anything
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        reader.moveDown();
        Sstring sstring = new Sstring();
        sstring.setId(Long.valueOf(reader.getValue()));

        reader.moveUp();
        reader.moveDown();
        System.out.println("--------------------------------------------------------1-");
        String b = reader.getValue();
        System.out.println(b);
//        //String b = reader.getValue().replaceAll("(&hellip;)", "...");
//        String b = reader.getValue().replaceAll("&", "...");
//        System.out.println(b);
        sstring.setFirstName(b);
        System.out.println("--------------------------------------------------------2-");
//        sstring.setFirstName(reader.getValue());

        reader.moveUp();
        reader.moveDown();
        sstring.setLastName(reader.getValue());

        reader.moveUp();
        reader.moveDown();

        return sstring;
    }
}
