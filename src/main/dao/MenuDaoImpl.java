package main.dao;

import main.entities.Menu;
import main.exceptions.APIException;

public class MenuDaoImpl implements Dao<Menu> {
    @Override
    public Menu get(String id) {
        return null;
    }

    @Override
    public boolean insert(Menu menu) throws APIException {
        return false;
    }

    @Override
    public Menu update(Menu menu) throws APIException {
        return null;
    }

    @Override
    public boolean delete(Menu menu) throws APIException {
        return false;
    }
}
