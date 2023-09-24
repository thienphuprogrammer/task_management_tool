package bussinesslayer.service.sapce.task;

import bussinesslayer.entity.space.Task;
import bussinesslayer.service.IService;

public interface ITaskService extends IService<Task> {
    void viewById(int id) throws Exception;
    void viewAll() throws Exception;
}
