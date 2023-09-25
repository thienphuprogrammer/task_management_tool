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
    public List<ReportBacklog> viewReport(int backlogId) throws Exception {

        return null;
    }

    @Override
    public void updateReportBacklog(ReportBacklog reportBacklog) {

    }

    @Override
    public void viewById(int id) throws Exception {
        ReportBacklog reportBacklog = reportBacklogIDao.getById(id);
        System.out.println("| id: " + reportBacklog.getId() + " ".repeat(40 - String.valueOf(reportBacklog.getId()).length()) + "|");
        System.out.println("| Date: " + reportBacklog.getDate() + " ".repeat(40 - String.valueOf(reportBacklog.getDate()).length()) + "|");
        System.out.println("| Time: " + reportBacklog.getTime() + " ".repeat(36 - String.valueOf(reportBacklog.getDescription()).length()) + "|");
//        System.out.println("| File URL: " + reportBacklog.getDescription() + " ".repeat(43 - String.valueOf(reportBacklog.getFileURL()).length()) + "|");
    }

    @Override
    public void viewAll() throws Exception {
//        ReportBacklog reportBacklog = reportBacklogIDao.getById();
    }

}
