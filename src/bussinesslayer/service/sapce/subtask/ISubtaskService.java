package bussinesslayer.service.sapce.subtask;

import bussinesslayer.entity.space.SubTask;
import bussinesslayer.service.IService;

public interface ISubtaskService extends IService<SubTask> {
    void viewById(int id) throws Exception;
    void viewAll() throws Exception;
}
