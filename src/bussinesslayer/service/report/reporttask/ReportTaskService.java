package bussinesslayer.service.report.reporttask;

import bussinesslayer.entity.report.ReportTask;
import bussinesslayer.service.report.IReportService;
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
    public IReportTaskDao getReportTaskIDao() {
        return reportTaskIDao;
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
        return reportTaskIDao.getById(id);
    }

    @Override
    public List<ReportTask> getAll() throws Exception {
        return reportTaskIDao.getAll();
    }

    @Override
    public List<ReportTask> getTaskProgress(int sprintId) {
        return reportTaskIDao.getTaskProgress(sprintId);
    }

    @Override
    public List<ReportTask> getReportsBySprintId(int sprintId) {
        return reportTaskIDao.getReports(sprintId);
    }
}
