package bussinesslayer.service;

import bussinesslayer.entity.Doc;

public interface IDocsService extends IService<Doc> {
    Doc getDocByProjectId(int projectId);

    void viewDocumentByProjectId(int projectId);
}
