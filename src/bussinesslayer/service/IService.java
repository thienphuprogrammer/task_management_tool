package bussinesslayer.service;

import java.util.List;

public interface IService<T> {
    public void update(T t) throws Exception;
    public void create(T t) throws Exception;
    public void delete(int id) throws Exception;
    public T getById(int id) throws Exception;
    public List<T> getAll() throws Exception;
}
