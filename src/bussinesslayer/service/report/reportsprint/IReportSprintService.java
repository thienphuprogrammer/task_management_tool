package bussinesslayer.service.report.reportsprint;

import bussinesslayer.entity.report.ReportSprint;
import bussinesslayer.service.report.IReportService;

public interface IReportSprintService extends IReportService<ReportSprint> {
    void createReport(ReportSprint reportSprint);
}
