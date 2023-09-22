package bussinesslayer.service.user;

import bussinesslayer.entity.user.Member;
import datalayer.DaoFactory;
import datalayer.IDao;
import datalayer.IDaoFactory;

import java.util.List;

public class MemberService implements IUserService<Member> {
    private IDao<Member> memberIDao;
    IDaoFactory adminDaoFactory;

    public MemberService() throws Exception {
        adminDaoFactory = new DaoFactory();
        this.memberIDao = adminDaoFactory.getMemberDao();
    }

    @Override
    public void update(Member member) throws Exception {

    }

    @Override
    public void create(Member member) throws Exception {

    }

    @Override
    public void delete(int id) throws Exception {

    }

    @Override
    public Member getById(int id) throws Exception {
        return null;
    }

    @Override
    public List<Member> getAll() throws Exception {
        return null;
    }
}
