package main.entities.businesses.locationTypes;

import com.fasterxml.jackson.annotation.JsonTypeName;

import main.entities.users.User;
import org.bson.types.ObjectId;

@JsonTypeName("Family_Friendly")
public class FamilyFriendly extends Location {

    public FamilyFriendly() {
    }

    public FamilyFriendly(ObjectId id, String name, String genre, ObjectId menu) {
        super(id, name, genre, menu);
    }

    @Override
    public boolean customerVerification(User user) {
        return true;
    }

    @Override
    public void review() {

    }

}
