package logic.manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import dao.AdminDAO;
import dao.ManagerDAO;
import logic.landing.Landing;
import models.CarService;
import models.User;

public class Manager {
    public static void managerMenu(User user) {
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
                        flag = false;
                        setupStorePage(user);
                    }
                    case 2 -> {
                        flag = false;
                        inputEmployeeData(user);
                    }
                    case 3 -> {
                        flag = false;
                        Landing.homeMenu();
                    }
                    default -> System.out.println("try again");
                }
            } catch (Exception e) {
                System.out.println("Wrong Input, try again!!");
                managerMenu(user);
            }
        }
    }

    private static void inputEmployeeData(User user) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("----Add Employee----");
            System.out.println("Enter Employee's UserID");
            Long userId = Long.parseLong(reader.readLine());
            System.out.println("Enter First Name");
            String firstName = reader.readLine();
            System.out.println("Enter Last Name");
            String lastName = reader.readLine();
            System.out.println("Enter Address");
            String address = reader.readLine();
            System.out.println("Enter Email Address");
            String emailAddress = reader.readLine();
            System.out.println("Enter Phone Number");
            Long phoneNumber = Long.parseLong(reader.readLine());
            System.out.println("Enter Role (M: mechanic/R: receptionist)");
            String role = reader.readLine();
            System.out.println("Enter Start Date");
            String startDate = reader.readLine();
            System.out.println("Enter Username");
            String username = reader.readLine();
            Integer compensation = null;
            if (role.equals("R")) {
                System.out.println("Enter Compensation ($)");
                compensation = Integer.parseInt(reader.readLine());
            }
            addEmployees(user, userId,firstName, lastName, address, emailAddress, phoneNumber, role, startDate, compensation, username);
        } catch (Exception e) {
            System.out.println("Wrong Input, try again!!");
            inputEmployeeData(user);
        }
    }

    private static void addEmployees(User user, Long userId, String firstName, String lastName, String address, String emailAddress, Long phoneNumber, String role, String startDate, Integer compensation, String username) {
        boolean flag = true;
        while(flag) {
            System.out.println("1. Add");
            System.out.println("2. Go Back");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                String choice = reader.readLine();
                switch (Integer.parseInt(choice)) {
                    case 1 -> {
                        String s = ManagerDAO.addEmployees(user, userId, firstName, lastName, address, emailAddress, phoneNumber, role, startDate, compensation, username);
                        System.out.println(s);
                        flag = false;
                        setupStorePage(user);
                    }
                    case 2 -> {
                        flag = false;
                        setupStorePage(user);
                    }
                    default -> System.out.println("Invalid Inputs. Try again");
                }
            } catch (Exception e) {
                System.out.println("Invalid Inputs. Try again");
                addEmployees(user, userId, firstName, lastName, address, emailAddress, phoneNumber, role, startDate, compensation, username);
            }
        }
    }

    private static void setupStorePage(User user) throws IOException {
        boolean flag = true;
        while (flag) {
            System.out.println("----Setup Store----");
            System.out.println("1. Add Employee");
            System.out.println("2. Setup operational hours");
            System.out.println("3. Setup hourly rate");
            System.out.println("4. Setup service Prices");
            System.out.println("5. Go Back");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                String choice = reader.readLine();
                switch (Integer.parseInt(choice)) {
                    case 1 -> {
                        flag = false;
                        inputEmployeeData(user);
                        managerMenu(user);
                    }
                    case 2 -> {
                        flag = false;
                        setOperationalHours(user);
                        managerMenu(user);
                    }
                    case 3 -> {
                        flag = false;
                        setHourlyRate(user);
                        managerMenu(user);
                    }
                    case 4 -> {
                        flag = false;
                        setupServicePrices(user);
                        managerMenu(user);
                    }
                    case 5 -> {
                        flag = false;
                        managerMenu(user);
                    }
                    default -> System.out.println("try again");
                }
            } catch (Exception e) {
                System.out.println("Wrong Input, try again!!");
                setupStorePage(user);
            }
        }
    }

    private static void setOperationalHours(User user) throws IOException {
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
                            flag = false;
                            String s = ManagerDAO.setOperationalHours(user, openOnSaturdays);
                            System.out.println(s);
                            setupStorePage(user);
                        } else {
                            flag = false;
                            System.out.println("Wrong Input, try again!!");
                            setOperationalHours(user);
                        }
                    }
                    case 2 -> {
                        flag = false;
                        setupStorePage(user);
                    }
                    default -> System.out.println("try again");
                }
            } catch (Exception e) {
                System.out.println("Wrong Input, try again!!");
                setOperationalHours(user);
            }
        }
    }

    private static void setHourlyRate(User user) throws IOException {
        boolean flag = true;
        while (flag) {
            System.out.println("----Setup Hourly Rate----");
            System.out.println("1. Setup hourly rate");
            System.out.println("2. Go Back");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                String choice = reader.readLine();
                switch (Integer.parseInt(choice)) {
                    case 1 -> {
                        System.out.println("Enter Hourly Rate");
                        Integer hourlyRate = Integer.parseInt(reader.readLine());
                        flag = false;
                        String s = ManagerDAO.setHourlyRate(user, hourlyRate);
                        System.out.println(s);
                        setupStorePage(user);
                    }
                    case 2 -> {
                        flag = false;
                        setupStorePage(user);
                    }
                    default -> System.out.println("try again");
                }
            } catch (Exception e) {
                System.out.println("Wrong Input, try again!!");
                setHourlyRate(user);
            }
        }
    }

    private static void setupServicePrices(User user) {
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
                        flag = false;
                        setupMaintenancePrice(user);
                        setupStorePage(user);
                    }
                    case 2 -> {
                        flag = false;
                        setupRepairPrice(user);
                        setupStorePage(user);
                    }
                    case 3 -> {
                        flag = false;
                        setupStorePage(user);
                        setupStorePage(user);
                    }
                    default ->
                            System.out.println("try again");
                }
            } catch (Exception e) {
                System.out.println("Wrong Input, try again!!");
                setupServicePrices(user);
            }
        }
    }

    private static void setupRepairPrice(User user) {
        ArrayList<CarService> list = ManagerDAO.displayRepairCarService(user);
        System.out.println("************Repair/Maintenance and Repair Services");
        Set<Integer> sIdSet = new HashSet<>();
        for(int i=0; i<list.size(); i++){
            System.out.println("S_ID = " + list.get(i).getSId() + " NAME = " + list.get(i).getName());
            sIdSet.add(list.get(i).getSId());
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("----Enter S_ID FROM THE FETCHED RESULTS----");
            Integer s_id = Integer.parseInt(reader.readLine());
            System.out.println("Enter Price For Honda");
            Integer hondaPrice = Integer.parseInt(reader.readLine());
            System.out.println("Enter Price For Infiniti");
            Integer infinitiPrice = Integer.parseInt(reader.readLine());
            System.out.println("Enter Price For Lexus");
            Integer lexusPrice = Integer.parseInt(reader.readLine());
            System.out.println("Enter Price For Nissan");
            Integer nissanPrice = Integer.parseInt(reader.readLine());
            System.out.println("Enter Price For Toyota");
            Integer toyotaPrice = Integer.parseInt(reader.readLine());

            if(sIdSet.contains(s_id)){
                String s = ManagerDAO.setupRepairPrice(user, s_id, hondaPrice, infinitiPrice, lexusPrice, nissanPrice, toyotaPrice);
                System.out.println(s);
            } else {
                System.out.println("Incorrect S_ID ENTERED, try again!!");
                setupRepairPrice(user);
            }
        } catch (Exception e) {
            System.out.println("Wrong Input, try again!!");
            setupRepairPrice(user);
        }
    }

    private static void setupMaintenancePrice(User user) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("----Setup Maintenance Price Menu----");
            System.out.println("Enter Bundle (A, B or C)");
            String bundle = reader.readLine();
            Set<String> bundleSet = new HashSet<>();
            bundleSet.add("A");
            bundleSet.add("B");
            bundleSet.add("C");
            if (bundleSet.contains(bundle)){
                System.out.println("Enter Price For Honda");
                Integer hondaPrice = Integer.parseInt(reader.readLine());
                System.out.println("Enter Price For Infiniti");
                Integer infinitiPrice = Integer.parseInt(reader.readLine());
                System.out.println("Enter Price For Lexus");
                Integer lexusPrice = Integer.parseInt(reader.readLine());
                System.out.println("Enter Price For Nissan");
                Integer nissanPrice = Integer.parseInt(reader.readLine());
                System.out.println("Enter Price For Toyota");
                Integer toyotaPrice = Integer.parseInt(reader.readLine());
                String s = ManagerDAO.setupMaintenancePrice(user, bundle, hondaPrice, infinitiPrice, lexusPrice, nissanPrice, toyotaPrice);
                System.out.println(s);
            } else {
                System.out.println("Incorrect Bundle Entered! Try Again");
                setupMaintenancePrice(user);
            }
        } catch (Exception e) {
            System.out.println("Wrong Input, try again!!");
            setupMaintenancePrice(user);
        }
    }

}
