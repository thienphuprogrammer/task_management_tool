package bussinesslayer.service.sapce.backog;

import bussinesslayer.entity.space.Backlog;
import bussinesslayer.service.IService;

public interface IBacklogService extends IService<Backlog> {
    void viewById(int id) throws Exception;
    void viewAll() throws Exception;
    void viewBacklogByProjectId(int projectId);
}
