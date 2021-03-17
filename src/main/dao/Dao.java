package main.dao;

import main.exceptions.APIException;

public interface Dao<T> {
    // Select by Id
    T get(String id);

    // Insert
    boolean insert(T t) throws APIException;

    // Update
    T update(T t) throws APIException;

    // Delete
    boolean delete(T t) throws APIException;
}
