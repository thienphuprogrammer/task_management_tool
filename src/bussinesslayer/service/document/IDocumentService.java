package bussinesslayer.service.document;

import bussinesslayer.entity.Document;
import bussinesslayer.service.IService;

public interface IDocumentService extends IService<Document> {
    Document getDocument(int projectId) throws Exception;
}
