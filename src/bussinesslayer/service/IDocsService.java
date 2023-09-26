package bussinesslayer.service;

import bussinesslayer.entity.Document;

public interface IDocsService extends IService<Document> {
    Document getDocument(int projectId) throws Exception;
}
