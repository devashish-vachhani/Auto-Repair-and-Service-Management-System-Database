package logic.queries;

import dao.QueryDAO;
import logic.landing.Landing;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class QueryDB {
    public static void displayMenu(){
        boolean flag = true;
        while(flag) {
            System.out.println("----Query List----");
            System.out.println("1. Query1");
            System.out.println("2. Query2");
            System.out.println("3. Query3");
            System.out.println("4. Query4");
            System.out.println("5. Query5");
            System.out.println("6. Query6");
            System.out.println("7. Go Back");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                String choice = reader.readLine();
                switch (Integer.parseInt(choice)) {
                    case 1 -> {
                        System.out.println("Which service center has the most number of customers");
                        System.out.print("OUTPUT: ");
                        Integer output = QueryDAO.query1Output();
                        System.out.println(output);
                        flag = false;
                    }
                    case 2 -> {
                        System.out.println("What is the average price of an Evaporator Repair for Hondas across all service centers");
                        System.out.print("OUTPUT: ");
                        Float output = QueryDAO.query2Output();
                        System.out.println(output);
                        flag = false;
                    }
                    case 3 -> {
                        System.out.println("Which customer(s) have unpaid invoices in Service Center 30003");
                        System.out.println("OUTPUT:");
                        //

                        flag = false;
                    }
                    case 4 -> {
                        System.out.println("List all services that are listed as both maintenance and repair services globally");
                        System.out.println("OUTPUT:");
                        //

                        flag = false;
                    }
                    case 5 -> {
                        System.out.println("What is the difference between the cost of doing a belt replacement + schedule A\n" +
                                "on a Toyota at center 30001 vs 30002?");
                        System.out.print("OUTPUT: ");
                        Integer output = QueryDAO.query5Output();
                        System.out.println(output);
                        flag = false;
                    }
                    case 6 -> {
                        System.out.println("What is the next eligible maintenance schedule service for the car with VIN\n" +
                                "34KLE19D?");
                        System.out.println("OUTPUT:");
                        //
                        flag = false;
                    }
                    case 7 -> {
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
}
