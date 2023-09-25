package bussinesslayer.service.sapce.project;

import bussinesslayer.entity.space.Project;
import bussinesslayer.service.IService;

public interface IProjectService extends IService<Project> {
    void viewById(int id) throws Exception;
    void viewAll() throws Exception;

    void addMemberToProject(int projectId, int memberId);

    void removeMemberFromProject(int projectId, int memberId);

    void viewMember();

    void viewReport();
    
    void getDocByProjectId(int memberId);

    void viewProjectMember(int projectId, int memberId);

    void viewAllMember(int memberId);
}
