/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.io.BufferedReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author ADMIN
 */
public class Validation {

    public static String removeUnnecessaryBlank(String input) {
        return input.trim().replaceAll("\\s+", " ");
    }

    public static int getInt(String mess, String errorNumberFormat, String errorOutOfRange, int min, int max) {
        while (true) {
            int ret = Integer.parseInt(getStringByRegex(mess, "[0-9]+", errorNumberFormat));
            if (ret < min || ret > max) {
                System.err.println(errorOutOfRange);
            } else {
                return ret;
            }
        }

    }

    public static double getDouble(String msg, String error, double min, double max) {
        while (true) {
            double input = Double.parseDouble(getStringByRegex(msg, "[0-9]*\\.?[0-9]+", error));
            if (input < min || input > max) {
                System.err.println("Số bạn nhập vào vượt ra khỏi phạm vi!");
            } else {
                return input;
            }
        }
    }

    public static String getStringByRegex(String msg, String regex, String err) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print(msg);
            String string = removeUnnecessaryBlank(sc.nextLine());
            if (string.equalsIgnoreCase("")) {
                System.out.println("Không được để trống");
            } else if (string.matches(regex)) {
                return string;
            } else {
                System.err.println(err);
            }
        }
    }

    public static boolean checkYN() {
        String input = getStringByRegex("Bạn có muốn tiếp tục không? (Y/N): ", "[YNyn]", "Chỉ được phép nhập Y cho đồng ý, N cho từ chối");
        return input.equalsIgnoreCase("y");
    }

    public static String getDate(String message) {
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatOutput = new SimpleDateFormat("dd/MMM/yyyy", Locale.ENGLISH);
        while (true) {
            System.out.print(message);
            try {
                String result = sc.nextLine().trim();
                Date date = format.parse(result);
                if (result.equalsIgnoreCase(format.format(date)) && result.matches("^[0-9]{2}/[0-9]{2}/[0-9]{4}$")) {
                    return formatOutput.format(date);
                } else {
                    System.err.println("Định dạng ngày tháng năm không hợp lệ (Ngày tháng phải đủ 2 con số, năm phải đủ 4 con số). Xin mời nhập lại!");
                }
            } catch (ParseException ex) {
                System.err.println("Định dạng ngày tháng năm không hợp lệ (Ngày tháng phải đủ 2 con số, năm phải đủ 4 con số), xin mời nhập lại!");
            }
        }
    }
}
