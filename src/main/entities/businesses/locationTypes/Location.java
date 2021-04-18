package main.entities.businesses.locationTypes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import org.bson.types.ObjectId;

@JsonPropertyOrder({ "_id", "name", "menu", "genre" })
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @Type(value = FamilyFriendly.class, name = "Family_Friendly"),
        @Type(value = Over18s.class, name = "Over_18"), @Type(value = VerifiedOnly.class, name = "Verified_Only") })

public abstract class Location {

    @JsonProperty("_id")
    private ObjectId id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("menu")
    private ObjectId menu;
    @JsonProperty("genre")
    private String genre;

    public Location() {
    }

    @JsonCreator
    public Location(@JsonProperty("_id") ObjectId i_id, @JsonProperty("name") String i_name,
            @JsonProperty("genre") String i_genre, @JsonProperty("menu") ObjectId i_menu) {
        this.id = i_id;
        this.name = i_name;
        this.genre = i_genre;
        this.menu = i_menu;
    }

    public ObjectId getMenuId() {
        return menu;
    }

    public void setMenu(ObjectId menu) {
        this.menu = menu;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return this.genre;
    }

    public ObjectId getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Location{" + "id=" + id + ", name='" + name + '\'' + ", menu=" + menu + ", genre='" + genre + '\''
                + '}';
    }

    public abstract boolean customerVerification();

    public abstract void review();

}
