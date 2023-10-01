package bussinesslayer.service.report.reporttask;

import bussinesslayer.entity.report.ReportTask;
import bussinesslayer.service.report.IReportService;

import java.util.List;

public interface IReportTaskService extends IReportService<ReportTask> {
    List<ReportTask> getReportsBySprintId(int sprintId) throws Exception;

    List<ReportTask> getReportsByTaskId(int taskId) throws Exception;
}
