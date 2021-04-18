package main.entities.serialisers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.bson.types.ObjectId;

import java.io.IOException;

public class ObjectIdSerialiser extends JsonSerializer<ObjectId> {

    @Override
    public void serialize(ObjectId value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {
        System.out.println("ObjectId Serialise");
        System.out.println(value.toString());
        jgen.writeString(value.toString());
    }
}
