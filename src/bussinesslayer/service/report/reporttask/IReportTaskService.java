package bussinesslayer.service.report.reporttask;

import bussinesslayer.entity.report.ReportTask;
import bussinesslayer.service.report.IReportService;

import java.util.List;

public interface IReportTaskService extends IReportService<ReportTask> {
    List<ReportTask> getTaskProgress(int sprintId);

    List<ReportTask> getReports(int sprintId);
}
