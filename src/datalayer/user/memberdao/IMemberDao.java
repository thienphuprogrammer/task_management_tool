package datalayer.user.memberdao;

import bussinesslayer.entity.user.Member;
import datalayer.IDao;

import java.util.List;

public interface IMemberDao extends IDao<Member> {
    List<Member> getByEmail(String email);

    Member loginMember(String email, String password);

    void changeEmail(int id, String email);

    void changePassword(int id, String password);
}
