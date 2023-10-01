package bussinesslayer.service.report.reporttask;

import bussinesslayer.entity.report.ReportTask;
import datalayer.DaoFactory;
import datalayer.IDaoFactory;
import datalayer.report.reporttaskdao.IReportTaskDao;

import java.util.List;

public class ReportTaskService implements IReportTaskService {
    IReportTaskDao reportTaskIDao;
    IDaoFactory taskDaoFactory;
    public ReportTaskService() throws Exception {
        taskDaoFactory = new DaoFactory();
        reportTaskIDao = taskDaoFactory.getReportTaskDao();
    }

    @Override
    public void update(ReportTask reportTask) throws Exception {
        reportTaskIDao.update(reportTask);
    }

    @Override
    public void create(ReportTask reportTask) throws Exception {
        reportTaskIDao.addNew(reportTask);
    }

    @Override
    public void delete(int id) throws Exception {
        reportTaskIDao.delete(id);
    }

    @Override
    public ReportTask getById(int id) throws Exception {
        ReportTask reportTask = reportTaskIDao.getById(id);
        if (reportTask == null) {
            throw new Exception("ReportTask is not exist");
        }
        return reportTask;
    }

    @Override
    public List<ReportTask> getAll() throws Exception {
        List<ReportTask> list = reportTaskIDao.getAll();
        if (list == null) {
            throw new Exception("ReportTask is not exist");
        }
        return list;
    }
    @Override
    public List<ReportTask> getReportsBySprintId(int sprintId) throws Exception {
        List<ReportTask> reportTaskList = reportTaskIDao.getReportsBySprintId(sprintId);
        if (reportTaskList == null) {
            throw new Exception("ReportTask is not exist");
        }
        return reportTaskList;
    }

    @Override
    public List<ReportTask> getReportsByTaskId(int taskId) throws Exception {
        List<ReportTask> reportTaskList = reportTaskIDao.getReportsByTaskId(taskId);
        if (reportTaskList == null) {
            throw new Exception("ReportTask is not exist");
        }
        return reportTaskList;
    }
}
