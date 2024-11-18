/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ui;

import controller.Controller;
import entity.Expense;
import java.util.ArrayList;
import utils.Validation;

/**
 *
 * @author ADMIN
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Menu menu = new Menu();
        Controller controller = new Controller();
        try {
            controller.createFile();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        while (true) {
            try {
                menu.menu();
                switch (menu.getInt()) {
                    case 1:
                        System.out.println("------------ Add an expense ------------");
                        do {
                            controller.addExpense();
                            System.out.println("Successfully!");
                        } while (Validation.checkYN());
                        break;
                    case 2:
                        if (controller.getList().isEmpty()) {
                            System.out.println("List is empty");
                            break;
                        }
                        System.out.println("------------ Display all expenses ------------");
                        System.out.printf("%-12s%-15s%-15s%s\n", "ID", "Date", "Amount", "Content");
                        for (Expense expense : controller.getList()) {
                            System.out.println(expense.toString());
                        }
                        System.out.println("Total: " + controller.getTotal());
                        break;
                    case 3:
                        try {
                            System.out.println("------------ Delete an expense ------------");
                            controller.delete();
                            System.out.println("Successfully!");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 4:
                        if (controller.getList().isEmpty()) {
                            System.out.println("List is empty");
                            break;
                        }
                        try {
                            System.out.println("------------ Change information of an expense ------------");
                            System.out.printf("%-12s%-15s%-15s%s\n", "ID", "Date", "Amount", "Content");
                            for (Expense ex : controller.getList()) {
                                System.out.println(ex.toString());
                            }
                            controller.updateBySelection();
                            System.out.println("Successfully");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 5:
                        ArrayList<Expense> list = controller.search();
                        if (list.isEmpty()) {
                            throw new Exception("No results");
                        }
                        try {
                            System.out.println("------------ Results ------------");
                            System.out.printf("%-12s%-15s%-15s%s\n", "ID", "Date", "Amount", "Content");
                            for (Expense ex : list) {
                                System.out.println(ex.toString());
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 6:
                        return;
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
