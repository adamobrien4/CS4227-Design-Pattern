package main.entities.businesses.locationTypes;

import com.fasterxml.jackson.annotation.JsonTypeName;

import main.entities.users.User;
import org.bson.types.ObjectId;

import main.entities.users.Customer;

@JsonTypeName("Verified_Only")
public class VerifiedOnly extends Location {

    VerifiedOnly() {
    }

    public VerifiedOnly(ObjectId id, String name, String genre, ObjectId menu) {
        super(id, name, genre, menu);
    }

    @Override
    public boolean customerVerification(User user) {
        return false;
    }

    @Override
    public void review() {

    }

}
