package datalayer.spacedao.sprintdao;

import bussinesslayer.entity.space.Sprint;
import datalayer.spacedao.ISpaceDao;

import java.util.List;

public interface ISprintDao extends ISpaceDao<Sprint> {
    List<Sprint> getAllSprintProject(int projectId);

    List<Sprint> getSprintMemberProject(int projectId, int memberId);
}
