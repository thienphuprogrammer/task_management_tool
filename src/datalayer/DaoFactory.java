package datalayer;

import bussinesslayer.entity.report.ReportBacklog;
import bussinesslayer.entity.report.ReportProject;
import bussinesslayer.entity.report.ReportSprint;
import bussinesslayer.entity.report.ReportTask;
import bussinesslayer.entity.space.*;
import bussinesslayer.entity.user.Admin;
import bussinesslayer.entity.user.Manager;
import bussinesslayer.entity.user.Member;
import datalayer.report.reportbacklogdao.ReportBacklogDao;
import datalayer.report.reportprojectdao.ReportProjectDao;
import datalayer.report.reportsprintdao.ReportSprintDao;
import datalayer.report.reporttaskdao.ReportTaskDao;
import datalayer.spacedao.backlogdao.BacklogDao;
import datalayer.spacedao.projectdao.ProjectDao;
import datalayer.spacedao.sprintdao.SprintDao;
import datalayer.spacedao.substaskdao.SubTaskDao;
import datalayer.spacedao.taskdao.TaskDao;
import datalayer.user.admindao.AdminDao;
import datalayer.user.managerdao.ManagerDao;
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
    public IDao<Backlog> getBacklogDao() throws Exception {
        return new BacklogDao();
    }

    @Override
    public IDao<Project> getProjectDao() throws Exception {
        return new ProjectDao();
    }

    @Override
    public IDao<Sprint> getSprintDao() throws Exception {
        return new SprintDao();
    }

    @Override
    public IDao<Task> getTaskDao() throws Exception {
        return new TaskDao();
    }

    @Override
    public IDao<SubTask> getSubTaskDao() throws Exception {
        return new SubTaskDao();
    }

    @Override
    public IDao<Admin> getAdminDao() throws Exception {
        return new AdminDao();
    }

    @Override
    public IDao<Member> getMemberDao() throws Exception {
        return new MemberDao();
    }

    @Override
    public IDao<Manager> getManagerDao() throws Exception {
        return new ManagerDao();
    }

    @Override
    public IDao<ReportBacklog> getReportBacklogDao() throws Exception {
        return new ReportBacklogDao();
    }

    @Override
    public IDao<ReportProject> getReportProjectDao() throws Exception {
        return new ReportProjectDao();
    }

    @Override
    public IDao<ReportTask> getReportTaskDao() throws Exception {
        return new ReportTaskDao();
    }

    @Override
    public IDao<ReportSprint> getReportSprintDao() throws Exception {
        return new ReportSprintDao();
    }
}
