package org.BBDD;

import java.util.List;

public interface DAO <T>{
    T get(int id);
    List<T> getAll();
    void save(T t);
    void update(T t);
    void delete(T t);
    boolean deleteById(int id);
    List<Integer> getAllIds();
    void updateLOB(T book, String f);
    void updateLOBById(int id, String f);
    void deleteAll();
}
