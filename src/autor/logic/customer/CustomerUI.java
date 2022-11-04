package logic.customer;

import java.io.BufferedReader;
import java.io.IOException;

public class CustomerUI {
    public static void customerUI(BufferedReader reader) throws NumberFormatException, IOException {

        String[] args;
        boolean exit = true;
        while(exit) {
            System.out.println("1.View and Update Profile");
            System.out.println("2.View and Schedule Service");
            System.out.println("3.Invoices");
            System.out.println("4.exit");
            String in_1 = reader.readLine();
            switch (Integer.parseInt(in_1)) {
                case 1:
                    boolean return_to_main_options = true;
                    while(return_to_main_options) {
                        System.out.println("1.View Profile");
                        System.out.println("2.Add Car to Profile");
                        System.out.println("3.Delete Car from Profile");
                        System.out.println("4.return");
                        String in_2 = reader.readLine();
                        switch (Integer.parseInt(in_2)) {
                            case 1:
                            case 2:
                            System.out.println("Enter | separated Integer ServiceCenter, Integer Vin");
                            str = reader.readLine().split("[|]");
                            PID = Integer.parseInt(str[0]);
                            ArticleID = Integer.parseInt(str[1]);
                            Text = str[2];
                            ArticleCRUD.insertArticle(PID,ArticleID,Text);
                            break;
                            case 3:
                            case 4:
                                return_to_main_options = false;
                                return;
                        }
                    }
                    return;
                case 2:
                    boolean return_to_main_options = true;
                    while(return_to_main_options) {
                        System.out.println("1.View Service History");
                        System.out.println("2.Schedule Service");
                        System.out.println("3.return");
                        String in_2 = reader.readLine();
                        switch (Integer.parseInt(in_2)) {
                            case 1:
                            case 2:
                            case 3:
                                return_to_main_options = false;
                                return;
                        }
                    }
                    return;
                case 3:
                while(return_to_main_options) {
                    System.out.println("1.View Invoice Details");
                    System.out.println("2.Pay Invoices");
                    System.out.println("3.return");
                    String in_2 = reader.readLine();
                    switch (Integer.parseInt(in_2)) {
                        case 1:
                        case 2:
                        case 3:
                            return_to_main_options = false;
                            return;
                    }
                }
                return;
                case 4:
                    exit = false;
                    return;

            }
        }
        System.out.println("Enter a valid choice");
    }
}
