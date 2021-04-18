package main.entities.businesses.locationTypes;

import com.fasterxml.jackson.annotation.JsonTypeName;

import org.bson.types.ObjectId;

import main.entities.users.Customer;

@JsonTypeName("Family_Friendly")
public class FamilyFriendly extends Location {

    public FamilyFriendly() {
    }

    @Override
    public boolean customerVerification(Customer c) {
        return true;
    }



    public FamilyFriendly(ObjectId id, String name, String genre, ObjectId menu) {
        super(id, name, genre, menu);
    }

}
