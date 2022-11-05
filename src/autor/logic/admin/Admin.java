package logic.admin;

import dao.AdminDAO;
import dao.UserDAO;
import logic.landing.Landing;
import models.ServiceCenter;
import models.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Admin {
    public static void adminMenu(){
        boolean flag = true;
        while(flag) {
            System.out.println("----Admin Page----");
            System.out.println("1. System Set Up");
            System.out.println("2. Add New Store");
            System.out.println("3. Add New Service");
            System.out.println("4. Logout");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                String choice = reader.readLine();
                switch (Integer.parseInt(choice)) {
                    case 1 -> {
                        //TODO
                        systemSetUpInput();
                        flag = false;
                    }
                    case 2 -> {
                        inputStoreData();
                        flag = false;
                    }
                    case 3 -> {
                        inputServiceData();
                        addServiceMenu();
                        flag = false;
                    }
                    case 4 -> {
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

    private static void inputServiceData() throws  IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Enter Existing Service Category (R, M or B)");
            String serviceType = reader.readLine();
            if (serviceType.length() !=1   || (serviceType.charAt(0) != 'R' && serviceType.charAt(0) != 'M' && serviceType.charAt(0) != 'B')) {
                IOException e = new IOException("Category not found");
                throw e;
            }
            System.out.println("Enter Service Name");
            String serviceName = reader.readLine();
            System.out.println("Enter Duration");
            Long serviceDuration = Long.parseLong(reader.readLine());
            System.out.println("Service data is:" + " " + serviceType + " " + serviceName  + " " + serviceDuration);
            addServiceMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void systemSetUpInput() throws IOException {
        System.out.println("Input Files");
        StringBuilder serviceGeneralInfoQuery = new StringBuilder();
        StringBuilder storeGeneralInfoQuery = new StringBuilder();
        try {
            System.out.println("Enter Service General Info File Name");
            String file1 = new BufferedReader(new InputStreamReader(System.in)).readLine();
            BufferedReader reader1 = new BufferedReader(new FileReader(file1));
            String line1;

            while((line1 = reader1.readLine()) != null){
                serviceGeneralInfoQuery.append(line1);
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Redirect to System Setup Input
            System.out.println("Service General Info File Not Found, Try Again");
            systemSetUpInput();;
        }
        try {
            System.out.println("Enter Store General Info File Name");
            String file2 = new BufferedReader(new InputStreamReader(System.in)).readLine();
            BufferedReader reader2 = new BufferedReader(new FileReader(file2));
            String line2;
            while((line2 = reader2.readLine()) != null){
                storeGeneralInfoQuery.append(line2);
            }
        } catch (IOException e){
            e.printStackTrace();
            // Redirect to System Setup Input
            System.out.println("Store General Info File Not Found, Try Again");
            systemSetUpInput();;
        }
        systemSetUpMenu(serviceGeneralInfoQuery.toString(), storeGeneralInfoQuery.toString());
    }

    private static void systemSetUpMenu(String serviceGeneralInfoQuery, String storeGeneralInfoQuery) {
        boolean flag = true;
        while(flag) {
            System.out.println("----System Set Up Menu Page----");
            System.out.println("1. Upload Service General Information");
            System.out.println("2. Upload Store General Information");
            System.out.println("3. Go Back");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                String choice = reader.readLine();
                switch (Integer.parseInt(choice)) {
                    case 1 -> {
                        AdminDAO.executeServiceGeneralInfoQuery(serviceGeneralInfoQuery);
                        flag = false;
                    }
                    case 2 -> {
                        AdminDAO.executeStoreGeneralInfoQuery(storeGeneralInfoQuery);
                        flag = false;
                    }
                    case 3 -> {
                        adminMenu();
                        flag = false;
                    }
                    default -> System.out.println("try again");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void addServiceMenu() {
        boolean flag = true;
        while(flag) {
            System.out.println("----Add Service Menu Page----");
            System.out.println("1. Add Service");
            System.out.println("2. Go Back");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                String choice = reader.readLine();
                switch (Integer.parseInt(choice)) {
                    case 1 -> {
                        AdminDAO.addService();
                        flag = false;
                    }
                    case 2 -> {
                        adminMenu();
                        flag = false;
                    }
                    default -> System.out.println("try again");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private static void addStoreMenu() {
        boolean flag = true;
        while(flag) {
            System.out.println("----Add Store Page----");
            System.out.println("1. Add Store");
            System.out.println("2. Go Back");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                String choice = reader.readLine();
                switch (Integer.parseInt(choice)) {
                    case 1 -> {
                        AdminDAO.addStore();
                        flag = false;
                    }
                    case 2 -> {
                        adminMenu();
                        flag = false;
                    }
                    default -> System.out.println("try again");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void inputStoreData() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Enter Service Center ID");
            Long serviceCenterId = Long.parseLong(reader.readLine());
            System.out.println("Enter Address");
            String address = reader.readLine();
            System.out.println("Enter Manager's First Name");
            String mgrFirstName = reader.readLine();
            System.out.println("Enter Manager's Last Name");
            String mgrLastName = reader.readLine();
            System.out.println("Enter Manager's Username");
            String mgrUsername = reader.readLine();
            System.out.println("Enter Manager's Password");
            String mgrPassword = reader.readLine();
            System.out.println("Enter Manager's Salary");
            Long mgrSalary = Long.parseLong(reader.readLine());
            System.out.println("Enter Manager's Employee ID");
            Long mgrId = Long.parseLong(reader.readLine());
            System.out.println("Enter Mechanics Max Wage");
            Long mechMaxWage = Long.parseLong(reader.readLine());
            System.out.println("Enter Mechanics Min Wage");
            Long mechMinWage = Long.parseLong(reader.readLine());

    //
    //        System.out.println("Enter Telephone Number");
    //        String tel = reader.readLine();
    //        System.out.println("Enter State");
    //        String state = reader.readLine();
    //        System.out.println("Enter Open On Saturdays (Y/N)");
    //        String openSaturdays = reader.readLine();
    //        System.out.println("Enter Hourly Rate");
    //        String hourlyRate = reader.readLine();
            System.out.println("Store data is:" + " " + serviceCenterId + " " + address  + " " + mgrFirstName + " " + mgrLastName  + " " + mgrUsername + " " + mgrPassword + " " +  mgrSalary  + " " + mgrId + " " + mechMaxWage + " " + mechMinWage);
            addStoreMenu();
        } catch (IOException e) {
            e.printStackTrace();
            inputStoreData();
        }
    }
}
