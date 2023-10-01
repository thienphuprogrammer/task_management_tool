package bussinesslayer.service.sapce.backog;

import bussinesslayer.entity.space.Backlog;
import bussinesslayer.entity.space.Sprint;
import bussinesslayer.entity.space.Task;
import bussinesslayer.service.IService;

import java.util.List;

public interface IBacklogService extends IService<Backlog> {
    void addTaskInBacklogToSprint(int backlogId, int taskId, int sprintId) throws Exception;

    void createTaskInBacklog(Task task) throws Exception;

    Task getTaskInBacklogByTaskId(int taskId) throws Exception;

    void updateTaskInBacklog(Task task) throws Exception;

    void deleteTaskInBacklog(int taskId) throws Exception;

    List<Task> getAllTasksInBacklog(int backlogId) throws Exception;

    Sprint getSprintBySprintId(int sprintId) throws Exception;

    Backlog getBacklogByProjectId(int projectId) throws Exception;
}
