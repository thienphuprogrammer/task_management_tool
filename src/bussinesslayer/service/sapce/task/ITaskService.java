package bussinesslayer.service.sapce.task;

import bussinesslayer.entity.space.Task;
import bussinesslayer.entity.submission.SubmissionTask;
import bussinesslayer.service.IService;

import java.util.List;

public interface ITaskService extends IService<Task> {
    void submitTask(SubmissionTask submissionTask, int taskId);
    List<Task> getAllTasksOfMember(int sprintId, int memberId) throws Exception;
    List<Task> getAllTasks(int sprintId) throws Exception;

    List<SubmissionTask> getSubmissionTaskByTaskId(int taskId) throws Exception;
}
