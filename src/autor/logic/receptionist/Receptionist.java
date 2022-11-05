package logic.receptionist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import logic.landing.Landing;

public class Receptionist {
    public static void displayMenu() {
        boolean flag = true;
        while (flag) {
            System.out.println("----Receptionist Landing Page----");
            System.out.println("1. Add New Customer Profile");
            System.out.println("2. Find Customers with Pending Invoices");
            System.out.println("3. Logout");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                String choice = reader.readLine();
                switch (Integer.parseInt(choice)) {
                    case 1 -> {
                        addCustomerDetails();
                        flag = false;
                    }
                    case 2 -> {
                        findCustomerPendingInvoice();
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

    private static void findCustomerPendingInvoice() throws IOException {
        // Query Database
        goBack();
    }

    private static void addCustomerDetails() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter Customer Name");
        String name = reader.readLine();
        System.out.println("Enter Address");
        String address = reader.readLine();
        System.out.println("Enter Email Address");
        String emailAddress = reader.readLine();
        System.out.println("Enter Phone Number");
        String phoneNumber = reader.readLine();
        System.out.println("Enter User Name");
        String userName = reader.readLine();
        System.out.println("Enter VIN Number");
        String vinNumber = reader.readLine();
        System.out.println("Enter Car Manufacturer");
        String carManufacturer = reader.readLine();
        System.out.println("Enter Current Mileage");
        String currentMileage = reader.readLine();
        System.out.println("Enter Year");
        String year = reader.readLine();
        // query to add
        goBack();
    }

    private static void goBack() throws IOException {
        boolean flag = true;
        while (flag) {
            System.out.println("1. Go Back");
            BufferedReader readerNew = new BufferedReader(new InputStreamReader(System.in));
            try {
                String choice = readerNew.readLine();
                if (Integer.parseInt(choice) == 1) {
                    displayMenu();
                    flag = false;
                }
                else {
                    System.out.println("try again");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
