package application.ui.member;

import bussinesslayer.entity.user.Member;
import bussinesslayer.service.user.IUserService;

import static application.utilities.InputUtil.readInt;
import static application.utilities.OutputUtil.*;

public class ProfileMemberMenu {
    private IUserService<Member> serviceMember;
    public enum CHOICE_PROFILE_MEMBER_MENU {
        EXIT,
        UPDATE_PROFILE,
        CHANGE_PASSWORD,
        CHANGE_EMAIL,
        CHANGE_INFORMATION,
        VIEW_INFORMATION,
    }
    // -------------------- Constructor ------------------------

    public ProfileMemberMenu(IUserService<Member> serviceMember) {
        this.serviceMember = serviceMember;
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
                        case UPDATE_PROFILE -> this.updateProfile();
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
    private void viewInformation() {

    }
    private void updateProfile() {

    }
    private void changePassword() {

    }
    private void changeEmail() {

    }
    private void changeInformation() {

    }
}
