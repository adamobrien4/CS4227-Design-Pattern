package main.entities.businesses.locationTypes;

import com.fasterxml.jackson.annotation.JsonTypeName;

import org.bson.types.ObjectId;

import main.entities.users.Customer;

@JsonTypeName("Verified_Only")
public class VerifiedOnly extends Location {

    @Override
    public boolean customerVerification(Customer c) {
        return false;
    }



    VerifiedOnly() {
    }

    public VerifiedOnly(ObjectId id, String name, String genre, ObjectId menu) {
        super(id, name, genre, menu);
    }

}
