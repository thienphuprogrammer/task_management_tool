package datalayer.user.memberdao;

import bussinesslayer.entity.user.Member;
import datalayer.IDao;

import java.util.List;

public interface IMemberDao extends IDao<Member> {
    List<Member> getByEmail(String email) throws Exception;

    Member loginMember(String email, String password) throws Exception;

    void changeEmail(int id, String email) throws Exception;

    void changePassword(int id, String password) throws Exception;
}
