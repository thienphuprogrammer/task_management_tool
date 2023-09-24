package bussinesslayer.service;

import bussinesslayer.entity.Doc;
import datalayer.IDao;

import java.util.List;

public class DocsService implements IDocsService {
    // -------------------- Properties ------------------------
    private IDao<Doc> docIDao;
    // -------------------- Constructor ------------------------
    public DocsService(IDao<Doc> docIDao) {
        this.docIDao = docIDao;
    }

    public DocsService() {
    }
    // -------------------- Getters and Setters ------------------------

    public IDao<Doc> getDocIDao() {
        return docIDao;
    }

    public void setDocIDao(IDao<Doc> docIDao) {
        this.docIDao = docIDao;
    }
    // -------------------- Methods ------------------------

    @Override
    public void update(Doc doc) throws Exception {
        docIDao.update(doc);
    }

    @Override
    public void create(Doc doc) throws Exception {
        docIDao.addNew(doc);
    }

    @Override
    public void delete(int id) throws Exception {
        docIDao.delete(id);
    }

    @Override
    public Doc getById(int id) throws Exception {
        return docIDao.getById(id);
    }

    @Override
    public List<Doc> getAll() throws Exception {
        return docIDao.getAll();
    }
}
