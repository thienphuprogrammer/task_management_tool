package bussinesslayer.service.sapce.project;

import bussinesslayer.entity.space.Project;
import bussinesslayer.entity.user.Member;
import bussinesslayer.service.IService;

import java.util.List;

public interface IProjectService extends IService<Project> {
    void viewById(int id) throws Exception;
    void viewAll() throws Exception;

    void addMemberToProject(int projectId, int memberId, int managerId) throws Exception;

    void removeMemberFromProject(int projectId, int memberId, int managerId) throws Exception;

    List<Member> getMember(int projectId, int managerId) throws Exception;
    List<Project> getProjectMember(int memberId) throws Exception;

    List<Member> getAllMember(int projectId, int memberId) throws Exception;
    List<Project> getAllProjectManager(int managerId) throws Exception;

    Project getMemberByProjectId(int projectId, int memberId);

    List<Project> getAllProjectMember(int memberId);
}
