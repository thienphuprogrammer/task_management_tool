package bussinesslayer.service.user.member;

import bussinesslayer.entity.user.Admin;
import bussinesslayer.entity.user.Member;
import bussinesslayer.service.user.IUserService;
import datalayer.DaoFactory;
import datalayer.IDao;
import datalayer.IDaoFactory;
import datalayer.user.memberdao.IMemberDao;

import java.util.List;

import static bussinesslayer.components.DataValidation.*;
import static bussinesslayer.components.DataValidation.isValidPhoneNumber;

public class MemberService implements IMemberService {
    private IMemberDao memberIDao;
    IDaoFactory adminDaoFactory;

    public MemberService() throws Exception {
        adminDaoFactory = new DaoFactory();
        this.memberIDao = adminDaoFactory.getMemberDao();
    }

    @Override
    public void update(Member member) throws Exception {
        memberIDao.update(member);
    }

    @Override
    public void create(Member member) throws Exception {
        memberIDao.addNew(member);
    }

    @Override
    public void delete(int id) throws Exception {
        memberIDao.delete(id);
    }

    @Override
    public Member getById(int id) throws Exception {
        return memberIDao.getById(id);
    }

    @Override
    public List<Member> getAll() throws Exception {
        return memberIDao.getAll();
    }

    @Override
    public void changePassword(int id, String password) {
        memberIDao.changePassword(id, password);
    }

    @Override
    public void changeEmail(int id, String email) throws Exception {
        List<Member> list = memberIDao.getByEmail(email);
        if (list.size() > 0) {
            throw new Exception("Email already exists");
        }
        memberIDao.changeEmail(id, email);
    }

    @Override
    public Member loginMember(String email, String password) {
        return memberIDao.loginMember(email, password);
    }

    @Override
    public void signupMember(Member member) throws Exception {
        if(!isValidEmail(member.getEmail())) {
            throw new Exception("Invalid email");
        }
        List<Member> list = memberIDao.getByEmail(member.getEmail());
        if (list.size() > 0) {
            throw new Exception("Email already exists");
        }
        if(isValidPassword(member.getPassword())) {
            throw new Exception("Invalid password");
        }
        if (isValidName(member.getName())) {
            throw new Exception("Invalid name");
        }
        if (isValidPhoneNumber(member.getPhoneNumber())) {
            throw new Exception("Invalid phone number");
        }
        memberIDao.addNew(member);
    }
}
