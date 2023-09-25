package datalayer.spacedao.substaskdao;

import bussinesslayer.entity.space.Subtask;
import datalayer.spacedao.ISpaceDao;

import java.util.List;

public interface ISubtaskDao extends ISpaceDao<Subtask> {
    List<Subtask> getAllSubtaskProject(int subtaskId);

    List<Subtask> getAllMySubtask(int memberId, int taskId);

    List<Subtask> getAllSubtask(int taskId);

    List<Subtask> getAllMySubtaskProject(int taskId, int memberId);

    void submitSubtask(int subtaskId);
}
