/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hw230315084;

import java.util.Scanner;

/**
 *
 * @author ozgeucar
 */
public class HW230315084 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Özge Uçar 230315084

        Scanner scan = new Scanner(System.in);//i called scanner class to reach  users enteries
        System.out.print("Enter custemers number: ");
        int numOfCustomers = scan.nextInt();// ask user how many customers that we have

        String[][] customer = createCustomerArray(numOfCustomers, scan);//create an array which crates 2 dimensional array including 
        //customers information
        displayCustomersArray(customer);//i called method which create myself to display customers info

        int[] customerState = createCustomerJobState(scan, numOfCustomers);//create an array whic hold the customers job status
        displayJobStatus(customerState);//called method which i create for displaying job status

        long[] balance = customerBalance(customer.length);//create a new arrray which holding customers money
        displayBalance(balance);//i called method which create  for display customers money
        double averageBalance = calculateAverageBalance(balance);//i have an method that calculates every users average balance
        System.out.printf("Average Balance: %.2f%n", averageBalance);

        long[] updatedBalances = updatedBalance(balance, customerState, averageBalance);//define an array whic holding updated money after average balance calculated
        displayUpdatedBalance(updatedBalances);//display updated money whic bank inserted or deducted
        displayGenderCount(customer);//its a void method which has properties counting genders

    }

    public static char isGender(String gender) {//create a method with returning char value
        Scanner scan = new Scanner(System.in);//called scanner class to reach users enter
        gender = gender.toUpperCase();//making upper case to entery

        while (!gender.equals("F") && !gender.equals("M")) {//until this condition happens it s a loop
            System.out.print("Please enter your gender as F or M : ");//it wants gender info again
            gender = scan.nextLine().toUpperCase();//catch it next line

        }
        return gender.charAt(0);//it returning genders first char

    }

    public static String splitName(String name) {// write an method which splitted string because our app wants just one name
        String[] splittedName = name.split(" ");//split it to reach first gap
        return splittedName[0];
    }

    public static String[][] createCustomerArray(int numOfCustomers, Scanner scan) {

        String[][] customer = new String[numOfCustomers][2];//create new array with 2 dimensional rows are increasing with
        //customers count
        for (int i = 0; i < numOfCustomers; i++) {//until i=numOfCustomers these block will work
            System.out.print("Please enter customer's name: ");
            String name = scan.nextLine();
            scan.nextLine();//i added this line later because it makes a mistake on output it cleans for next code
            String firstName = splitName(name);
            System.out.print("Please enter customer's last name: ");
            String lastNAme = scan.nextLine();
    
            System.out.print("What is customer's gender? F(female) or M(male): ");

            char gender = isGender(scan.nextLine());
//i take customer information from user and chack name and gender 
            customer[i][0] = firstName + " " + lastNAme;
            customer[i][1] = String.valueOf(gender);
            //i decided every collumn keep what

        }
        return customer;//return customer array to use again

    }

    public static void displayCustomersArray(String[][] customer) {//this method shows customers info
        System.out.println("Name Surname \tGender ");
        for (String[] customers : customer) {
            System.out.printf("%s\t%s\n", customers[0], customers[1]);
        }
    }

    public static int[] createCustomerJobState(Scanner scan, int numOfCustomers) {//this method returns customers job staatus with an array
        int[] jobState = new int[numOfCustomers];//define an array 
        for (int i = 0; i < numOfCustomers; i++) {//until i=numOfCustomers these block will work
            while (true) {//this loop provide to valid entery from user
                System.out.println("Please enter Customer's job status as :");
                System.out.println("1: Student \n2: REtired \n3: Employed \n4: Unempoleyed");
                int state = scan.nextInt();
                if (state != 1 && state != 2 && state != 3 && state != 4) {//check the state  is it valid
                    System.out.println("Please enter the Customer's job status correct..");
                    return createCustomerJobState(scan, numOfCustomers);
                } else {
                    jobState[i] = state;//if its valid enter this and assign this value in array
                    break;
                }
            }
        }
        return jobState;//return this array
    }

    public static void displayJobStatus(int[] customerState) {//this method provide to show costumers job status
        for (int i = 0; i < customerState.length; i++) {
            String status = "";
            switch (customerState[i]) {
                case 1:
                    status = "Student";
                    break;
                case 2:
                    status = "Retired";
                    break;
                case 3:
                    status = "Empoleyed";
                    break;
                case 4:
                    status = "Unemployed";
                    break;

            }
            System.out.printf("%12d | %s%n", (i + 1), status);
        }
    }

    public static long[] customerBalance(int numberOfCustomers) {//create customers balance array
        long[] balance = new long[numberOfCustomers];
        for (int i = 0; i < numberOfCustomers; i++) {
            balance[i] = (long) ((Math.random() * (100000 - 10000)) + 10000);//i did it with random number 10000 to 100000
        }
        return balance;//return this array
    }

    public static void displayBalance(long[] balance) {//this method shows customers balance
        System.out.println("\nInitial Balance: ");
        for (int i = 0; i < balance.length; i++) {
            System.out.printf("Customer %d : %d%n", (i + 1), balance[i]);
        }
    }

    public static double calculateAverageBalance(long[] balance) {
        long total = 0;
        for (long b : balance) {//it handle every index of array to sum 
            total += b;
        }
        return (double) (total / (balance.length));
//this method calculate average balance 
    }

    public static long[] updatedBalance(long[] balance, int[] customerState, double calculateAverageBalance) {
        long[] updatedBalance = new long[balance.length];
        for (int i = 0; i < balance.length; i++) {
            updatedBalance[i] = balance[i];
            if (balance[i] > calculateAverageBalance) {
                updatedBalance[i] += (long) (balance[i] * 0.02);
            } else if (customerState[i] != 1 && customerState[i] != 2) {
                updatedBalance[i] -= (long) (balance[i] * 0.01);
            }

        }
        return updatedBalance;
        //this method update customers balance according to the average
    }

    public static void displayUpdatedBalance(long[] updatedBalance) {
        System.out.println("\nUpdated Balance: ");
        for (int i = 0; i < updatedBalance.length; i++) {
            System.out.printf("Customer %d : %d%n", (i + 1), updatedBalance[i]);
        }
        //this method display customers updated balance
    }

    public static void displayGenderCount(String[][] customer) {
        int countMale = 0;//i assign 2 new parameter for counting how many customers is female and male
        int countFemale = 0;
        for (String[] numGender : customer) {//this loop continue until all customers counting
            if (numGender.length > 1) { //check customer has info more than one 
                String gender = numGender[1].toUpperCase(); //make gender info upper case
                if (gender.equals("M")) { //if it equal M add 1 to count male
                    countMale++;
                } else if (gender.equals("F")) { //otherhand add 1 to counting female
                    countFemale++;
                }
            }
        }
        System.out.printf("\nGender Count: Male: %d , Female: %d%n", countMale, countFemale);
    }

}
