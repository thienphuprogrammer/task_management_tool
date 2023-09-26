package bussinesslayer.service.report.reportsubtask;

import bussinesslayer.entity.report.ReportSubtask;
import bussinesslayer.service.report.IReportService;

import java.util.List;

public interface IReportSubtaskService extends IReportService<ReportSubtask> {
    List<ReportSubtask> getReport(int taskId);
}
