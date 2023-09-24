package bussinesslayer.service.sapce.subtask;

import bussinesslayer.entity.space.SubTask;
import bussinesslayer.service.IService;

public interface ISubTaskService extends IService<SubTask> {
    void viewById(int id) throws Exception;
    void viewAll() throws Exception;
}
