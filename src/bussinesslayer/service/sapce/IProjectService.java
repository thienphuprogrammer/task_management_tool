package bussinesslayer.service.sapce;

import bussinesslayer.service.IService;

public interface IProjectService<Project> extends IService<Project> {
    void viewById(int id) throws Exception;
    void viewAll() throws Exception;
}
