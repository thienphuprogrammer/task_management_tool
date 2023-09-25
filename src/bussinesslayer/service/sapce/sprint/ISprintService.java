package bussinesslayer.service.sapce.sprint;

import bussinesslayer.entity.space.Sprint;
import bussinesslayer.service.IService;

import java.util.List;

public interface ISprintService extends IService<Sprint> {
    void viewById(int id) throws Exception;
    void viewAll() throws Exception;
    List<Sprint> getAllSprintProject(int projectId);

    List<Sprint> getMySprintProject(int sprintId, int projectId);
}
