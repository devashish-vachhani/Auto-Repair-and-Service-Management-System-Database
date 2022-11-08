package logic.landing;

import logic.authentication.Authentication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Landing {

    public static void homeMenu(){
        boolean flag = true;
            while(flag) {
                System.out.println("----Home Page----");
                System.out.println("1. Login");
                System.out.println("2. Run Queries");
                System.out.println("3. Exit");
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                try {
                    String choice = reader.readLine();
                    switch (Integer.parseInt(choice)) {
                        case 1 -> {
                            Authentication.loginMenu();
                            flag = false;
                        }
                        case 2 -> {
                            // TODO
                        }
                        case 3 -> {
                            System.out.println("AUTOR terminated");
                            System.exit(0);
                            flag = false;
                        }
                        default -> System.out.println("try again");
                    }
                } catch (Exception e) {
                    System.out.println("Wrong Input, try again!!");
                    homeMenu();
                }
        }
    }
}
