package bussinesslayer.service.sapce.sprint;

import bussinesslayer.entity.space.Sprint;
import bussinesslayer.service.IService;

import java.util.List;

public interface ISprintService extends IService<Sprint> {
    void viewById(int id) throws Exception;
    void viewAll() throws Exception;
    List<Sprint> getAllSprint(int projectId);

    List<Sprint> getSprintMember(int memberId);
}
