package bussinesslayer.service.sapce.subtask;

import bussinesslayer.entity.space.Subtask;
import bussinesslayer.service.IService;

import java.util.List;

public interface ISubtaskService extends IService<Subtask> {
    void viewById(int id) throws Exception;
    void viewAll() throws Exception;

    void assignSubtaskToMember(int subtaskId, int memberId, int taskId) throws Exception;

    void reassignSubtaskToMember(int subtaskId, int memberId, int taskId) throws Exception;

    void submitSubtask(int subtaskId);

    List<Subtask> getAllSubtask(int taskId);

    List<Subtask> getAllMySubtaskProject(int taskId, int memberId);

    void getAllSubtaskProject(int taskId);
}
