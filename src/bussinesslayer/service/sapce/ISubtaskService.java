package bussinesslayer.service.sapce;

import bussinesslayer.service.IService;

public interface ISubtaskService<Subtask> extends IService<Subtask> {
    void viewById(int id) throws Exception;
    void viewAll() throws Exception;
}
