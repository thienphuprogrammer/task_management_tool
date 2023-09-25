package bussinesslayer.service;

import bussinesslayer.entity.Doc;

public interface IDocsService extends IService<Doc> {
    void getDocument(int projectId);
    void getDocument(int projectId, int userId) throws Exception;
}
