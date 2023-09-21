package datalayer.spacedao.backlogdao;

import datalayer.spacedao.ISpaceDao;
import java.util.List;

public interface IBacklogDao<Backlog> extends ISpaceDao<Backlog> {
    @Override
    Backlog getById(int id) throws Exception;

    @Override
    List<Backlog> getAll() throws Exception;

    @Override
    void addNew(Backlog space) throws Exception;

    @Override
    void update(Backlog space) throws Exception;

    @Override
    void delete(Backlog space) throws Exception;

    @Override
    void save(Backlog space) throws Exception;
}
