package bussinesslayer.service.report.reportproject;

import bussinesslayer.entity.report.ReportProject;
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
        ReportProject reportProject = reportProjectIDao.getById(id);
        if (reportProject == null) {
            throw new Exception("ReportProject is not exist");
        }
        return reportProject;
    }

    @Override
    public List<ReportProject> getAll() throws Exception {
        List<ReportProject> list = reportProjectIDao.getAll();
        if (list == null) {
            throw new Exception("ReportProject is not exist");
        }
        return list;
    }

    @Override
    public List<ReportProject> getReportsByProjectId(int projectId) throws Exception {
        List<ReportProject> reportProjectList = reportProjectIDao.getReportsByProjectId(projectId);
        if (reportProjectList == null) {
            throw new Exception("ReportProject is not exist");
        }
        return reportProjectList;
    }
}
