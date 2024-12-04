package org.BBDDfilosofos.modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DAO <T>{
    T get(int id);
    List<T> getAll();
    ResultSet getCursor() throws SQLException;
    List<Integer> getAllIds();

}
