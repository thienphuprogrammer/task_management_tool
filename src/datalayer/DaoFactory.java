package datalayer;

import datalayer.spacedao.backlogdao.BacklogDao;
import datalayer.spacedao.backlogdao.IBacklogDao;
import datalayer.spacedao.projectdao.IProjectDao;
import datalayer.spacedao.projectdao.ProjectDao;
import datalayer.spacedao.sprintdao.ISprintDao;
import datalayer.spacedao.sprintdao.SprintDao;
import datalayer.spacedao.substaskdao.ISubTaskDao;
import datalayer.spacedao.substaskdao.SubTaskDao;
import datalayer.spacedao.taskdao.ITaskDao;
import datalayer.spacedao.taskdao.TaskDao;

import java.sql.Connection;

public class DaoFactory implements IDaoFactory {
    // -------------------- Properties ------------------------
    private Connection connection;
    private IFileManager fileManager;

    // -------------------- Constructor ------------------------
    public DaoFactory() {

    }

    public DaoFactory(Connection connection, IFileManager fileManager) {
        this.connection = connection;
        this.fileManager = fileManager;
    }
    // -------------------- Getters and Setters ------------------------
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public IFileManager getFileManager() {
        return fileManager;
    }

    public void setFileManager(IFileManager fileManager) {
        this.fileManager = fileManager;
    }

    // -------------------- Methods ------------------------

    @Override
    public IBacklogDao getBacklogDao() throws Exception {
        return new BacklogDao();
    }

    @Override
    public IProjectDao getProjectDao() throws Exception {
        return new ProjectDao();
    }

    @Override
    public ISprintDao getSprintDao() throws Exception {
        return new SprintDao();
    }

    @Override
    public ITaskDao getTaskDao() throws Exception {
        return new TaskDao();
    }

    @Override
    public ISubTaskDao getSubTaskDao() throws Exception {
        return new SubTaskDao();
    }
}
