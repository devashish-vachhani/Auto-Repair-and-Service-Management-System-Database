package logic.authentication;

import dao.UserDAO;
import logic.admin.Admin;
import logic.landing.Landing;
import logic.mechanic.Mechanic;
import models.User;
import models.UserRoleEnum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void inputCredentials() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter username");
        String username = reader.readLine();
        System.out.println("Enter password");
        String password = reader.readLine();
        System.out.println("Credentials are:" + " " + username + " " + password);
        User user = UserDAO.verifyUser(username, password);
        if(user != null){
            //redirect switch case
            if(user.getRole() == UserRoleEnum.ADMIN){
                //redirect to admin
                //Added print statements just to verify if getting values from DB. Need to call adminMenu
                System.out.println("From table:" + " " + user.getUserid() + " " + user.getUsername() + " " + user.getRole());
                Admin.adminMenu();
            } else if(user.getRole() == UserRoleEnum.MANAGER){
                //redirect to manager
                System.out.println("From table:" + " " + user.getUserid() + " " + user.getUsername() + " " + user.getRole());
            } else if(user.getRole() == UserRoleEnum.RECEPTIONIST){
                //redirect to receptionist
            } else if(user.getRole() == UserRoleEnum.MECHANIC){
                System.out.println("From table:" + " " + user.getUserid() + " " + user.getUsername() + " " + user.getRole());
                Mechanic.mechanicMenu();
                //redirect to mechanic
            } else {
                //redirect to customer
            }
        } else {
            System.out.println("Invalid username/password. Try logging in again");
            inputCredentials();
        };
    }
}
