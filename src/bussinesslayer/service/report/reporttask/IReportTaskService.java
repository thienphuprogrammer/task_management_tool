package bussinesslayer.service.report.reporttask;

import bussinesslayer.entity.report.ReportTask;
import bussinesslayer.service.report.IReportService;

public interface IReportTaskService extends IReportService<ReportTask> {
    void viewReport(int id) throws Exception;

    void viewReportSprint(int taskId, int sprintId);
}
