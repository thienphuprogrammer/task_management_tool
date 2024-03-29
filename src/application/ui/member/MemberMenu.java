package application.ui.member;
import bussinesslayer.service.user.member.IMemberService;
import bussinesslayer.service.user.member.MemberService;

import static application.utilities.InputUtil.readInt;
import static application.utilities.OutputUtil.*;

//Member Menu
public class MemberMenu {
    public enum CHOICE_MEMBER_MENU {
        EXIT,
        // Project
        INTO_PROJECT_MEMBER,
        INTO_PROFILE_MEMBER
    }
    // -------------------- Properties ------------------------
    IMemberService service = new MemberService();
    private int memberId = 1;

    public MemberMenu(int memberId) throws Exception {
        this.memberId = memberId;
    }

    // -------------------- Constructor ------------------------
    public MemberMenu(IMemberService service) throws Exception {
        this.service = service;
    }
    public void processMenuForMember() {
        boolean exit = false;
        while (!exit) {
            printLineSeparate("Member Menu");
            printValueMenu("Member");
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
                        case INTO_PROJECT_MEMBER -> this.processMenuForProject();
                        case INTO_PROFILE_MEMBER -> this.processMenuForProfile();
                        default -> {
                        }
                    }
                }
            } catch (Exception e) {
                printValueln("Invalid choice.");
            }
        }
    }
    /*
     * Process menu for project
     */
    private void processMenuForProject() throws Exception {
        ProjectMemberMenu projectMemberMenu = new ProjectMemberMenu(memberId);
        projectMemberMenu.processMenuForProjectMember();
    }
    /*
     * Process menu for profile
     */
    private void processMenuForProfile() {
        ProfileMemberMenu profileMemberMenu = new ProfileMemberMenu(service, memberId);
        profileMemberMenu.processMenuForProfileMember();
    }
}
