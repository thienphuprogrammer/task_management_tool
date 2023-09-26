package bussinesslayer.service.sapce.task;

import bussinesslayer.entity.space.Task;
import bussinesslayer.service.IService;

import java.util.List;

public interface ITaskService extends IService<Task> {
    void submitTask(int taskId);

    List<Task> getAllTask(int backlogId);

    List<Task> getAllMyTaskMember(int sprintId, int memberId);

    List<Task> getTasks(int sprintId, int memberId);

    List<Task> getTaskProgress(int sprintId);

    List<Task> getAllTasksMamager(int sprintId);
}
