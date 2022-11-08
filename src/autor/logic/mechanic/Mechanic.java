package logic.mechanic;

import dao.MechanicDAO;
import logic.landing.Landing;
import models.AvailableMechanicsAndSlots;
import models.AvailableSwapRequests;
import models.BookedMechanic;
import models.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Mechanic {
    public static void mechanicMenu(User user) {
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
                        flag = false;
                        ArrayList<BookedMechanic> list = MechanicDAO.viewSchedule(user);
                        System.out.println("******You have been booked to work in the following slots*******");
                        for(int i=0; i<list.size(); i++){
                            System.out.println("DAY = " + list.get(i).getDay() + " WEEK = " + list.get(i).getWeek() + " SLOTS = " + list.get(i).getSlots());
                        }
                        viewScheduleMenu(user);
                    }
                    case 2 -> {
                        flag = false;
                        requestTimeOffDisplayPage(user);
                    }
                    case 3 -> {
                        flag = false;
                        requestSwapInput(user);
//                        mechanicMenu(user);
                    }
                    case 4 -> {
                        flag = false;
                        AcceptOrRejectSwap(user);
//                        mechanicMenu(user);
                    }
                    case 5 -> {
                        flag = false;
                        Landing.homeMenu();
                    }
                    default -> System.out.println("try again");
                }
            } catch (Exception e) {
                System.out.println("Wrong Input, try again!!");
                mechanicMenu(user);
            }
        }
    }

    private static void requestTimeOffDisplayPage(User user) throws IOException {
        ArrayList<AvailableMechanicsAndSlots> list = MechanicDAO.sendRequestTimeOffRetrievalQuery(user);
        System.out.println("******Mechanic Schedule*******");
        Set<Integer> slotIdSet = new HashSet<>();
        for(int i=0; i<list.size(); i++){
            System.out.println("SLOT ID = " + list.get(i).getSlot_id() + " DAY = " + list.get(i).getDay() + " WEEK = " + list.get(i).getWeek() + " SLOTS = " + list.get(i).getSlots());
            slotIdSet.add(list.get(i).getSlot_id());
        }
        Integer inputSlotId = inputSlotIdRequestTimeOff(slotIdSet);
        requestTimeOffMenu(user, inputSlotId);
    }

    private static void AcceptOrRejectSwap(User user) throws IOException {
        ArrayList<AvailableSwapRequests> list = MechanicDAO.requestSwapInfo(user);
        System.out.println("----Accept/Request Swap Page----");
        for(int i=0; i<list.size(); i++){
            System.out.println("REQUEST ID = " + list.get(i).getRegisterId() + " Mechanic Name = " + list.get(i).getMechName() + " SLOT ID = " + list.get(i).getSlotId());
        }
        if (list.size() == 0) {
            System.out.println("No Swap Request currently");
            mechanicMenu(user);
        }
        boolean flag = true;
        while(flag) {
            System.out.println("1. Manage Swap Request");
            System.out.println("2. Go Back");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                String choice = reader.readLine();
                switch (Integer.parseInt(choice)) {
                    case 1 -> {
                        flag = false;
                        Long requestId = ManageSwapInput();
                        ManageSwapMenu(user, requestId);
                        mechanicMenu(user);
                    }
                    case 2 -> {
                        flag = false;
                        mechanicMenu(user);
                    }
                    default -> System.out.println("try again");
                }
            } catch (Exception e) {
                System.out.println("Invalid Inputs. Try again");
                AcceptOrRejectSwap(user);
            }
        }
    }

    private static Integer inputSlotIdRequestTimeOff(Set<Integer> slotIdSet) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("----Enter SlotID from the fetched results----");
        Integer inputSlotId = Integer.parseInt(reader.readLine());
        if(slotIdSet.contains(inputSlotId)){
            return inputSlotId;
        } else {
            System.out.println("Slot ID not in fetched results. Try Again!");
            inputSlotIdRequestTimeOff(slotIdSet);
        }
        return null;
    }

    private static void ManageSwapMenu(User user, Long requestId) {
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
                        flag = false;
                        String s = MechanicDAO.updateSwap(requestId, 1);
                        System.out.println(s);
                        AcceptOrRejectSwap(user);
                    }
                    case 2 -> {
                        flag = false;
                        String s = MechanicDAO.updateSwap(requestId, 2);
                        System.out.println(s);
                        AcceptOrRejectSwap(user);
                    }
                    case 3 -> {
                        flag = false;
                        AcceptOrRejectSwap(user);
                    }
                    default -> System.out.println("try again");
                }
            } catch (Exception e) {
                System.out.println("Invalid Inputs. Try again");
                ManageSwapMenu(user, requestId);
            }
        }
    }

    private static Long ManageSwapInput() throws IOException {
            System.out.println("Enter Request Id");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String requestId = reader.readLine();
            return Long.parseLong(requestId);
    }

    private static void requestSwapMenu(Long inputMech_Id, User user, Integer inputSlotId1, Integer inputSlotId2) {
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
                        flag = false;
                        MechanicDAO.sendSwap(inputMech_Id, user, inputSlotId1, inputSlotId2);
                        mechanicMenu(user);
                    }
                    case 2 -> {
                        flag = false;
                        mechanicMenu(user);
                    }
                    default -> System.out.println("try again");
                }
            } catch (Exception e) {
                System.out.println("Invalid Inputs. Try again");
                requestSwapMenu(inputMech_Id, user, inputSlotId1, inputSlotId2);
            }
        }
    }

    private static void requestSwapInput(User user) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter Swap Request Details");
            System.out.println("Enter Week");
            Integer week = Integer.parseInt(reader.readLine());
            System.out.println("Enter Day");
            Integer day = Integer.parseInt(reader.readLine());
            Integer sc_id = user.getSc_id();
            Long mech_id = user.getUserid();
            ArrayList<AvailableMechanicsAndSlots> list = MechanicDAO.requestSwapRetrievalQuery(sc_id, mech_id, week, day);
            Set<Integer> slotIdSet = new HashSet<>();
            for(int i=0; i<list.size(); i++){
                System.out.println("SLOT ID = " + list.get(i).getSlot_id() + " DAY = " + list.get(i).getDay() + " WEEK = " + list.get(i).getWeek() + " SLOTS = " + list.get(i).getSlots());
                slotIdSet.add(list.get(i).getSlot_id());
            }
            inputSlotIdRequestSwap(user, slotIdSet);
        } catch (Exception e) {
            System.out.println("Invalid Inputs. Try again");
            requestSwapInput(user);
        }
    }

    private static void requestSwapInputAnother(Long inputMechId, User user,Integer inputSlotId) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter Swap Request Details");
            System.out.println("Enter Week");
            Integer week = Integer.parseInt(reader.readLine());
            System.out.println("Enter Day");
            Integer day = Integer.parseInt(reader.readLine());
            Integer sc_id = user.getSc_id();
            ArrayList<AvailableMechanicsAndSlots> list = MechanicDAO.requestSwapRetrievalQuery(sc_id, inputMechId, week, day);
            Set<Integer> slotIdSet = new HashSet<>();
            for(int i=0; i<list.size(); i++){
                System.out.println("SLOT ID = " + list.get(i).getSlot_id() + " DAY = " + list.get(i).getDay() + " WEEK = " + list.get(i).getWeek() + " SLOTS = " + list.get(i).getSlots());
                slotIdSet.add(list.get(i).getSlot_id());
            }
            inputSlotIdRequestSwapAnother(inputMechId, user, inputSlotId, slotIdSet);
        } catch (Exception e) {
            System.out.println("Invalid Inputs. Try again");
            requestSwapInputAnother(inputMechId, user, inputSlotId);
        }
    }

    private static void inputSlotIdRequestSwap(User user, Set<Integer> slotIdSet) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("----Enter SlotID from the fetched results----");
        Integer inputSlotId = Integer.parseInt(reader.readLine());
        if(slotIdSet.contains(inputSlotId)){
            System.out.println("Enter Mechanic ID to swap with");
            Long inputMechId = Long.parseLong(reader.readLine());
            Integer sc_id = user.getSc_id();
            Integer count = MechanicDAO.requestSwapCheckQuery(sc_id, inputMechId);
            if(count == 1){
                requestSwapInputAnother(inputMechId, user, inputSlotId);
            } else{
                System.out.println("Mechanic does not exists in the same service center. Try Again!");
                inputSlotIdRequestSwap(user, slotIdSet);
            }
        } else {
            System.out.println("Slot ID not in fetched results. Try Again!");
            inputSlotIdRequestSwap(user, slotIdSet);
        }
    }

    private static void inputSlotIdRequestSwapAnother(Long inputMech_Id, User user, Integer inputSlodId1, Set<Integer> slotIdSet) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("----Enter SlotID from the fetched results----");
        Integer inputSlotId2 = Integer.parseInt(reader.readLine());
        if(slotIdSet.contains(inputSlotId2)) {
            requestSwapMenu(inputMech_Id, user, inputSlodId1, inputSlotId2);
        } else {
            System.out.println("Slot ID not in fetched results. Try Again!");
            inputSlotIdRequestSwapAnother(inputMech_Id, user, inputSlodId1, slotIdSet);
        }
    }

    private static void requestTimeOffMenu(User user, Integer inputSlotId) {
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
                        flag = false;
                        String s = MechanicDAO.sendRequestTimeOffCountQuery(user, inputSlotId);
                        System.out.println(s);
                        mechanicMenu(user);
                    }
                    case 2 -> {
                        flag = false;
                        mechanicMenu(user);
                    }
                    default -> System.out.println("try again");
                }
            } catch (Exception e) {
                System.out.println("Invalid Inputs. Try again");
                requestTimeOffMenu(user, inputSlotId);
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

    private static void viewScheduleMenu(User user) {
        boolean flag = true;
        while(flag) {
            System.out.println("----View Schedule Page----");
            System.out.println("1. Go Back");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                String choice = reader.readLine();
                switch (Integer.parseInt(choice)) {
                    case 1 -> {
                        flag = false;
                        mechanicMenu(user);
                    }
                    default -> System.out.println("try again");
                }
            } catch (Exception e) {
                System.out.println("Invalid Inputs. Try again");
                viewScheduleMenu(user);
            }
        }
    }
}
