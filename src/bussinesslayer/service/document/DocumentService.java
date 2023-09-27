package bussinesslayer.service.document;

import bussinesslayer.entity.document.Document;
import datalayer.IDao;
import datalayer.documentdao.DocumentDao;
import datalayer.documentdao.IDocumentDao;

import java.util.List;

public class DocumentService implements IDocumentService {
    // -------------------- Properties ------------------------
    private IDocumentDao docIDao = new DocumentDao();
    // -------------------- Constructor ------------------------
    public DocumentService(IDocumentDao docIDao) {
        this.docIDao = docIDao;
    }

    public DocumentService() {
    }
    // -------------------- Getters and Setters ------------------------

    public IDao<Document> getDocIDao() {
        return docIDao;
    }

    public void setDocIDao(IDocumentDao docIDao) {
        this.docIDao = docIDao;
    }
    // -------------------- Methods ------------------------

    @Override
    public void update(Document doc) throws Exception {
        docIDao.update(doc);
    }

    @Override
    public void create(Document doc) throws Exception {
        docIDao.addNew(doc);
    }

    @Override
    public void delete(int id) throws Exception {
        docIDao.delete(id);
    }

    @Override
    public Document getById(int id) throws Exception {
        return docIDao.getById(id);
    }

    @Override
    public List<Document> getAll() throws Exception {
        return docIDao.getAll();
    }

    @Override
    public List<Document> getAllDocumentsByProjectId(int projectId) throws Exception {
        return docIDao.getAllDocumentsByProjectId(projectId);
    }

    @Override
    public Document getDocument(int projectId, int documentId) {
        return null;
    }
}
