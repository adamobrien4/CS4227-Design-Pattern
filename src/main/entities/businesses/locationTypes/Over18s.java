package main.entities.businesses.locationTypes;

import com.fasterxml.jackson.annotation.JsonTypeName;

import main.entities.users.User;
import org.bson.types.ObjectId;

import main.entities.users.Customer;

@JsonTypeName("Over_18")
public class Over18s extends Location {

    public Over18s() {
    }

    public Over18s(ObjectId id, String name, String genre, ObjectId menu) {
        super(id, name, genre, menu);
    }

    @Override
    public boolean customerVerification(User user) {
        System.out.println("Checking Age...");
        return true;
    }

    @Override
    public void review() {

    }
}
