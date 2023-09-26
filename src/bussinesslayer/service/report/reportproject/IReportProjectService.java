package bussinesslayer.service.report.reportproject;

import bussinesslayer.entity.report.ReportProject;
import bussinesslayer.service.report.IReportService;

import java.util.List;

public interface IReportProjectService extends IReportService<ReportProject> {
    List<ReportProject> getReportProject(int projectId);
}
