package application.ui.member;

import bussinesslayer.entity.user.Member;
import bussinesslayer.service.user.IUserService;

import static application.utilities.InputUtil.readInt;
import static application.utilities.InputUtil.readString;
import static application.utilities.OutputUtil.*;

public class ProfileMemberMenu {
    private IUserService<Member> serviceMember;
    int memberId;
    public enum CHOICE_PROFILE_MEMBER_MENU {
        EXIT,
        CHANGE_PASSWORD,
        CHANGE_EMAIL,
        CHANGE_INFORMATION,
        VIEW_INFORMATION,
    }
    // -------------------- Constructor ------------------------
    public ProfileMemberMenu(IUserService<Member> serviceMember, int memberId) {
        this.serviceMember = serviceMember;
        this.memberId = memberId;
    }

    // -------------------- Getters and Setters ------------------------
    // -------------------- Methods ------------------------
    public void processMenuForProfileMember() {
        boolean exit = false;
        while (!exit) {
            printLineSeparate("Profile Member Menu");
            printValueMenu("Member\\Profile");
            for (CHOICE_PROFILE_MEMBER_MENU choice : CHOICE_PROFILE_MEMBER_MENU.values()) {
                printValueMenu(choice.ordinal() + " to  " + choice.name().replace("_", " ").toLowerCase());
            }
            printLineSeparate();
            try {
                int choice = readInt("Enter your choice: ");

                if (choice < 0 || choice >= CHOICE_PROFILE_MEMBER_MENU.values().length) {
                    printValueln("Invalid choice.");
                } else {
                    switch (CHOICE_PROFILE_MEMBER_MENU.values()[choice]) {
                        case EXIT -> exit = true;
                        case VIEW_INFORMATION -> this.viewInformation();
                        case CHANGE_PASSWORD -> this.changePassword();
                        case CHANGE_EMAIL -> this.changeEmail();
                        case CHANGE_INFORMATION -> this.changeInformation();
                        default -> {
                        }
                    }
                }
            } catch (Exception e) {
                printValueln("Invalid choice.");
            }
        }
    }
    private void viewInformation() throws Exception {
        serviceMember.viewById(memberId);
    }
    private void changePassword() {
        String password = readString("Password: ");
        serviceMember.changePassword(memberId, password);
    }
    private void changeEmail() {

    }
    private void changeInformation() {

    }
}
