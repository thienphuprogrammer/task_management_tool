package bussinesslayer.service.sapce.task;

import bussinesslayer.entity.space.Task;
import bussinesslayer.service.IService;

import java.util.List;

public interface ITaskService extends IService<Task> {
    void submitTask(int taskId);
    List<Task> getAllTasksOfMember(int sprintId, int memberId);
    List<Task> getAllTasks(int sprintId);
}
