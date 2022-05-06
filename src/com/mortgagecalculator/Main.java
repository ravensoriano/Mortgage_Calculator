package com.mortgagecalculator;

import java.text.NumberFormat;
import java.util.Scanner;


public class Main {
    final static byte MONTHS_IN_YEAR = 12;
    final static byte PERCENT = 100;

    public static void main (String[] args) {
        int principal = (int)read_number("Principal: ", 1000, 1_000_000);
        float annual_interest = (float)read_number("Annual Interest: ", 1, 30);
        byte years = (byte)read_number("Period (Years): ", 1, 30);

        print_mortgage(principal, annual_interest, years);
        print_payment_schedule(principal, annual_interest, years);
    }

  // Print Mortgage Details
  private static void print_mortgage (int principal, float annual_interest, byte years) {
        double mortgage = calculate_mortgage(principal, annual_interest, years);
        String total = NumberFormat.getCurrencyInstance().format(mortgage);
      System.out.println();
      System.out.println("MORTGAGE");
      System.out.println("---------");
      System.out.print("Monthly Payments: " + total);
  }


  // Print Payment Schedule Details
  private static void print_payment_schedule(int principal, float annual_interest, byte years) {
      System.out.println();
      System.out.println("PAYMENT SCHEDULE");
      System.out.println("----------------");

      for (short month = 1; month <= years * MONTHS_IN_YEAR; month++) {
          double balance = calculate_balance(principal, annual_interest, years, month);
          System.out.println(NumberFormat.getCurrencyInstance().format(balance));
      }

  }


  // Read Number
  public static double read_number (String prompt, double min, double max) {
       Scanner scanner = new Scanner(System.in);

       double value;
       while (true) {
           System.out.print(prompt);
           value = scanner.nextFloat();

           if (value >= min && value <= max) // If true, execution will break out of this loop and function then return the "value".
               break;
           System.out.println("Enter a Value between " + min + " and " + max); // If false ...
       }
       return value;
  }


  // Calculate Balance
  public static double calculate_balance (int principal, float annual_interest, byte years, short number_of_payments_made) {

        float monthly_interest = annual_interest / PERCENT / MONTHS_IN_YEAR;
        short number_of_payments = (short)(years * MONTHS_IN_YEAR);

        double balance = principal
                * (Math.pow(1 + monthly_interest, number_of_payments) - Math.pow(1 + monthly_interest, number_of_payments_made))
                / (Math.pow(1 + monthly_interest, number_of_payments) - 1);

        return balance;
  }


  // Calculate Mortgage
  public static double calculate_mortgage (int principal, float annual_interest, byte years) {

        float monthly_interest = annual_interest / PERCENT / MONTHS_IN_YEAR;
        short number_of_payments = (short)(years * MONTHS_IN_YEAR);

        double mortgage = principal
                * (monthly_interest * Math.pow(1 + monthly_interest, number_of_payments))
                / (Math.pow(1 + monthly_interest, number_of_payments) - 1);

        return  mortgage;
  }
}


