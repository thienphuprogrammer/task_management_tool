package bussinesslayer.service.report;

import bussinesslayer.service.IService;

public interface IReportService<T> extends IService<T> {
    public void viewById(int id) throws Exception;
    public void viewAll() throws Exception;
}
