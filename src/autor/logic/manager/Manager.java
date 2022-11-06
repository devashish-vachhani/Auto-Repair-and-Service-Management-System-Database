package logic.manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import dao.ManagerDAO;
import logic.landing.Landing;

public class Manager {
    public static void displayMenu() {
        boolean flag = true;
        while (flag) {
            System.out.println("----Manager Landing Page----");
            System.out.println("1. Setup Store");
            System.out.println("2. Add New Employee");
            System.out.println("3. Logout");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                String choice = reader.readLine();
                switch (Integer.parseInt(choice)) {
                    case 1 -> {
                        setupStorePage();
                        flag = false;
                    }
                    case 2 -> {
                        addEmployeeDetails();
                        flag = false;
                    }
                    case 3 -> {
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

    private static void addEmployeeDetails() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("----Add Employee----");
        System.out.println("Enter Name");
        String name = reader.readLine();
        System.out.println("Enter Address");
        String address = reader.readLine();
        System.out.println("Enter Email Address");
        String emailAddress = reader.readLine();
        System.out.println("Enter Phone Number");
        String phoneNumber = reader.readLine();
        System.out.println("Enter Role");
        String role = reader.readLine();
        System.out.println("Enter Start Date");
        String startDate = reader.readLine();
        System.out.println("Enter Compensation ($)");
        String compensation = reader.readLine();
        boolean flag = true;
        while (flag) {
            System.out.println("1. Add");
            System.out.println("2. Go Back");
            BufferedReader readerNew = new BufferedReader(new InputStreamReader(System.in));
            try {
                String choice = readerNew.readLine();
                switch (Integer.parseInt(choice)) {
                    case 1 -> {
                        ManagerDAO.addEmployees();
                        //
                        flag = false;
                    }
                    case 2 -> {
                        setupStorePage();
                        flag = false;
                    }
                    default -> System.out.println("try again");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private static void setupStorePage() throws IOException {
        boolean flag = true;
        while (flag) {
            System.out.println("----Setup Store----");
            System.out.println("1. Add Employee");
            System.out.println("2. Setup operational hours");
            System.out.println("3. Setup service Prices");
            System.out.println("4. Go Back");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                String choice = reader.readLine();
                switch (Integer.parseInt(choice)) {
                    case 1 -> {
                        addEmployeeDetails();
                        flag = false;
                    }
                    case 2 -> {
                        setOperationalHours();
                        flag = false;
                    }
                    case 3 -> {
                        setupServicePrices();
                        flag = false;
                    }
                    case 4 -> {
                        displayMenu();
                        flag = false;
                    }
                    default -> System.out.println("try again");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void setOperationalHours() throws IOException {
        boolean flag = true;
        while (flag) {
            System.out.println("----Setup Operational Hours----");
            System.out.println("1. Setup operational hours");
            System.out.println("2. Go Back");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                String choice = reader.readLine();
                switch (Integer.parseInt(choice)) {
                    case 1 -> {
                        System.out.println("Operational on Saturdays? (Y/N)");
                        String openOnSaturdays = reader.readLine();
                        if (openOnSaturdays.length() == 1 && (openOnSaturdays.charAt(0) == 'Y' || openOnSaturdays.charAt(0) == 'N')){
                            ManagerDAO.setOperationalHours();
                            flag = false;
                        }
                    }
                    case 2 -> {
                        setupStorePage();
                        flag = false;
                    }
                    default -> System.out.println("try again");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void setupServicePrices() {
        boolean flag = true;
        while (flag) {
            System.out.println("---- Setup Service Prices ----");
            System.out.println("1. Setup Maintenance Service Prices");
            System.out.println("2. Setup Repair Service Prices");
            System.out.println("3. Go Back");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                String choice = reader.readLine();
                switch (Integer.parseInt(choice)) {
                    case 1 -> {
                        setupMaintenancePrice();
                        flag = false;
                    }
                    case 2 -> {
                        setupRepairPrice();
                        flag = false;
                    }
                    case 3 -> {
                        setupStorePage();
                        flag = false;
                    }
                    default ->
                            System.out.println("try again");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void setupRepairPrice() {
    }

    private static void setupMaintenancePrice() {
    }

}
