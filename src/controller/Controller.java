/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import bo.FileManager;
import bo.Input;
import bo.Manager;
import entity.Expense;
import java.util.ArrayList;
import utils.Validation;

/**
 *
 * @author ADMIN
 */
public class Controller {

    private Manager manager;
    private final FileManager file;
    private Input input;

    public Controller() {
        this.file = new FileManager();
        this.manager = new Manager();
    }

    public void addExpense() throws Exception {
        manager = new Manager(file.getMaxID());
        openFile();
        input = new Input();
        Expense expense = input.getExpense();
        manager.addToList(expense);
        file.createText(expense.toString()); 
    }

    public void delete() throws Exception {
        openFile();
        if (manager.getList().isEmpty()) { //chinh sua 
            throw new Exception("List is empty, cannot be deleted");
        }
        int id = Validation.getInt("Enter ID: ", "Only one positive number!", "Out of range!", 1, manager.getList().size());
        manager.deleteExpense(id);
        updateFile();
    }

    public void updateBySelection() throws Exception {
        openFile();
        String menu1 = """
                1. Sửa tên
                2. Sửa số tiền đã tiêu
                3. Sửa ngày\s
                Chọn tiếp:\s""";
        int choice = Validation.getInt(menu1, "Number only!", "Out of range", 1, 3);
        int id = Validation.getInt("ID: ", "Number only!", "Out of range", 1, manager.getList().size());
        Expense expense = manager.getExpense(id);
        if (expense == null) {
            throw new Exception("There is no item has this id");
        }
        switch (choice) {
            case 1:
                String name = input.getName();
                manager.updateName(expense, name);
                break;
            case 2:
                double amount = input.getAmount();
                manager.updatePrice(expense, amount);
                break;
            case 3:
                String date = input.getDate();
                manager.updateDate(expense, date);
                break;
        }
        updateFile();
    }

    public ArrayList<Expense> search() throws Exception {
        openFile();
        if (manager.getList().isEmpty()) {
            throw new Exception("List is empty");
        }
        String string = Validation.getStringByRegex("Search: ", "[A-Za-z0-9,. ]+", "Do not have special characters excepts dot and comma");
        return manager.searchExpense(string);
    }

    public int getTotal() {
        return manager.calcSummaryOfExpense();
    }

    public ArrayList<Expense> getList() throws Exception {
        openFile();
        return manager.getList();
    }

    public void createFile() {
        file.createNewFile();
    }

    private void updateFile() throws Exception {
        file.clear();
        for (Expense expense : manager.getList()) {
            file.createText(expense.toString());
        }
    }

    private void openFile() throws Exception {
        manager.setList(file.readFromFile());
    }
}
