package main.entities.serialisers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import main.Globals;
import main.entities.Order;
import main.services.POJOMapper;

import java.io.IOException;

public class CustomOrderSerialiser extends StdSerializer<Order> {

    public CustomOrderSerialiser() {
        this(null);
    }

    public CustomOrderSerialiser(Class<Order> t) {
        super(t);
    }

    @Override
    public void serialize(Order order, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        try {

            String driverValue;
            if(order.getDriver() == null) {
                driverValue = "Unset";
            } else {
                driverValue = order.getDriver().toHexString();
            }

            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("customer", Globals.getLoggedInUser().getId().toString());
            jsonGenerator.writeStringField("driver", driverValue);
            jsonGenerator.writeStringField("restaurant", order.getRestaurant().toHexString());
            jsonGenerator.writeStringField("status", order.getStatus());
            jsonGenerator.writeStringField("address", order.getAddress());
            jsonGenerator.writeStringField("totalCost", Double.toString(order.getTotalCost()));
            jsonGenerator.writeStringField("foodCost", Double.toString(order.getFoodCost()));
            jsonGenerator.writeStringField("deliveryCost", Double.toString(order.getDeliveryCost()));
            jsonGenerator.writeStringField("discountCode", order.getDiscountCode());
            jsonGenerator.writeStringField("discountAmount", Double.toString(order.getDiscountAmount()));
            jsonGenerator.writeStringField("orderItems", POJOMapper.getMapper().writeValueAsString(order.getOrderItems()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
