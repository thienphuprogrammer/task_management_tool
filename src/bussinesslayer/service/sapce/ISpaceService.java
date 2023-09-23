package bussinesslayer.service.sapce;

import bussinesslayer.service.IService;

public interface ISpaceService<T> extends IService<T> {
    void viewById(int id) throws Exception;
    void viewAll() throws Exception;
}
