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
        Member member = memberIDao.getById(id);
        if (member == null) {
            throw new Exception("Member is not exist");
        }
        return member;
    }

    @Override
    public List<Member> getAll() throws Exception {
        List<Member> list = memberIDao.getAll();
        if (list == null) {
            throw new Exception("Member is not exist");
        }
        return list;
    }

    @Override
    public void changePassword(int id, String password) throws Exception {
        if (!isValidPassword(password)) {
            throw new Exception("Invalid password");
        }
        memberIDao.changePassword(id, password);
    }

    @Override
    public void changeEmail(int id, String email) throws Exception {
        List<Member> list = memberIDao.getByEmail(email);
        if (list.size() > 0) {
            throw new Exception("Email already exists");
        }
        if (!isValidEmail(email)) {
            throw new Exception("Invalid email");
        }

        memberIDao.changeEmail(id, email);
    }

    @Override
    public Member loginMember(String email, String password) throws Exception {
        Member member = memberIDao.loginMember(email, password);
        if (member == null) {
            throw new Exception("Invalid email or password");
        }
        return member;
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
        if(!isValidPassword(member.getPassword())) {
            throw new Exception("Invalid password");
        }
        if (!isValidName(member.getName())) {
            throw new Exception("Invalid name");
        }
        if (!isValidPhoneNumber(member.getPhoneNumber())) {
            throw new Exception("Invalid phone number");
        }
        memberIDao.addNew(member);
    }
}
