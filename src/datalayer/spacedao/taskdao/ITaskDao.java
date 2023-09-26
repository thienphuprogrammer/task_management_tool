package datalayer.spacedao.taskdao;

import bussinesslayer.entity.space.Task;
import datalayer.spacedao.ISpaceDao;

import java.util.List;

public interface ITaskDao extends ISpaceDao<Task> {
    List<Task> getAllMyTaskMember(int sprintId, int memberId);

    void submitTask(int taskId);

    List<Task> getAllTaskBacklog(int backlogId);

    List<Task> getTasksMember(int sprintId, int memberId);

    List<Task> getTaskProgress(int sprintId);

    List<Task> getAllTasksMamager(int sprintId);

    List<Task> getAllTasks(int sprintId);
}
