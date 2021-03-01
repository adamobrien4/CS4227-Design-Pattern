package main.dao;

public interface Dao<T> {
    // Select by Id
    T get(String id);

    // Insert
    boolean insert(T t);

    // Update
    boolean update(T t);

    // Delete
    boolean delete(T t);
}
