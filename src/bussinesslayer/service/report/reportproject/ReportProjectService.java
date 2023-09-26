package bussinesslayer.service.report.reportproject;

import bussinesslayer.entity.report.ReportProject;
import bussinesslayer.service.report.IReportService;
import datalayer.DaoFactory;
import datalayer.IDaoFactory;
import datalayer.report.reportprojectdao.IReportProjectDao;

import java.util.List;

public class ReportProjectService implements IReportProjectService {
    IReportProjectDao reportProjectIDao;
    IDaoFactory projectDaoFactory;
    public ReportProjectService() throws Exception {
        projectDaoFactory = new DaoFactory();
        reportProjectIDao = projectDaoFactory.getReportProjectDao();
    }
    public IReportProjectDao getReportProjectIDao() {
        return reportProjectIDao;
    }
    @Override
    public void update(ReportProject reportProject) throws Exception {
        reportProjectIDao.update(reportProject);
    }

    @Override
    public void create(ReportProject reportProject) throws Exception {
        reportProjectIDao.addNew(reportProject);
    }

    @Override
    public void delete(int id) throws Exception {
        reportProjectIDao.delete(id);
    }

    @Override
    public ReportProject getById(int id) throws Exception {
        return reportProjectIDao.getById(id);
    }

    @Override
    public List<ReportProject> getAll() throws Exception {
        return reportProjectIDao.getAll();
    }

    @Override
    public void viewById(int id) throws Exception {

    }

    @Override
    public void viewAll() throws Exception {

    }
}
