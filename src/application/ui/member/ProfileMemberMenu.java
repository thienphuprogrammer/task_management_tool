package application.ui.member;

import bussinesslayer.entity.user.Member;
import bussinesslayer.service.user.member.IMemberService;

import static application.utilities.InputUtil.*;
import static application.utilities.OutputUtil.*;

public class ProfileMemberMenu {
    private IMemberService serviceMember;
    int memberId;
    public enum CHOICE_PROFILE_MEMBER_MENU {
        EXIT,
        CHANGE_PASSWORD,
        CHANGE_EMAIL,
        CHANGE_INFORMATION,
        VIEW_INFORMATION,
    }
    // -------------------- Constructor ------------------------
    public ProfileMemberMenu(IMemberService serviceMember, int memberId) {
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
    /*
     * View information
     */
    private void viewInformation()  {
        try {
            Member member = serviceMember.getById(memberId);
            printLineSeparate("Information");
            printValueMenu("Id: " + member.getId());
            printValueMenu("Name: " + member.getName());
            printValueMenu("Age: " + member.getAge());
            printValueMenu("Phone number: " + member.getPhoneNumber());
            printValueMenu("Gender: " + member.isGender());
            printValueMenu("Address: " + member.getAddress());
            printValueMenu("Email: " + member.getEmail());
            printValueMenu("Password: " + member.getPassword());
            printLineSeparate();
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }

    /*
     * Change password
     * check password is correct
     */
    private void changePassword() {
        try {
            String password = readString("Password: ");
            serviceMember.changePassword(memberId, password);
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }

    /*
     * Change email
     * check email is validation
     * check email is exist
     */
    private void changeEmail() {
        try {
            String email = readString("Email: ");
            serviceMember.changeEmail(memberId, email);
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }

    /*
     * Change information
     * check validation
     * press enter to keep old info
     * check Gender is only Male or Female
     */
    private void changeInformation() throws Exception {
        try {
            Member member = serviceMember.getById(memberId);
            member.setName(readString("Name: ", member.getName()));
            member.setAge(readInt("Age: ", member.getAge()));
            member.setPhoneNumber(readString("Phone number: ", member.getPhoneNumber()));
            member.setGender(readString("Gender: ", member.isGender()));
            member.setAddress(readString("Address: ", member.getAddress()));
            serviceMember.update(member);
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
}
