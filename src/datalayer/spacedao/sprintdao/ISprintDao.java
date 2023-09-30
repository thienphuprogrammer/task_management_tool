package datalayer.spacedao.sprintdao;

import bussinesslayer.entity.space.Sprint;
import datalayer.spacedao.ISpaceDao;

import java.util.List;

public interface ISprintDao extends ISpaceDao<Sprint> {
    List<Sprint> getAllSprintsOfProject(int projectId);

    List<Sprint> getAllSprintsInProjectOfMember(int projectId, int memberId);
}
