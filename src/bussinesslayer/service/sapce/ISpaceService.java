package bussinesslayer.service.sapce;

import bussinesslayer.service.IService;

public interface ISpaceService<T> extends IService<T> {
    public void viewById(int id) throws Exception;
    public void viewAll() throws Exception;
}
