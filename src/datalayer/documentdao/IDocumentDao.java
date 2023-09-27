package datalayer.documentdao;

import bussinesslayer.entity.Document;
import datalayer.IDao;

import java.util.List;

public interface IDocumentDao extends IDao<Document> {
    List<Document> getDocument(int projectId);
}
