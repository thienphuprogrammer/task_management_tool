package datalayer.spacedao.projectdao;

import bussinesslayer.entity.space.Project;
import bussinesslayer.entity.user.Member;
import datalayer.spacedao.ISpaceDao;

import java.util.List;

public interface IProjectDao extends ISpaceDao<Project> {
    void addMemberToProject(int projectId, int memberId);

    void removeMemberFromProject(int projectId, int memberId);

    List<Member> getAllMemberProject(int projectId, int managerId);

    List<Project> getAllProject(int userId);

    Project getProjectByMemberId(int projectId, int memberId);

    List<Project> getAllProjectMember(int memberId);

    Member searchMemberInProject(int projectId, int memberId) throws Exception;
}
