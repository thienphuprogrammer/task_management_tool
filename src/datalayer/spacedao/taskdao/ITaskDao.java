package datalayer.spacedao.taskdao;

import bussinesslayer.entity.space.Task;
import bussinesslayer.entity.submission.SubmissionTask;
import datalayer.spacedao.ISpaceDao;

import java.util.List;

public interface ITaskDao extends ISpaceDao<Task> {
    List<Task> getAllTasksOfMember(int sprintId, int memberId);

    void submitTask(SubmissionTask submissionTask, int taskId);

    List<Task> getAllTaskBacklog(int backlogId);

    List<Task> getTasksMember(int sprintId, int memberId);

    List<Task> getTaskProgress(int sprintId);


    List<Task> getAllTasks(int sprintId);

    List<SubmissionTask> getSubmissionTaskByTaskId(int taskId) throws Exception;
}
