package bussinesslayer.service.sapce;

import bussinesslayer.service.IService;

public interface ISprintService<Sprint> extends IService<Sprint> {
    void viewById(int id) throws Exception;
    void viewAll() throws Exception;
}
