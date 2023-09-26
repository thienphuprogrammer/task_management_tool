package datalayer.report.reportprojectdao;

import bussinesslayer.entity.report.ReportProject;
import datalayer.IDao;

import java.util.List;

public interface IReportProjectDao extends IDao<ReportProject> {
    List<ReportProject> getReport(int projectId);
}
