package bussinesslayer.service.user.member;

import bussinesslayer.entity.user.Member;
import bussinesslayer.service.user.IUserService;

public interface IMemberService extends IUserService<Member> {
    Member loginMember(String email, String password) throws Exception;
    void signupMember(Member member) throws Exception;
}
