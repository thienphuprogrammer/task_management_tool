package bussinesslayer.service.document;

import bussinesslayer.entity.Document;
import bussinesslayer.service.IService;

import java.util.List;

public interface IDocumentService extends IService<Document> {
    List<Document> getAllDocumentsByProjectId(int projectId) throws Exception;

    Document getDocument(int projectId, int documentId);
}
