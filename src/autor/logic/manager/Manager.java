package logic.manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
                        setupStorePage(user);
                        flag = false;
                    }
                    case 2 -> {
                        inputEmployeeData(user);
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
            String s = ManagerDAO.addEmployees(user, userId,firstName, lastName, address, emailAddress, phoneNumber, role, startDate, compensation, username);
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
            inputEmployeeData(user);
        }
    }

    private static void setupStorePage(User user) throws IOException {
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
                        inputEmployeeData(user);
                        flag = false;
                    }
                    case 2 -> {
                        setOperationalHours(user);
                        flag = false;
                    }
                    case 3 -> {
                        setupServicePrices(user);
                        flag = false;
                    }
                    case 4 -> {
                        managerMenu(user);
                        flag = false;
                    }
                    default -> System.out.println("try again");
                }
            } catch (IOException e) {
                e.printStackTrace();
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
                            String s = ManagerDAO.setOperationalHours(user, openOnSaturdays);
                            System.out.println(s);
                            flag = false;
                        }
                    }
                    case 2 -> {
                        setupStorePage(user);
                        flag = false;
                    }
                    default -> System.out.println("try again");
                }
            } catch (IOException e) {
                e.printStackTrace();
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
                        setupMaintenancePrice(user);
                        flag = false;
                    }
                    case 2 -> {
                        setupRepairPrice(user);
                        flag = false;
                    }
                    case 3 -> {
                        setupStorePage(user);
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

    private static void setupRepairPrice(User user) {
        ArrayList<CarService> list = ManagerDAO.displayRepairCarService(user);
        System.out.println("************Repair/Maintenance and Repair Services");
        Set<Integer> sIdSet = new HashSet<>();
        for(int i=0; i<list.size(); i++){
            System.out.println("S_ID = " + list.get(i).getSId() + " NAME = " + list.get(i).getName() + " TYPE = " + list.get(i).getType());
            sIdSet.add(list.get(i).getSId());
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("----Enter S_ID FROM THE FETCHED RESULTS----");
            Long s_id = Long.parseLong(reader.readLine());
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
                setupRepairPrice(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
            setupMaintenancePrice(user);
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
                setupMaintenancePrice(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
            setupMaintenancePrice(user);
        }
    }

}
