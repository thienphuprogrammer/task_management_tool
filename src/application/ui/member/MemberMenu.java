package application.ui.member;

import bussinesslayer.entity.user.Member;
import bussinesslayer.service.IService;
import bussinesslayer.service.user.IUserService;

import static application.utilities.InputUtil.readInt;
import static application.utilities.OutputUtil.*;

//Member Menu
public class MemberMenu {
    public enum CHOICE_MEMBER_MENU {
        EXIT,
        // Project
        PROJECT_MEMBER,
        PROFILE_MEMBER
    }
    // -------------------- Properties ------------------------
    IUserService<Member> service;
    private int memberId;
    // -------------------- Constructor ------------------------
    public MemberMenu(IUserService<Member> service) {
        this.service = service;
    }
    public void processMenuForMember() {
        boolean exit = false;
        while (!exit) {
            printLineSeparate("Member Menu");
            for (CHOICE_MEMBER_MENU choice : CHOICE_MEMBER_MENU.values()) {
                printValueMenu(choice.ordinal() + ". " + choice.name().replace("_", " ").toLowerCase());
            }
            printLineSeparate();
            try {
                int choice = readInt("Enter your choice: ");
                if (choice < 0 || choice >= CHOICE_MEMBER_MENU.values().length) {
                    printValueln("Invalid choice.");
                } else {
                    switch (CHOICE_MEMBER_MENU.values()[choice]) {
                        case EXIT -> exit = true;
                        case PROJECT_MEMBER -> this.processMenuForProject();
                        case PROFILE_MEMBER -> this.processMenuForProfile();
                        default -> {
                        }
                    }
                }
            } catch (Exception e) {
                printValueln("Invalid choice.");
            }
        }
    }
    private void processMenuForProject() throws Exception {
        ProjectMemberMenu projectMemberMenu = new ProjectMemberMenu(memberId);
        projectMemberMenu.processMenuForProjectMember();
    }
    private void processMenuForProfile() {
        ProfileMemberMenu profileMemberMenu = new ProfileMemberMenu(service, memberId);
        profileMemberMenu.processMenuForProfileMember();
    }
}