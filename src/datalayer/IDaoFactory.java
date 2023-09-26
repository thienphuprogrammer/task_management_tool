package datalayer;

import datalayer.report.reportprojectdao.IReportProjectDao;
import datalayer.report.reporttaskdao.IReportTaskDao;
import datalayer.spacedao.backlogdao.IBacklogDao;
import datalayer.spacedao.projectdao.IProjectDao;
import datalayer.spacedao.sprintdao.ISprintDao;
import datalayer.spacedao.taskdao.ITaskDao;
import datalayer.user.admindao.IAdminDao;
import datalayer.user.managerdao.IManagerDao;
import datalayer.user.memberdao.IMemberDao;

public interface IDaoFactory {
    IBacklogDao getBacklogDao() throws Exception;

    IProjectDao getProjectDao() throws Exception;

    ISprintDao getSprintDao() throws Exception;

    ITaskDao getTaskDao() throws Exception;
    IAdminDao getAdminDao() throws Exception;

    IMemberDao getMemberDao() throws Exception;

    IManagerDao getManagerDao() throws Exception;

    IReportProjectDao getReportProjectDao() throws Exception;

    IReportTaskDao getReportTaskDao() throws Exception;
}