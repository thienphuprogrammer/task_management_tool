package datalayer.spacedao.projectdao;

import bussinesslayer.entity.space.Project;
import bussinesslayer.entity.user.Member;
import datalayer.spacedao.ISpaceDao;

import java.util.List;

public interface IProjectDao extends ISpaceDao<Project> {
    void addMemberToProject(int projectId, int memberId, int managerId);

    void removeMemberFromProject(int projectId, int memberId, int managerId);

    List<Member> getAllMemberProject(int projectId, int managerId);

    List<Project> getAllProject(int userId);
}
