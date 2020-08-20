package com.banulp.toy.batch;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

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
        sstring.setFirstName(reader.getValue());

        reader.moveUp();
        reader.moveDown();
        sstring.setLastName(reader.getValue());

        reader.moveUp();
        reader.moveDown();

        return sstring;
    }
}
