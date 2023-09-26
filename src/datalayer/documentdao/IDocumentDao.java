package datalayer.documentdao;

import bussinesslayer.entity.Document;
import datalayer.IDao;

public interface IDocumentDao extends IDao<Document> {
    Document getDocument(int projectId);
}
