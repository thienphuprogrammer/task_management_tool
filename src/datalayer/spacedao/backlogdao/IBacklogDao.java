package datalayer.spacedao.backlogdao;

import bussinesslayer.entity.space.Backlog;
import bussinesslayer.entity.space.Sprint;
import bussinesslayer.entity.space.Task;
import datalayer.spacedao.ISpaceDao;
import java.util.List;

public interface IBacklogDao extends ISpaceDao<Backlog> {
    Backlog getBacklogByProjectId(int projectId) throws Exception;

    List<Task> getTasksInBacklog(int backlogId);

    void addTaskInBacklogToSprint(int backlogId, int taskId, int sprintId) throws Exception;

    void createTaskInBacklog(Task task) throws Exception;

    Task getTaskInBacklogByTaskId(int taskId) throws Exception;

    void updateTaskInBacklog(Task task) throws Exception;

    void deleteTaskInBacklog(int taskId) throws Exception;

    List<Task> getAllTasksInBacklog(int backlogId) throws Exception;

    Sprint getSprintBySprintId(int sprintId) throws Exception;
}
