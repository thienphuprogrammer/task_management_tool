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
    public void viewById(int id) throws Exception {
        Member member = getById(id);
        System.out.println("| id: " + member.getId() + " ".repeat(40 - String.valueOf(member.getId()).length()) + "|");
        System.out.println("| Name: " + member.getName() + " ".repeat(40 - String.valueOf(member.getName()).length()) + "|");
        System.out.println("| age: " + member.getAge() + " ".repeat(36 - String.valueOf(member.getAge()).length()) + "|");
        System.out.println("| email: " + member.getEmail() + " ".repeat(43 - String.valueOf(member.getEmail()).length()) + "|");
        System.out.println("| password: " + member.getPassword() + " ".repeat(43 - String.valueOf(member.getPassword()).length()) + "|");
        System.out.println("| phone number: " + member.getPhoneNumber() + " ".repeat(43 - String.valueOf(member.getPhoneNumber()).length()) + "|");
        System.out.println("| address: " + member.getAddress() + " ".repeat(43 - String.valueOf(member.getAddress()).length()) + "|");
        System.out.println("| role: " + member.getRole() + " ".repeat(43 - String.valueOf(member.getRole()).length()) + "|");
        System.out.println("| gender: " + member.isGender() + " ".repeat(43 - String.valueOf(member.isGender()).length()) + "|");
    }

    @Override
    public void viewAll() throws Exception {
        try {
            List<Member> list = getAll();
            for (Member member : list) {
                viewById(member.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
