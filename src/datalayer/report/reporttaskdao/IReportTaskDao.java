package datalayer.report.reporttaskdao;

import bussinesslayer.entity.report.ReportTask;
import datalayer.IDao;

import java.util.List;

public interface IReportTaskDao extends IDao<ReportTask> {
    List<ReportTask> getTaskProgress(int sprintId);

    List<ReportTask> getReports(int taskId);
}
