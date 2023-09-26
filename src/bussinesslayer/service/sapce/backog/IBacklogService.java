package bussinesslayer.service.sapce.backog;

import bussinesslayer.entity.space.Backlog;
import bussinesslayer.entity.space.Task;
import bussinesslayer.service.IService;

import java.util.List;

public interface IBacklogService extends IService<Backlog> {
    void viewById(int id) throws Exception;
    void viewAll() throws Exception;

    List<Backlog> getAllBacklogInProject(int projectId);

    List<Task> getTasksInBacklog(int backlogId);
}
