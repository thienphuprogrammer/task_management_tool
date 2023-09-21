package datalayer.spacedao;

import java.util.List;

public interface ISpaceDao <T>{
    T getById(int id) throws Exception;
    List<T> getAll() throws Exception;
    void addNew(T space) throws Exception;
    void update(T space) throws Exception;
    void delete(T space) throws Exception;
    void save(T space) throws Exception;
}
