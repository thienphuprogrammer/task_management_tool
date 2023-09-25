package bussinesslayer.service.sapce.task;

import bussinesslayer.entity.space.Task;
import bussinesslayer.service.IService;

import java.util.List;

public interface ITaskService extends IService<Task> {
    void viewById(int id) throws Exception;
    void viewAll() throws Exception;

    Task getTask(int taskId, int sprintId) throws Exception;

    List<Task> getAllTaskProject(int sprintId);

    void submitTask(int taskId);

    List<Task> getAllTask(int backlogId);

    List<Task> getAllMyTaskMember(int sprintId, int memberId);
}
