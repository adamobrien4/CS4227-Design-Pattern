package main.entities;

import org.bson.Document;

public interface User {
    // Stringify the entity
    public String toString();

    // Convert the entity to Json format
    public String toJson();

    // TODO : Define the static fromDocument() function here later
}
