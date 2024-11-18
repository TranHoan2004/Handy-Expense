/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo;

import entity.Expense;
import utils.Validation;

/**
 *
 * @author ADMIN
 */
public class Input {

    private final Expense expense;

    public Input() {
        this.expense = new Expense();
    }

    public Expense getExpense() {
        expense.setDate(Validation.getDate("Nhập ngày (dd/MM/yyyy): "));
        expense.setMoney(Validation.getDouble("Nhập tiền: ", "Chỉ nhập số thực!", 0, Double.MAX_VALUE));
        expense.setContent(Validation.getStringByRegex("Nhập tên đồ vừa mua: ", ".+", ""));
        return expense;
    }

    public String getName() {
        return Validation.getStringByRegex("Nhập tên: ", ".+", "Tên đồ vật không chứa các ký tự đặc biệt");
    }

    public double getAmount() {
        return Validation.getDouble("Giá: ", "Chỉ được chứa các ký tự số!", 0, Double.MAX_VALUE);
    }

    public String getDate() {
        return Validation.getDate("Ngày mua: ");
    }
}
