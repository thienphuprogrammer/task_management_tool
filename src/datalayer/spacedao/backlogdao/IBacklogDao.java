package datalayer.spacedao.backlogdao;

import bussinesslayer.entity.space.Backlog;
import datalayer.spacedao.ISpaceDao;
import java.util.List;

public interface IBacklogDao extends ISpaceDao<Backlog> {
    Backlog getBacklogByProjectId(int projectId) throws Exception;
}
