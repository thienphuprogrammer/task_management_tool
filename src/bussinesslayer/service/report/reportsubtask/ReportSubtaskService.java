package bussinesslayer.service.report.reportsubtask;

import bussinesslayer.entity.report.ReportSubtask;
import datalayer.DaoFactory;
import datalayer.IDao;
import datalayer.IDaoFactory;
import datalayer.report.reportsubtaskdao.IReportSubtaskDao;

import java.util.List;

public class ReportSubtaskService implements IReportSubtaskService {
    IReportSubtaskDao reportSubtaskDao;
    IDaoFactory subtaskDaoFactory;
    public ReportSubtaskService() throws Exception {
        subtaskDaoFactory = new DaoFactory();
        reportSubtaskDao = subtaskDaoFactory.getReportSubtaskDao();
    }
    @Override
    public void update(ReportSubtask reportSubtask) throws Exception {
        reportSubtaskDao.update(reportSubtask);
    }

    @Override
    public void create(ReportSubtask reportSubtask) throws Exception {
        reportSubtaskDao.addNew(reportSubtask);
    }

    @Override
    public void delete(int id) throws Exception {
        reportSubtaskDao.delete(id);
    }

    @Override
    public ReportSubtask getById(int id) throws Exception {
        return reportSubtaskDao.getById(id);
    }

    @Override
    public List<ReportSubtask> getAll() throws Exception {
        return reportSubtaskDao.getAll();
    }

    @Override
    public List<ReportSubtask> getReport(int taskId) {

        return null;
    }
}
