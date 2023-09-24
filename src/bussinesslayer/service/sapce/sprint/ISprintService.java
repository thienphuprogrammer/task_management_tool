package bussinesslayer.service.sapce.sprint;

import bussinesslayer.entity.space.Sprint;
import bussinesslayer.service.IService;

public interface ISprintService extends IService<Sprint> {
    void viewById(int id) throws Exception;
    void viewAll() throws Exception;
}
