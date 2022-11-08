package logic.authentication;

import dao.UserDAO;
import logic.admin.Admin;
import logic.landing.Landing;
import logic.manager.Manager;
import logic.mechanic.Mechanic;
import logic.receptionist.Receptionist;
import models.CarService;
import models.User;
import models.UserRoleEnum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Console;


public class Authentication {

    public static void loginMenu(){
        boolean flag = true;
        while(flag) {
            System.out.println("----Login Page----");
            System.out.println("1. Sign-in");
            System.out.println("2. Go Back");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                String choice = reader.readLine();
                switch (Integer.parseInt(choice)) {
                    case 1 -> {
                        inputCredentials();
                        flag = false;
                    }
                    case 2 -> {
                        Landing.homeMenu();
                        flag = false;
                    }
                    default -> System.out.println("try again");
                }
            } catch (Exception e) {
                System.out.println("Wrong Input, try again!!");
                loginMenu();
            }
        }
    }

    private static void inputCredentials() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter username");
        String username = reader.readLine();
        System.out.println("Enter password");
//        Console console = System.console();
        String password = reader.readLine();
        System.out.println("Credentials are:" + " " + username + " " + password);
        User user = UserDAO.verifyUser(username, password);
        if(user != null){
            //redirect switch case
            if(user.getRole() == UserRoleEnum.ADMIN){
                System.out.println("From table:" + " " + user.getUserid() + " " + user.getUsername() + " " + user.getRole());
                Admin.adminMenu();
            } else if(user.getRole() == UserRoleEnum.MANAGER){
                System.out.println("From table:" + " " + user.getUserid() + " " + user.getUsername() + " " + user.getRole());
                Manager.managerMenu(user);
            } else if(user.getRole() == UserRoleEnum.RECEPTIONIST){
                System.out.println("From table:" + " " + user.getUserid() + " " + user.getUsername() + " " + user.getRole());
                Receptionist.receptionistMenu(user);
            } else if(user.getRole() == UserRoleEnum.MECHANIC){
                System.out.println("From table:" + " " + user.getUserid() + " " + user.getUsername() + " " + user.getRole());
                Mechanic.mechanicMenu(user);
            } else {
                //redirect to customer
            }
        } else {
            System.out.println("Invalid username/password. Try logging in again");
            inputCredentials();
        }
    }
}
