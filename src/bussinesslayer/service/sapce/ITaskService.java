package bussinesslayer.service.sapce;

import bussinesslayer.service.IService;

public interface ITaskService<Task> extends IService<Task> {
    void viewById(int id) throws Exception;
    void viewAll() throws Exception;
}
