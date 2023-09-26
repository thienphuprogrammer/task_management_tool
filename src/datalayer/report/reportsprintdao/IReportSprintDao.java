package datalayer.report.reportsprintdao;

import bussinesslayer.entity.report.ReportSprint;
import datalayer.IDao;

import java.util.List;

public interface IReportSprintDao extends IDao<ReportSprint> {
    List<ReportSprint> getReports(int projectId);
}
