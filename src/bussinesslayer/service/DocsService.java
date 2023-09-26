package bussinesslayer.service;

import bussinesslayer.entity.Document;
import datalayer.IDao;
import datalayer.documentdao.IDocumentDao;

import java.util.List;

public class DocsService implements IDocsService {
    // -------------------- Properties ------------------------
    private IDocumentDao docIDao;
    // -------------------- Constructor ------------------------
    public DocsService(IDocumentDao docIDao) {
        this.docIDao = docIDao;
    }

    public DocsService() {
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
    public Document getDocument(int projectId) throws Exception {
        return docIDao.getDocument(projectId);
    }
}
