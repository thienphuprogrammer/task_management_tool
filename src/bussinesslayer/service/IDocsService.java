package bussinesslayer.service;

import bussinesslayer.entity.Doc;

public interface IDocsService extends IService<Doc> {
    void viewDocumentByProjectId(int projectId);

}
