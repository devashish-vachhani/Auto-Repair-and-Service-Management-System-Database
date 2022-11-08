package logic.Customer;

import dao.*;
import logic.landing.Landing;
import models.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CustomerUI {
    public static void customerUI(BufferedReader reader, Long UserId, Integer ScId) throws NumberFormatException, IOException {
        String in_1, in_2;
        while (true) {
            System.out.println("1.View and Update Profile");
            System.out.println("2.View and Schedule Service");
            System.out.println("3.Invoices");
            System.out.println("4.Logout");
            in_1 = reader.readLine();
            switch (Integer.parseInt(in_1)) {
                case 1:
                    profilePage(UserId, ScId);
                    break;
                case 2:
                    servicePage(UserId, ScId);
                    break;
                case 3:
                    invoicePage(UserId, ScId);
                    break;
                case 4:
                    Landing.homeMenu();
                default:
                    System.out.println("Enter a valid choice");
                    break;
            }
        }
    }

    private static void profilePage(Long UserId, Integer ScId) throws IOException {
        String input;
        ArrayList<Object> inputList;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("1.View Profile");
        System.out.println("2.Add Car");
        System.out.println("3.Delete Car");
        System.out.println("4.Go Back");
        input = reader.readLine();
        switch (Integer.parseInt(input)) {
            case 1:
                CustomerDAO.viewCustomer(UserId, ScId);
                VehicleDAO.viewVehicles(UserId, ScId);
                profilePage(UserId, ScId);
                break;
            case 2:
                inputList = addCarInput();
                addCarMenu(UserId, ScId, inputList);
                break;
            case 3:
                VehicleDAO.viewVehicles(UserId, ScId);
                deleteCarMenu(UserId, ScId);
                break;
            case 4:
                customerUI(reader, UserId, ScId);
                break;
            default:
                System.out.println("Enter a valid choice");
                profilePage(UserId, ScId);
        }
    }

    private static ArrayList<Object> addCarInput() throws IOException {
        ArrayList<Object> inputList = new ArrayList<>();
        String[] args;
        String Vin, Brand;
        Long Mileage;
        Integer Year;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter | separated Integer String Vin, String Brand, Long Mileage, Integer Year");
        args = reader.readLine().split("[|]");
        Vin = args[0];
        Brand = args[1];
        Mileage = Long.parseLong(args[2]);
        Year = Integer.parseInt(args[3]);
        inputList.add(Vin);
        inputList.add(Brand);
        inputList.add(Mileage);
        inputList.add(Year);
        return inputList;
    }

    private static void addCarMenu(Long UserId, Integer ScId, ArrayList<Object> inputList) throws IOException {
        String input;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("1.Save Information");
        System.out.println("2.Cancel");
        input = reader.readLine();
        switch (Integer.parseInt(input)) {
            case 1:
                if (VehicleDAO.insertVehicle((String) inputList.get(0), UserId, ScId, (String) inputList.get(1), (Long) inputList.get(2), (Integer) inputList.get(3))) {
                    System.out.println("The vehicle record was saved");
                    CustomerDAO.updateCustomerStatus(UserId, ScId, 1);
                } else {
                    System.out.println("Failed");
                }
                customerUI(reader, UserId, ScId);
            case 2:
                customerUI(reader, UserId, ScId);
            default:
                System.out.println("Enter a valid choice");
                addCarMenu(UserId, ScId, inputList);
        }
    }

    private static void deleteCarMenu(Long UserId, Integer ScId) throws IOException {
        String input, arg;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("1.Select the car to delete");
        System.out.println("2.Go Back");

        input = reader.readLine();

        switch (Integer.parseInt(input)) {
            case 1:
                System.out.println("Enter String Vin");
                arg = reader.readLine();
                if (VehicleDAO.deleteVehicle(arg)) {
                    System.out.println("The vehicle was deleted");
                    if(VehicleDAO.countVehicles(UserId, ScId) == 0) {
                        CustomerDAO.updateCustomerStatus(UserId, ScId, 0);
                    }
                } else {
                    System.out.println("Failed");
                }
                customerUI(reader, UserId, ScId);
            case 2:
                customerUI(reader, UserId, ScId);
            default:
                System.out.println("Enter a valid choice");
                deleteCarMenu(UserId, ScId);
        }
    }

    private static void servicePage(Long UserId, Integer ScId) throws IOException {
        String input, Vin;
        String[] args;
        Long Mileage;
        Set<String> cart = new HashSet<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("1.View Service History");
        System.out.println("2.Schedule Service");
        System.out.println("3.Go Back");

        input = reader.readLine();
        switch (Integer.parseInt(input)) {
            case 1:
                serviceHistoryMenu(UserId, ScId);
                break;
            case 2:
                System.out.println("Enter | separated Integer String Vin, Long Mileage");
                args = reader.readLine().split("[|]");
                Vin = args[0];
                Mileage = Long.parseLong(args[1]);
                scheduleServiceMenu(UserId, ScId, Vin, Mileage, cart);
            case 3:
                customerUI(reader, UserId, ScId);
                break;
            default:
                System.out.println("Enter a valid choice");
                servicePage(UserId, ScId);
        }
    }

    private static void serviceHistoryMenu(Long UserId, Integer ScId) throws IOException {
        String input, Vin;
        ArrayList<ServiceHistory> serviceHistories;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter String Vin");
        Vin = reader.readLine();
        System.out.println("1.Show History");
        System.out.println("2.Go Back");
        input = reader.readLine();
        switch (Integer.parseInt(input)) {
            case 1:
                serviceHistories = ServiceHistoryDAO.viewServiceHistory(UserId, ScId, Vin);
                if(serviceHistories.size() == 0) {
                    System.out.println("No service history to display");
                }
                for (int i = 0; i < serviceHistories.size(); i++) {
                    ServiceHistory serviceHistory = serviceHistories.get(i);
                    System.out.println(i+1 + ".SERVICE ID: " + serviceHistory.getSId() + " VIN: " + serviceHistory.getVin() + " SERVICE TYPE: " + serviceHistory.getType() + " SERVICE COST: " + serviceHistory.getPrice() + " MECHANIC NAME: " + serviceHistory.getName() + " SERVICE DATE: " + serviceHistory.getServiceDate());
                }
                System.out.println();
                break;
            case 2:
                break;
            default:
                System.out.println("Enter a valid choice");
                serviceHistoryMenu(UserId, ScId);
                break;
        }
        servicePage(UserId, ScId);
    }

    private static void scheduleServiceMenu(Long UserId, Integer ScId, String Vin, Long Mileage, Set<String> cart) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input;
        while(true) {
            System.out.println("1.Add Schedule Maintenance");
            System.out.println("2.Add Schedule Repair");
            System.out.println("3.View cart and select schedule time");
            System.out.println("4.Go Back");
            input = reader.readLine();
            switch (Integer.parseInt(input)) {
                case 1:
                    scheduleMaintenanceMenu(UserId, ScId, Vin, Mileage, cart);
                    break;
                case 2:
                    scheduleRepairMenu(UserId, ScId, Vin, Mileage, cart);
                    break;
                case 3:
                    if(!cart.isEmpty()) {
                        viewCartScheduleTimeMenu(UserId, ScId, Vin, Mileage, cart);
                    }
                    else {
                        System.out.println("The cart is empty.");
                    }
                    break;
                case 4:
                    cart.clear();
                    servicePage(UserId, ScId);
                    break;
                default:
                    System.out.println("Enter a valid choice");
                    break;
            }
        }
    }

    private static void scheduleMaintenanceMenu(Long UserId, Integer ScId, String Vin, Long Mileage, Set<String> cart) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input, NextService = "", newService = "";
        Vehicle vehicle = VehicleDAO.viewVehicle(Vin);
        switch (vehicle.getLastService()) {
            case "O":
            case "C":
                NextService = "A";
                break;
            case "A":
                NextService = "B";
                break;
            case "B":
                NextService = "C";
                break;
        }
        CarService carService = CarServiceDAO.viewCarService(NextService);
        OfferedPrice offeredPrice = OfferedPriceDAO.viewOfferedPrice(carService.getSId(), ScId, vehicle.getBrand());
        if(offeredPrice == null) {
            System.out.println("The price of the" + NextService + "maintenance service is not available");
            scheduleServiceMenu(UserId, ScId, Vin, Mileage, cart);
        }
        System.out.println("The price of the " + NextService + " maintenance service is " + offeredPrice.getPrice());
        System.out.println();
        System.out.println("1.Accept and add to cart");
        System.out.println("2.Decline and go back to previous page");
        input = reader.readLine();
        switch (Integer.parseInt(input)) {
            case 1:
                newService = NextService;
                cart.add(newService);
                break;
            case 2:
                break;
            default:
                System.out.println("Enter a valid choice");
                scheduleMaintenanceMenu(UserId, ScId, Vin, Mileage, cart);
                break;
        }
    }

    private static void scheduleRepairMenu(Long UserId, Integer ScId, String Vin, Long Mileage, Set<String> cart) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input, category = "", newService = "";
        Integer idx;
        ArrayList<String> names;
        System.out.println("1.Engine Services");
        System.out.println("2.Exhaust Services");
        System.out.println("3.Electrical Services");
        System.out.println("4.Transmission Services");
        System.out.println("5.Tire Services");
        System.out.println("6.Heating and AC Services");
        System.out.println("7.Go Back");
        input = reader.readLine();
        switch (Integer.parseInt(input)) {
            case 1:
                category = "ENGINESERVICES";
                break;
            case 2:
                category = "EXHAUSTSERVICES";
                break;
            case 3:
                category = "ELECTRICALSERVICES";
                break;
            case 4:
                category = "TRANSMISSIONSERVICES";
                break;
            case 5:
                category = "TIRESERVICES";
                break;
            case 6:
                category = "HEATINGANDACSERVICES";
                break;
            case 7:
                scheduleServiceMenu(UserId, ScId, Vin, Mileage, cart);
                break;
            default:
                System.out.println("Enter a valid choice");
                scheduleRepairMenu(UserId, ScId, Vin, Mileage, cart);
                break;
        }
        names = CarServiceRepair.viewNamesByCategory(category);
        for (int i = 0; i < names.size(); i++) {
            System.out.println(i + 1 + "." + names.get(i));
        }
        System.out.println(names.size() + 1 + ".Go Back");
        input = reader.readLine();
        idx = Integer.parseInt(input) - 1;
        if (idx == names.size()) scheduleRepairMenu(UserId, ScId, Vin, Mileage, cart);
        newService = names.get(idx);
        cart.add(newService);
    }

    private static void viewCartScheduleTimeMenu(Long UserId, Integer ScId, String Vin, Long Mileage, Set<String> cart) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input, cartString = "";
        Integer inputInt, requiredServiceHours, SeId, ServiceDate, AmountCharged;
        ArrayList<MechanicScheduleSlots> mechanicScheduleSlots;
        MechanicScheduleSlots mechanicScheduleSlot;
        Set<Integer> SIds;
        for (String service : cart) {
            System.out.println(service);
        }
        System.out.println("1.Proceed with scheduling");
        System.out.println("2.Go back");
        input = reader.readLine();
        switch (Integer.parseInt(input)) {
            case 1:
                for (String service : cart) {
                    cartString = cartString + "'" + service + "',";
                }
                cartString = cartString.substring(0, cartString.length() - 1);
                requiredServiceHours = ScheduleServiceDAO.countTotalHours(Vin, cartString);
                System.out.println(requiredServiceHours);
                if(requiredServiceHours != -1) {
                    mechanicScheduleSlots = MechanicScheduleSlotsDAO.viewAvailableMechanicSlots(ScId, requiredServiceHours);
                    for(int i=0; i<mechanicScheduleSlots.size(); i++) {
                        mechanicScheduleSlot = mechanicScheduleSlots.get(i);
                        System.out.println(i+1 + "." + mechanicScheduleSlot.getWeek() + " " + mechanicScheduleSlot.getSlotDay() + " " + mechanicScheduleSlot.getSlotsMin() + " " + mechanicScheduleSlot.getMechId() + " " + mechanicScheduleSlot.getSlotIdMin());
                    }
                    inputInt = Integer.parseInt(reader.readLine());
                    mechanicScheduleSlot = mechanicScheduleSlots.get(inputInt-1);
                    SeId = ServiceEventDAO.getNewSeId() + 1;
                    ServiceDate = Integer.parseInt(mechanicScheduleSlot.getSlotDay().toString() + mechanicScheduleSlot.getWeek().toString());
                    AmountCharged = AmountChargedDAO.viewAmountCharged(ScId, Vin, cartString);
                    ServiceEventDAO.insertServiceEvent(SeId, Vin, mechanicScheduleSlot.getMechId(), ScId, UserId, AmountCharged, ServiceDate, requiredServiceHours);
                    SIds = CarServiceDAO.getSIds(cartString);
                    ServiceEventDetailsDAO.insertServiceEventDetails(SeId, SIds);
                    MechanicScheduleDAO.updateMechanicSchedule(mechanicScheduleSlot.getSlotIdMin(), mechanicScheduleSlot.getMechId(), ScId, requiredServiceHours);
                    CustomerDAO.updateCustomerStanding(UserId, ScId, 0);
                    cart.clear();
                }
                scheduleServiceMenu(UserId, ScId, Vin, Mileage, cart);
                break;
            case 2:
                scheduleServiceMenu(UserId, ScId, Vin, Mileage, cart);
                break;
            default:
                System.out.println("Enter a valid choice");
                viewCartScheduleTimeMenu(UserId, ScId, Vin, Mileage, cart);
                break;
        }
    }

    private static void invoicePage(Long UserId, Integer ScId) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input;
        ArrayList<ServiceEvent> serviceEvents = ServiceEventDAO.viewServiceEventByCustomer(UserId, ScId);
        System.out.println("List of invoices:");
        for (int i = 0; i < serviceEvents.size(); i++) {
            ServiceEvent serviceEvent = serviceEvents.get(i);
            System.out.println(i+1 + ".INVOICE ID: " + serviceEvent.getSeId() + " VIN: " + serviceEvent.getVin() + " STATUS: " + serviceEvent.getStatus());
        }
        System.out.println();
        System.out.println("1.View Invoice Details");
        System.out.println("2.Pay Invoices");
        System.out.println("3.Go Back");
        input = reader.readLine();
        switch (Integer.parseInt(input)) {
            case 1:
                viewInvoiceMenu(UserId, ScId);
                break;
            case 2:
                payInvoiceMenu(UserId, ScId);
                break;
            case 3:
                customerUI(reader, UserId, ScId);
                break;
            default:
                System.out.println("Enter a valid choice");
                invoicePage(UserId, ScId);
                break;
        }
    }

    private static void viewInvoiceMenu(Long UserId, Integer ScId) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input;
        Integer SeId;
        ArrayList<Invoice> invoices;
        System.out.println("1.View Invoice");
        System.out.println("2.Go Back");
        input = reader.readLine();
        switch (Integer.parseInt(input)) {
            case 1:
                System.out.println("Enter Integer Invoice ID:");
                SeId = Integer.parseInt(reader.readLine());
                invoices = InvoiceDAO.viewInvoiceBySeId(SeId);
                for (int i = 0; i < invoices.size(); i++) {
                    Invoice invoice = invoices.get(i);
                    System.out.println(i+1 + ".INVOICE ID: " + invoice.getSeId() + " CUSTOMER ID: " + invoice.getCustId() + " VIN: " + invoice.getVin() + " SERVICE DATE: " + invoice.getServiceDate() + " SERVICE ID: " + invoice.getSId() + " SERVICE TYPE: " + invoice.getType() + " INVOICE STATUS: " + invoice.getStatus() + " MECHANIC NAME: " + invoice.getName() + " SERVICE COST: " + invoice.getPrice() + " TOTAL COST: " + invoice.getAmountCharged());
                }
                System.out.println();
                viewInvoiceMenu(UserId, ScId);
                break;
            case 2:
                invoicePage(UserId, ScId);
                break;
            default:
                System.out.println("Enter a valid choice");
                viewInvoiceMenu(UserId, ScId);
                break;
        }
    }
    private static void payInvoiceMenu(Long UserId, Integer ScId) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input;
        Integer SeId;
        System.out.println("1.Pay Invoice");
        System.out.println("2.Go Back");
        input = reader.readLine();
        switch (Integer.parseInt(input)) {
            case 1:
                System.out.println("Enter Integer Invoice ID:");
                SeId = Integer.parseInt(reader.readLine());
                if (InvoiceDAO.updateInvoiceStatusBySeId(SeId)) {
                    if(ServiceEventDAO.countUnpaidInvoicesByCustomer(UserId, ScId) == 0) {
                        CustomerDAO.updateCustomerStanding(UserId, ScId, 1);
                    }
                    payInvoiceMenu(UserId, ScId);
                }
                break;
            case 2:
                invoicePage(UserId, ScId);
                break;
            default:
                System.out.println("Enter a valid choice");
                payInvoiceMenu(UserId, ScId);
                break;
        }
    }
}
