package bussinesslayer.service.report.reportsubtask;

import bussinesslayer.entity.report.ReportSubtask;
import bussinesslayer.service.report.IReportService;

public interface IReportSubtaskService extends IReportService<ReportSubtask> {
    void createReport(ReportSubtask reportSubtask);

    void viewReport(int taskId);
}
