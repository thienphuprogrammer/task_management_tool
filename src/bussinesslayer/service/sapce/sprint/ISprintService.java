package bussinesslayer.service.sapce.sprint;

import bussinesslayer.entity.space.Sprint;
import bussinesslayer.service.IService;

import java.util.List;

public interface ISprintService extends IService<Sprint> {
    List<Sprint> getAllSprintsOfProject(int projectId) throws Exception;

    List<Sprint> getAllSprintsInProjectOfMember(int projectId, int memberId) throws Exception;
}
