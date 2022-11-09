package logic.queries;

import dao.QueryDAO;
import logic.landing.Landing;
import models.CarService;
import models.Customer;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class QueryDB {
    public static void displayMenu(){
        boolean flag = true;
        while(flag) {
            System.out.println("----Query List----");
            System.out.println("1. Query1: Which service center has the most number of customers");
            System.out.println("2. Query2: What is the average price of an Evaporator Repair for HONDAS across all service centers");
            System.out.println("3. Query3: Which customer(s) have unpaid invoices in Service Center 30003");
            System.out.println("4. Query4: List all services that are listed as both maintenance and repair services globally");
            System.out.println("5. Query5: What is the difference between the cost of doing a belt replacement + schedule A on a Toyota at center 30001 vs 30002?");
            System.out.println("6. Query6: What is the next eligible maintenance schedule service for the car with VIN + 34KLE19D?");
            System.out.println("7. Go Back");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                String choice = reader.readLine();
                switch (Integer.parseInt(choice)) {
                    case 1 -> {
                        flag = false;
                        System.out.println("OUTPUT: ");
                        Integer output = QueryDAO.query1Output();
                        System.out.println(output);
                        displayMenu();
                    }
                    case 2 -> {
                        flag = false;
                        System.out.println("OUTPUT: ");
                        Float output = QueryDAO.query2Output();
                        System.out.println(output);
                        displayMenu();
                    }
                    case 3 -> {
                        flag = false;
                        System.out.println("OUTPUT:");
                        ArrayList<Customer> list = QueryDAO.query3Output();
                        for(int i =0; i< list.size(); i++)
                            System.out.println("CUSTOMER ID = " + list.get(i).getUserId() + " CUSTOMER NAME = " + list.get(i).getFName()  + " " + list.get(i).getLName());
                        displayMenu();
                    }
                    case 4 -> {
                        flag = false;
                        System.out.println("OUTPUT:");
                        ArrayList<CarService> list = QueryDAO.query4Output();
                        for(int i =0; i< list.size(); i++)
                            System.out.println("SERVICE ID = " + list.get(i).getSId() + " SERVICE NAME = " + list.get(i).getName());
                        displayMenu();
                    }
                    case 5 -> {
                        flag = false;
                        System.out.println("OUTPUT: ");
                        Float output = QueryDAO.query5Output();
                        System.out.println(output);
                        displayMenu();

                    }
                    case 6 -> {
                        flag = false;
                        System.out.println("OUTPUT:");
                        String res = QueryDAO.query6Output();
                        System.out.println(res);
                        displayMenu();
                    }
                    case 7 -> {
                        flag = false;
                        Landing.homeMenu();
                    }
                    default -> System.out.println("try again");
                }
            } catch (Exception e) {
                System.out.println("Wrong Input. Try Again!");
                displayMenu();
            }
        }
    }
}
