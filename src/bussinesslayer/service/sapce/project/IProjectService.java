package bussinesslayer.service.sapce.project;

import bussinesslayer.entity.space.Project;
import bussinesslayer.service.IService;

public interface IProjectService extends IService<Project> {
    void viewById(int id) throws Exception;
    void viewAll() throws Exception;
}
