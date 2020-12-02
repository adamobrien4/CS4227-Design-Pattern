package entities;

interface User {
    // Stringify the entity
    public String toString();

    // Convert the entity to Json format
    public String toJson();

    // Create a new entity given a Json string
    public void fromJson();
}
