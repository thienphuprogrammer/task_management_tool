package datalayer;

import datalayer.spacedao.backlogdao.IBacklogDao;
import datalayer.spacedao.projectdao.IProjectDao;
import datalayer.spacedao.sprintdao.ISprintDao;
import datalayer.spacedao.substaskdao.ISubTaskDao;
import datalayer.spacedao.taskdao.ITaskDao;

import java.sql.Connection;

public interface IDaoFactory {
    IBacklogDao getBacklogDao() throws Exception;
    IProjectDao getProjectDao() throws Exception;
    ISprintDao getSprintDao() throws Exception;
    ITaskDao getTaskDao() throws Exception;
    ISubTaskDao getSubTaskDao() throws Exception;
}
