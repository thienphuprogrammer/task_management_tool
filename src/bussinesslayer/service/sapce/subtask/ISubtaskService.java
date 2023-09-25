package bussinesslayer.service.sapce.subtask;

import bussinesslayer.entity.space.Subtask;
import bussinesslayer.service.IService;

public interface ISubtaskService extends IService<Subtask> {
    void viewById(int id) throws Exception;
    void viewAll() throws Exception;

    void assignSubtaskToMember(int subtaskId, int memberId);

    void reassignSubtaskToMember(int subtaskId, int memberId);

    void createSubtask(Subtask subtask);

    void viewSubtaskProject(int subtaskId, int taskId);

    void viewAllSubtaskProject(int taskId);
    void submitSubtask(int subtaskId);
}
