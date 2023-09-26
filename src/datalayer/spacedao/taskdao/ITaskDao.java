package datalayer.spacedao.taskdao;

import bussinesslayer.entity.space.Task;
import datalayer.spacedao.ISpaceDao;

import java.util.List;

public interface ITaskDao extends ISpaceDao<Task> {
    List<Task> getAllMyTaskMember(int sprintId, int memberId);

    void submitTask(int taskId);

    List<Task> getAllTask(int backlogId);

    List<Task> getTasks(int sprintId, int memberId);

    List<Task> getTaskProgress(int sprintId);
}
