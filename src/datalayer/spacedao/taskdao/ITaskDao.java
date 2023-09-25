package datalayer.spacedao.taskdao;

import bussinesslayer.entity.space.Task;
import datalayer.spacedao.ISpaceDao;

import java.util.List;

public interface ITaskDao extends ISpaceDao<Task> {
    List<Task> getAllMyTaskMember(int sprintId, int memberId);

    void submitTask(int taskId);

    List<Task> getAllTask(int backlogId);

    List<Task> getAllTaskProject(int sprintId);
}
