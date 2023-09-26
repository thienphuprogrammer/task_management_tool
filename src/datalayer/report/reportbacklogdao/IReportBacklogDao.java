package datalayer.report.reportbacklogdao;

import bussinesslayer.entity.report.ReportBacklog;
import datalayer.IDao;

import java.util.List;

public interface IReportBacklogDao extends IDao<ReportBacklog> {

    List<ReportBacklog> getReport(int backlogId);

    List<ReportBacklog> getAllReport(int projectId);
}
