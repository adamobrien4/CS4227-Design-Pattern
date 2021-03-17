package main.entities.serialisers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import main.entities.Discount;

import java.io.IOException;

public class DiscountSerialiser extends StdSerializer<Discount> {
    public DiscountSerialiser() {
        this(null);
    }

    public DiscountSerialiser(Class<Discount> t) {
        super(t);
    }

    @Override
    public void serialize(Discount discount, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        try {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("amount", Double.toString(discount.getAmount()));
            jsonGenerator.writeStringField("code", discount.getCode());
            jsonGenerator.writeStringField("type", Integer.toString(discount.getType()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
