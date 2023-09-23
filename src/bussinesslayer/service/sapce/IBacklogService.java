package bussinesslayer.service.sapce;

import bussinesslayer.service.IService;

public interface IBacklogService<Backlog> extends IService<Backlog> {
    void viewById(int id) throws Exception;
    void viewAll() throws Exception;
}
