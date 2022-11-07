package logic.receptionist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import dao.ReceptionistDAO;
import logic.landing.Landing;
import models.PendingCustomers;
import models.User;

public class Receptionist {
    public static void receptionistMenu(User user) {
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
                        inputAddCustomerDetails(user);
                        flag = false;
                    }
                    case 2 -> {
                        findCustomerPendingInvoice(user);
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

    private static void findCustomerPendingInvoice(User user) {
        ArrayList<PendingCustomers> list = ReceptionistDAO.viewPendingCustomers(user);
        System.out.println("********Pending Customers*********");
        for(int i=0; i<list.size(); i++){
            System.out.println("CUSTOMER ID = " + list.get(i).getCust_id() + " CUSTOMER'S FIRST NAME = " + list.get(i).getCustFirstName() + " CUSTOMER'S LAST NAME = " + list.get(i).getCustLastName() + " SERVICE EVENT ID = " + list.get(i).getSe_id() + " SERVICE DATE = " + list.get(i).getServiceDate() + " AMOUNT CHARGED = " + list.get(i).getAmountCharged());
        }
    }

    private static void inputAddCustomerDetails(User user) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter Customer Id");
            Long custId = Long.parseLong(reader.readLine());
            // SC_ID from receptionist
            System.out.println("Enter User Name");
            String custUsername = reader.readLine();
            System.out.println("Enter Customer First Name");
            String custFirstName = reader.readLine();
            System.out.println("Enter Customer Last Name");
            String custLastName = reader.readLine();
            System.out.println("Enter Customer's Address");
            String custAddress = reader.readLine();
            String role = "CUSTOMER";
            System.out.println("Enter Email Address");
            String custEmail = reader.readLine();
            System.out.println("Enter Phone Number");
            Long custPhoneNumber = Long.parseLong(reader.readLine());
            System.out.println("Enter VIN Number");
            String vin = reader.readLine();
            System.out.println("Enter Car Manufacturer");
            String brand = reader.readLine();
            System.out.println("Enter Current Mileage");
            String mileage = reader.readLine();
            System.out.println("Enter Year");
            String year = reader.readLine();
            Integer standing = 1;
            Integer status = 1;
            String s = ReceptionistDAO.addCustomer(user, custId, custUsername, custFirstName, custLastName, custAddress, role, custEmail, custPhoneNumber, vin, brand, mileage, year, status, standing);
            System.out.println(s);
            addCustomerMenu(user);
        } catch (IOException e) {
            e.printStackTrace();
            inputAddCustomerDetails(user);
        }
    }
    private static void addCustomerMenu(User user) throws IOException {
        boolean flag = true;
        while (flag) {
            System.out.println("1. Go Back");
            BufferedReader readerNew = new BufferedReader(new InputStreamReader(System.in));
            try {
                String choice = readerNew.readLine();
                if (Integer.parseInt(choice) == 1) {
                    receptionistMenu(user);
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
