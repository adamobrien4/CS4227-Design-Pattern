package main.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import main.entities.Discount;
import main.entities.Order;
import main.entities.serialisers.CustomOrderSerialiser;
import main.entities.serialisers.DiscountSerialiser;
import main.entities.serialisers.ObjectIdSerialiser;
import org.bson.types.ObjectId;

public class POJOMapper extends ObjectMapper {

    private static final POJOMapper instance = new POJOMapper();

    private POJOMapper() {

        // Register ObjectId serialiser
        SimpleModule objectIdmodule = new SimpleModule("ObjectIdSerialiser");
        objectIdmodule.addSerializer(ObjectId.class, new ObjectIdSerialiser());
        this.registerModule(objectIdmodule);

        // Register custom order serialiser
        SimpleModule orderModule = new SimpleModule("CustomOrderSerialiser");
        orderModule.addSerializer(Order.class, new CustomOrderSerialiser());
        this.registerModule(orderModule);

        // Register Discount serialiser
        SimpleModule discountModule = new SimpleModule("DiscountSerialiser");
        discountModule.addSerializer(Discount.class, new DiscountSerialiser());
        this.registerModule(discountModule);

        this.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public static POJOMapper getMapper() {
        return instance;
    }
}
