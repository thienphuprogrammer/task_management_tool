package application.ui;

import bussinesslayer.entity.user.Member;
import bussinesslayer.service.user.member.IMemberService;
import bussinesslayer.service.user.member.MemberService;

import static application.utilities.InputUtil.readInt;
import static application.utilities.InputUtil.readString;
import static application.utilities.OutputUtil.*;

public class AccountMemberMenu {
    private final IMemberService managerService = new MemberService();

    public AccountMemberMenu() throws Exception {
    }

    public enum CHOICE_ACCOUNT_MEMBER_MENU {
        EXIT,
        LOGIN_MEMBER,
        SIGNUP_MEMBER
    }
    public Member processMenuForMember() {
        boolean exit = false;
        while (!exit) {
            printLineSeparate("Member Menu");
            for (CHOICE_ACCOUNT_MEMBER_MENU choice : CHOICE_ACCOUNT_MEMBER_MENU.values()) {
                printValueMenu(choice.ordinal() + ". " + choice.name().replace("_", " ").toLowerCase());
            }
            printLineSeparate();
            try {
                int choice = readInt("Enter your choice: ");
                if (choice < 0 || choice >= CHOICE_ACCOUNT_MEMBER_MENU.values().length) {
                    printValueln("Invalid choice.");
                } else {
                    switch (CHOICE_ACCOUNT_MEMBER_MENU.values()[choice]) {
                        case EXIT -> exit = true;
                        case LOGIN_MEMBER -> {
                            return loginMember();
                        }
                        case SIGNUP_MEMBER -> this.signupMember();
                        default -> {
                        }
                    }
                }
            } catch (Exception e) {
                printValueln("Invalid choice.");
            }
        }
        return null;
    }

    /*
     * Login member
     * check validation
     */
    private Member loginMember() {
        Member member = null;
        try {
            String email = readString("Enter your email: ");
            String password = readString("Enter your password: ");
            member = managerService.loginMember(email, password);
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
        return member;
    }
    /*
     * Signup member
     * check validation
     * check email is exist
     * check password
     * check gender only Male or Female
     */
    private void signupMember() {
        try {
            String name = readString("Enter your name: ");
            int age = readInt("Enter your age: ");
            String email = readString("Enter your email: ");
            String password = readString("Enter your password: ");
            String phone_number = readString("Enter your phone number: ");
            String address = readString("Enter your address: ");
            String gender = readString("Male or Female? Enter your gender: ");
            Member member = new Member(name, age, email, password, phone_number, address,gender);
            managerService.signupMember(member);
        } catch (Exception e) {
            printValueln(e.getMessage());
        }
    }
}
