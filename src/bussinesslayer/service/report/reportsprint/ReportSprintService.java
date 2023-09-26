package bussinesslayer.service.report.reportsprint;

import bussinesslayer.entity.report.ReportSprint;
import datalayer.DaoFactory;
import datalayer.IDaoFactory;
import datalayer.report.reportsprintdao.IReportSprintDao;

import java.util.List;

public class ReportSprintService implements IReportSprintService {
    IReportSprintDao reportSprintDao;
    IDaoFactory sprintDaoFactory;
    public ReportSprintService() throws Exception {
        sprintDaoFactory = new DaoFactory();
        reportSprintDao = sprintDaoFactory.getReportSprintDao();
    }
    public IReportSprintDao getReportSprintDao() {
        return reportSprintDao;
    }
    @Override
    public void update(ReportSprint reportSprint) throws Exception {
        reportSprintDao.update(reportSprint);
    }

    @Override
    public void create(ReportSprint reportSprint) throws Exception {
        reportSprintDao.addNew(reportSprint);
    }

    @Override
    public void delete(int id) throws Exception {
        reportSprintDao.delete(id);
    }

    @Override
    public ReportSprint getById(int id) throws Exception {
        return reportSprintDao.getById(id);
    }

    @Override
    public List<ReportSprint> getAll() throws Exception {
        return reportSprintDao.getAll();
    }


    @Override
    public List<ReportSprint> getReports(int projectId) {
        return reportSprintDao.getReports(projectId);
    }
}
