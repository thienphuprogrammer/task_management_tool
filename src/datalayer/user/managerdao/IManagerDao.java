package datalayer.user.managerdao;

import bussinesslayer.entity.user.Manager;
import bussinesslayer.entity.user.Member;
import datalayer.IDao;

import java.util.List;

public interface IManagerDao extends IDao<Manager> {
    List<Member> viewAllMember(int managerId);
}
