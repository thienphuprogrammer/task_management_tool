package datalayer.documentdao;

import bussinesslayer.entity.document.Document;
import datalayer.IDao;

import java.util.List;

public interface IDocumentDao extends IDao<Document> {
    List<Document> getAllDocumentsByProjectId(int projectId);
}
