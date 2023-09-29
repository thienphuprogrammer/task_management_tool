package bussinesslayer.service.sapce.project;

import bussinesslayer.entity.space.Project;
import bussinesslayer.entity.user.Member;
import bussinesslayer.service.IService;

import java.util.List;

public interface IProjectService extends IService<Project> {
    void addMemberToProject(int projectId, int memberId, int managerId) throws Exception;

    void removeMemberFromProject(int projectId, int memberId, int managerId) throws Exception;

    List<Member> getAllMembersInProject(int projectId, int managerId) throws Exception;

    List<Project> getAllProjectsOfManager(int managerId) throws Exception;

    Project getProjectByMemberId(int projectId, int memberId);

    List<Project> getAllProjectsOfMember(int memberId);

    Member searchMemberInProject(int memberId, int projectId) throws Exception;
}
