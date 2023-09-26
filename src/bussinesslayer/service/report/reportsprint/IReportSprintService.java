package bussinesslayer.service.report.reportsprint;

import bussinesslayer.entity.report.ReportSprint;
import bussinesslayer.service.report.IReportService;

import java.util.List;

public interface IReportSprintService extends IReportService<ReportSprint> {

    List<ReportSprint> getReports(int projectId);

}
