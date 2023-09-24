package datalayer;

import bussinesslayer.entity.report.ReportSprint;
import bussinesslayer.entity.report.ReportTask;
import datalayer.report.reportbacklogdao.IReportBacklogDao;
import datalayer.report.reportbacklogdao.ReportBacklogDao;
import datalayer.report.reportprojectdao.IReportProjectDao;
import datalayer.report.reportprojectdao.ReportProjectDao;
import datalayer.report.reportsprintdao.IReportSprintDao;
import datalayer.report.reportsprintdao.ReportSprintDao;
import datalayer.report.reporttaskdao.IReportTaskDao;
import datalayer.report.reporttaskdao.ReportTaskDao;
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
import datalayer.user.admindao.AdminDao;
import datalayer.user.admindao.IAdminDao;
import datalayer.user.managerdao.IManagerDao;
import datalayer.user.managerdao.ManagerDao;
import datalayer.user.memberdao.IMemberDao;
import datalayer.user.memberdao.MemberDao;

public class DaoFactory implements IDaoFactory {
    // -------------------- Properties ------------------------
    private IFileManager fileManager;

    // -------------------- Constructor ------------------------
    public DaoFactory() {

    }
    // -------------------- Getters and Setters ------------------------
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

    @Override
    public IAdminDao getAdminDao() throws Exception {
        return new AdminDao();
    }

    @Override
    public IMemberDao getMemberDao() throws Exception {
        return new MemberDao();
    }

    @Override
    public IManagerDao getManagerDao() throws Exception {
        return new ManagerDao();
    }

    @Override
    public IReportBacklogDao getReportBacklogDao() throws Exception {
        return new ReportBacklogDao();
    }

    @Override
    public IReportProjectDao getReportProjectDao() throws Exception {
        return new ReportProjectDao();
    }

    @Override
    public IReportTaskDao getReportTaskDao() throws Exception {
        return new ReportTaskDao();
    }

    @Override
    public IReportSprintDao getReportSprintDao() throws Exception {
        return new ReportSprintDao();
    }
}
