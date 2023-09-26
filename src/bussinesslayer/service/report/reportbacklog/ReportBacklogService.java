package bussinesslayer.service.report.reportbacklog;

import bussinesslayer.entity.report.ReportBacklog;
import datalayer.DaoFactory;
import datalayer.IDaoFactory;
import datalayer.report.reportbacklogdao.IReportBacklogDao;

import java.util.List;

public class ReportBacklogService implements IReportBacklogService {
    IReportBacklogDao reportBacklogIDao;
    IDaoFactory backlogDaoFactory;

    public ReportBacklogService() throws Exception {
        backlogDaoFactory = new DaoFactory();
        reportBacklogIDao = backlogDaoFactory.getReportBacklogDao();
    }

    public IReportBacklogDao getReportBacklogIDao() {
        return reportBacklogIDao;
    }

    public void setReportBacklogIDao(IReportBacklogDao reportBacklogIDao) {
        this.reportBacklogIDao = reportBacklogIDao;
    }

    @Override
    public void update(ReportBacklog reportBacklog) throws Exception {
        reportBacklogIDao.update(reportBacklog);
    }

    @Override
    public void create(ReportBacklog reportBacklog) throws Exception {
        reportBacklogIDao.addNew(reportBacklog);
    }

    @Override
    public void delete(int id) throws Exception {
        reportBacklogIDao.delete(id);
    }

    @Override
    public ReportBacklog getById(int id) throws Exception {
        return reportBacklogIDao.getById(id);
    }

    @Override
    public List<ReportBacklog> getAll() throws Exception {
        return reportBacklogIDao.getAll();
    }

    @Override
    public List<ReportBacklog> getReport(int backlogId) throws Exception {
        return reportBacklogIDao.getReport(backlogId);
    }

    @Override
    public void updateReportBacklog(ReportBacklog reportBacklog) throws Exception {
            reportBacklogIDao.update(reportBacklog);
    }

    @Override
    public List<ReportBacklog> getAllReport(int projectId) throws Exception {
        return reportBacklogIDao.getAllReport(projectId);
    }
}
