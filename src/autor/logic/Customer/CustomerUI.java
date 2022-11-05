package logic.Customer;

import dao.VehicleDAO;
import logic.landing.Landing;
import models.Vehicle;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class CustomerUI {
    public static void customerUI(BufferedReader reader) throws NumberFormatException, IOException {
        ArrayList<Vehicle> vehicles;
        Integer CustomerId, Year;
        String in_1, in_2, in_3, arg, Vin, CarManufacturer;
        Long CurrentMileage;
        String[] args;
        while(true) {
            System.out.println("1.View and Update Profile");
            System.out.println("2.View and Schedule Service");
            System.out.println("3.Invoices");
            System.out.println("4.Logout");
            in_1 = reader.readLine();
            switch (Integer.parseInt(in_1)) {
                case 1:
                    System.out.println("1.View Profile");
                    System.out.println("2.Add Car");
                    System.out.println("3.Delete Car");
                    System.out.println("4.Go Back");
                    in_2 = reader.readLine();
                    switch (Integer.parseInt(in_2)) {
                        case 1:

                        case 2:
                            System.out.println("Enter | separated Integer CustomerID, String Vin, String CarManufacturer, Long CurrentMileage, Integer Year");
                            args = reader.readLine().split("[|]");
                            CustomerId = Integer.parseInt(args[0]);
                            Vin = args[1];
                            CarManufacturer = args[2];
                            CurrentMileage = Long.parseLong(args[3]);
                            Year = Integer.parseInt(args[4]);

                            System.out.println("1.Save Information");
                            System.out.println("2.Cancel");

                            in_3 = reader.readLine();

                            switch (Integer.parseInt(in_3)) {
                                case 1:
                                    if (VehicleDAO.insertVehicle(Vin, CarManufacturer, CurrentMileage, "", Year, CustomerId)) {
                                        System.out.println("The vehicle record was saved");
                                    } else {
                                        System.out.println("Failed");
                                    }
                                case 2:
                            }
                            break;
                        case 3:
                            System.out.println("Enter CustomerID");
                            arg = reader.readLine();
                            CustomerId = Integer.parseInt(arg);
                            vehicles = VehicleDAO.viewVehicles(CustomerId);
                            for(Vehicle instance: vehicles) {
                                System.out.println(String.format("VIN=%s Car Manufacturer=%s Current mileage=%s Year=%d", instance.getVin(), instance.getCarManufacturer(), instance.getCurrentMileage(), instance.getYear()));

                            }

                            System.out.println("1.Select the car to delete");
                            System.out.println("2.Go Back");

                            in_3 = reader.readLine();

                            switch (Integer.parseInt(in_3)) {
                                case 1:
                                    System.out.println("Enter String Vin");
                                    arg = reader.readLine();
                                    Vin = arg;
                                    if(VehicleDAO.deleteVehicle(Vin)) {
                                        System.out.println("Operation Successful");
                                    } else {
                                        System.out.println("Failed");
                                    }
                                case 2:
                            }
                            break;
                        case 4:
                            break;
                    }
                    break;
                case 2:
                    System.out.println("1.View Service History");
                    System.out.println("2.Schedule Service");
                    System.out.println("3.return");
                    in_2 = reader.readLine();
                    switch (Integer.parseInt(in_2)) {
                        case 1:
                        case 2:
                        case 3:
                    }
                case 3:
                    System.out.println("1.View Invoice Details");
                    System.out.println("2.Pay Invoices");
                    System.out.println("3.return");
                    in_2 = reader.readLine();
                    switch (Integer.parseInt(in_2)) {
                        case 1:
                        case 2:
                        case 3:
                    }
                case 4:
                    Landing.homeMenu();
            }
            System.out.println("Enter a valid choice");
        }
    }
}
