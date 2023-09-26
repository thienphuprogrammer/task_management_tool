package datalayer;

import bussinesslayer.entity.space.Subtask;
import bussinesslayer.entity.space.Task;

import java.util.List;

public interface IDao <T> {
    T getById(int id) throws Exception;
    List<T> getAll() throws Exception;
    void addNew(T space) throws Exception;
    void update(T space) throws Exception;
    void delete(int id) throws Exception;
}
