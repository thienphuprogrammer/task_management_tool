package bussinesslayer.service.report.reportbacklog;

import bussinesslayer.entity.report.ReportBacklog;
import bussinesslayer.service.report.IReportService;

import java.util.List;

public interface IReportBacklogService extends IReportService<ReportBacklog> {
    @Override
    void update(ReportBacklog reportBacklog) throws Exception;

    @Override
    void create(ReportBacklog reportBacklog) throws Exception;

    @Override
    void delete(int id) throws Exception;

    @Override
    ReportBacklog getById(int id) throws Exception;

    @Override
    List<ReportBacklog> getAll() throws Exception;

    void viewReport(int projectId);

    void updateReportBacklog(ReportBacklog reportBacklog);

    void createReportBacklog(ReportBacklog reportBacklog);

}
