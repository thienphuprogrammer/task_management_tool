package bussinesslayer.service.sapce;

import bussinesslayer.service.IService;

import java.util.List;

public interface ISpaceService<T> extends IService<T> {
    void viewById(int id) throws Exception;
    void viewAll() throws Exception;

    @Override
    void update(T t) throws Exception;

    @Override
    void create(T t) throws Exception;

    @Override
    void delete(int id) throws Exception;

    @Override
    T getById(int id) throws Exception;

    @Override
    List<T> getAll() throws Exception;
}
