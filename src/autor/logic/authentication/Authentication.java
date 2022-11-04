package logic.authentication;

import dao.EmployeeDAO;
import logic.landing.Landing;
import models.Employee;
import models.EmployeeRoleEnum;

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
                    default -> System.out.println("try agian");
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
        Employee employee = EmployeeDAO.verifyUser(username, password);
        if(employee != null){
            //redirect switch case
            if(employee.getRole() == EmployeeRoleEnum.ADMIN){
                //redirect to admin
                //Added print statements just to verify if getting values from DB. Need to call adminMenu
                System.out.println("From table:" + " " + employee.getUsername() + " " + employee.getRole());
            } else if(employee.getRole() == EmployeeRoleEnum.MANAGER){
                //redirect to manager
                System.out.println("From table:" + " " + employee.getUsername() + " " + employee.getRole());
            } else if(employee.getRole() == EmployeeRoleEnum.RECEPTIONIST){
                //redirect to receptionist
            } else {
                //redirect to mechanic
            }
        } else {
            System.out.println("Invalid username/password. Try logging in again");
            inputCredentials();
        };
    }
}
