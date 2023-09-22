package datalayer;

import bussinesslayer.entity.space.*;
import bussinesslayer.entity.user.Admin;
import bussinesslayer.entity.user.Manager;
import bussinesslayer.entity.user.Member;

public interface IDaoFactory {
    IDao<Backlog> getBacklogDao() throws Exception;

    IDao<Project> getProjectDao() throws Exception;

    IDao<Sprint> getSprintDao() throws Exception;

    IDao<Task> getTaskDao() throws Exception;

    IDao<SubTask> getSubTaskDao() throws Exception;

    IDao<Admin> getAdminDao() throws Exception;

    IDao<Member> getMemberDao() throws Exception;

    IDao<Manager> getManagerDao() throws Exception;
}