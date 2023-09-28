package bussinesslayer.service.sapce.backog;

import bussinesslayer.entity.space.Backlog;
import bussinesslayer.entity.space.Task;
import bussinesslayer.service.IService;

import java.util.List;

public interface IBacklogService extends IService<Backlog> {
    void viewById(int id) throws Exception;
    void addTaskInBacklogToSprint(int backlogId, int taskId, int sprintId);

    void createTaskInBacklog(Task task);

    Task getTaskInBacklogByTaskId(int taskId);

    void updateTaskInBacklog(Task task);

    void deleteTaskInBacklog(int taskId);

    List<Task> getAllTasksInBacklog(int backlogId);
}
