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

    List<ReportBacklog> getReport(int backlogId) throws Exception;

    void updateReportBacklog(ReportBacklog reportBacklog) throws Exception;
    List<ReportBacklog> getAllReport(int projectId) throws  Exception;
}
