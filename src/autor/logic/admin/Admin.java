package logic.admin;

import dao.AdminDAO;
import logic.landing.Landing;

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
            System.out.println("Enter Service ID");
            Long serviceId = Long.parseLong(reader.readLine());
            System.out.println("Enter Service Type (REPAIR, MAINTENANCE or MAINTENANCEREPAIR)");
            String serviceType = reader.readLine();
            String serviceBundle = null;
            String serviceCategory = null;
            if(serviceType.equals("MAINTENANCEREPAIR")){
                System.out.println("Enter Service Category");
                serviceCategory = reader.readLine();
                System.out.println("Enter Service Bundle (A, B or C)");
                serviceBundle = reader.readLine();
            } else if(serviceType.equals("REPAIR")) {
                System.out.println("Enter Service Category");
                serviceCategory = reader.readLine();
            } else if(serviceType.equals("REPAIR")) {
                System.out.println("Enter Service Bundle (A, B or C");
                serviceBundle = reader.readLine();
            } else {
                System.out.println("Incorrect Service Type Entered! Try Again!");
                inputServiceData();
            }
            System.out.println("Enter Service Name");
            String serviceName = reader.readLine();
            System.out.println("Enter Duration for HONDA");
            Integer hondaDuration = Integer.parseInt(reader.readLine());
            System.out.println("Enter Duration for LEXUS");
            Integer lexusDuration = Integer.parseInt(reader.readLine());
            System.out.println("Enter Duration for INFINITY");
            Integer infinityDuration = Integer.parseInt(reader.readLine());
            System.out.println("Enter Duration for NISSAN");
            Integer nissanDuration = Integer.parseInt(reader.readLine());
            System.out.println("Enter Duration for TOYOTA");
            Integer toyotaDuration = Integer.parseInt(reader.readLine());

            System.out.println("Service data is:" + " " + serviceId + " " + serviceType + " " + serviceBundle  + " " + serviceCategory + " " + serviceName + " " + hondaDuration + " " + lexusDuration + " " + infinityDuration + " " + nissanDuration + " " + toyotaDuration);

            addServiceMenu(serviceId, serviceType, serviceBundle, serviceCategory, serviceName, hondaDuration, lexusDuration, infinityDuration, nissanDuration, toyotaDuration);
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

    private static void addServiceMenu(Long serviceId, String serviceType, String serviceBundle, String serviceCategory, String serviceName, Integer hondaDuration, Integer lexusDuration, Integer infinityDuration, Integer nissanDuration, Integer toyotaDuration) {
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
                        String s= AdminDAO.addService(serviceId, serviceType, serviceBundle, serviceCategory, serviceName, hondaDuration, lexusDuration, infinityDuration, nissanDuration, toyotaDuration);
                        System.out.println(s);
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

    private static void addStoreMenu(Integer serviceCenterId, String address, Long telephone, String state, Integer openSaturdays, Integer mechMaxWage, Integer mechMinWage, Integer mechHourlyWage, String mgrFirstName, String mgrLastName, String mgrUsername, String mgrPassword, Integer mgrSalary, Long mgrId, String mgrEmail, Long mgrPhone) {
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
                        String s = AdminDAO.addStore(serviceCenterId, address, telephone, state, openSaturdays, mechMaxWage, mechMinWage, mechHourlyWage, mgrFirstName, mgrLastName, mgrUsername, mgrPassword, mgrSalary, mgrId, mgrEmail, mgrPhone);
                        System.out.println(s);
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
            Integer serviceCenterId = Integer.parseInt(reader.readLine());
            System.out.println("Enter Address");
            String address = reader.readLine();
            System.out.println("Enter Telephone Number");
            Long telephone =  Long.parseLong(reader.readLine());
            System.out.println("Enter State");
            String state = reader.readLine();
            System.out.println("Enter Open On Saturdays (N: 0/ Y: 1)");
            Integer openSaturdays =  Integer.parseInt(reader.readLine());
            System.out.println("Enter Mechanics Max Wage");
            Integer mechMaxWage = Integer.parseInt(reader.readLine());
            System.out.println("Enter Mechanics Min Wage");
            Integer mechMinWage = Integer.parseInt(reader.readLine());
            System.out.println("Enter Mechanics Hourly Wage");
            Integer mechHourlyWage = Integer.parseInt(reader.readLine());
            System.out.println("Enter Manager's First Name");
            String mgrFirstName = reader.readLine();
            System.out.println("Enter Manager's Last Name");
            String mgrLastName = reader.readLine();
            System.out.println("Enter Manager's Email");
            String mgrEmail = reader.readLine();
            System.out.println("Enter Manager's Phone Number");
            Long mgrPhone = Long.parseLong(reader.readLine());
            System.out.println("Enter Manager's Username");
            String mgrUsername = reader.readLine();
            System.out.println("Enter Manager's Password");
            String mgrPassword = reader.readLine();
            System.out.println("Enter Manager's Salary");
            Integer mgrSalary = Integer.parseInt(reader.readLine());
            System.out.println("Enter Manager's Employee ID");
            Long mgrId = Long.parseLong(reader.readLine());
            System.out.println("Store data is:" + " " + serviceCenterId + " " + address  + " " + telephone + " " + state + " " + openSaturdays + " " + mechMaxWage + " " + mechMinWage  + " " + mechHourlyWage + " " + mgrFirstName + " " +  mgrLastName + " " + mgrUsername + " " +  mgrPassword  + " " + mgrSalary + " " + mgrId);
            addStoreMenu(serviceCenterId, address, telephone, state, openSaturdays, mechMaxWage, mechMinWage, mechHourlyWage, mgrFirstName, mgrLastName, mgrUsername, mgrPassword, mgrSalary, mgrId, mgrEmail, mgrPhone);
        } catch (IOException e) {
            e.printStackTrace();
            inputStoreData();
        }
    }
}
