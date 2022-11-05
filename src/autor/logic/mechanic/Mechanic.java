package logic.mechanic;

import dao.MechanicDAO;
import logic.landing.Landing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Mechanic {
    public static void mechanicMenu() {
        boolean flag = true;
        while(flag) {
            System.out.println("----Mechanic Page----");
            System.out.println("1. View Schedule");
            System.out.println("2. Request TimeOff");
            System.out.println("3. Request Swap");
            System.out.println("4. Accept/Reject Swap");
            System.out.println("5. Logout");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                String choice = reader.readLine();
                switch (Integer.parseInt(choice)) {
                    case 1 -> {
                        MechanicDAO.viewSchedule();
                        viewScheduleMenu();
                        flag = false;
                    }
                    case 2 -> {
                        ArrayList<Object> inputList = requestTimeOffInput();
                        requestTimeOffMenu(inputList);
                        flag = false;
                    }
                    case 3 -> {
                        ArrayList<Object> inputList = requestSwapInput();
                        requestSwapMenu(inputList);
                        flag = false;
                    }
                    case 4 -> {
                        Long requestId = acceptOrRejectSwapInput();
                        acceptOrRejectSwapMenu(requestId);
                        flag = false;
                    }
                    case 5 -> {
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

    private static void acceptOrRejectSwapMenu(Long requestId) {
        boolean flag = true;
        while(flag) {
            System.out.println("----Manage Swap Requests Page----");
            System.out.println("1. Accept Request");
            System.out.println("2. Reject Request");
            System.out.println("3. Go Back");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                String choice = reader.readLine();
                switch (Integer.parseInt(choice)) {
                    case 1 -> {
                        MechanicDAO.updateSwap(requestId);
                        flag = false;
                        mechanicMenu();
                    }
                    case 2 -> {
                        //TODO: update swap status
                        flag = false;
                        mechanicMenu();
                    }
                    case 3 -> {
                        flag = false;
                        mechanicMenu();
                    }
                    default -> System.out.println("try again");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static Long acceptOrRejectSwapInput() throws IOException {
            System.out.println("Enter Request Id");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String requestId = reader.readLine();
            return Long.parseLong(requestId);
    }

    private static void requestSwapMenu(ArrayList<Object> inputList) {
        boolean flag = true;
        while(flag) {
            System.out.println("----Request Swap Page----");
            System.out.println("1. Send Request");
            System.out.println("2. Go Back");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                String choice = reader.readLine();
                switch (Integer.parseInt(choice)) {
                    case 1 -> {
                        MechanicDAO.sendSwap(inputList);
                        flag = false;
                    }
                    case 2 -> {
                        mechanicMenu();
                        flag = false;
                    }
                    default -> System.out.println("try again");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static ArrayList<Object> requestSwapInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter Swap Request Details");
        System.out.println("Enter WeekID");
        String weekId = reader.readLine();
        System.out.println("Enter Day");
        String day = reader.readLine();
        System.out.println("Enter Slot Start ID");
        String slotStartId1 = reader.readLine();
        System.out.println("Enter Slot End ID");
        String slotEndId1 = reader.readLine();
        System.out.println("Enter Employee ID");
        String uid = reader.readLine();
        System.out.println("Enter Slot Start ID");
        String slotStartId2 = reader.readLine();
        System.out.println("Enter Slot End ID");
        String slotEndId2 = reader.readLine();
        ArrayList<Object> list = new ArrayList<>();
        list.add(Long.parseLong(weekId));
        list.add(day);
        list.add(Long.parseLong(slotStartId1));
        list.add(Long.parseLong(slotEndId1));
        list.add(Long.parseLong(uid));
        list.add(Long.parseLong(slotStartId2));
        list.add(Long.parseLong(slotEndId2));
        return list;
    }

    private static void requestTimeOffMenu(ArrayList<Object> inputList) {
        boolean flag = true;
        while(flag) {
            System.out.println("----Request TimeOff Page----");
            System.out.println("1. Send Request");
            System.out.println("2. Go Back");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                String choice = reader.readLine();
                switch (Integer.parseInt(choice)) {
                    case 1 -> {
                        MechanicDAO.sendRequestTimeOff(inputList);
                        flag = false;
                    }
                    case 2 -> {
                        mechanicMenu();
                        flag = false;
                    }
                    default -> System.out.println("try again");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static ArrayList<Object> requestTimeOffInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter Time Slots");
        System.out.println("Enter Week");
        String week = reader.readLine();
        System.out.println("Enter day");
        String day = reader.readLine();
        System.out.println("Enter time");
        String time = reader.readLine();
        System.out.println("Enter Slot Start ID");
        String slotStartId = reader.readLine();
        System.out.println("Enter Slot End ID");
        String slotEndId = reader.readLine();
        ArrayList<Object> list = new ArrayList<>();
        list.add(week);
        list.add(day);
        list.add(time);
        // Long
        list.add(Long.parseLong(slotStartId));
        list.add(Long.parseLong(slotEndId));
        return list;
    }

    private static void viewScheduleMenu() {
        boolean flag = true;
        while(flag) {
            System.out.println("----View Schedule Page----");
            System.out.println("1. Go Back");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                String choice = reader.readLine();
                switch (Integer.parseInt(choice)) {
                    case 1 -> {
                        mechanicMenu();
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
