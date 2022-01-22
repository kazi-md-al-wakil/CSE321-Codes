package cse321.codes;

import java.io.*;
import java.util.*;

class lab6_BankerAlgo {

    static int row, column;
    static int safeSet[] = new int[row + 10];

    public static boolean safeSequence(int allocation[][], int available[], int need[][],
            int row, int column, int availableChange[][]) {

        boolean safeChecking[] = new boolean[row];

        for (int i = 0; i < row; i++) {
            safeChecking[i] = false;
        }

        int counter1 = 0;
        int counter2 = 0;
        while (counter1 < row && counter2 < row) {
            for (int i = 0; i < row; i++) {
                boolean flag = true;
                if (safeChecking[i] == false) {
                    for (int j = 0; j < column; j++) {
                        if (available[j] < need[i][j]) {
                            flag = false;
                        }
                    }
                    if (flag == true) {
                        for (int j = 0; j < column; j++) {
                            available[j] = available[j] + allocation[i][j];
                            availableChange[counter1][j] = available[j];

                        }
                        safeSet[counter1] = i;
                        counter1++;
                        safeChecking[i] = true;
                    }
                }
            }
            counter2++;
        }
        if (counter1 > row) {
            return false;
        } else {
            return true;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader b = new BufferedReader(new FileReader("C:\\Users\\Wakil\\OneDrive\\Documents\\Codes\\CSE321 Codes\\src\\cse321\\codes\\lab6_input_bankers.txt")); //to read input from file

        row = Integer.parseInt(b.readLine()); //For the matrix
        column = Integer.parseInt(b.readLine()); //For the matrix

        //Initializing 2D arrays
        int[][] max = new int[row][column];
        int[][] allocation = new int[row][column];
        int[][] need = new int[row][column];
        int available[] = new int[column];
        int[][] availableChange = new int[row][column];
        //End of Initializing 2D arrays

        //Populating Max Matrix
        for (int i = 0; i < row; i++) {
            String s = b.readLine();
            StringTokenizer st = new StringTokenizer(s, " ");
            for (int j = 0; j < column; j++) {
                max[i][j] = Integer.parseInt(st.nextToken());

            }
        }
        //End of Populating Max Matrix

        //Populating allocation Matrix And at the same time Populating Need Matrix
        for (int i = 0; i < row; i++) {
            String s = b.readLine();
            StringTokenizer st = new StringTokenizer(s, " ");
            for (int j = 0; j < column; j++) {
                allocation[i][j] = Integer.parseInt(st.nextToken());
                need[i][j] = max[i][j] - allocation[i][j];
            }
        }
        //End of Populating allocation Matrix & Need Matrix

        //Printing need matrix
        System.out.println("Need Matrix: ");
        for (int i = 0; i < row; i++) {
            System.out.println("");
            for (int j = 0; j < column; j++) {
                System.out.print(need[i][j] + " ");
            }
        }
        //End of printing need matrix

        //Populating Available matrix---------------------------
        System.out.println("");
        String s = b.readLine();
        StringTokenizer st = new StringTokenizer(s, " ");

        for (int i = 0; i < column; i++) {
            available[i] = Integer.parseInt(st.nextToken());
        }
        //End Populating Available matrix---------------------------

        // Finding out the safe sequence
        boolean counter1 = safeSequence(allocation, available, need,
                row, column, availableChange);
        if (counter1) {
            //Printing safe sequence
            System.out.println("");
            System.out.println("Safe Sequence: ");
            for (int i = 0; i < row; i++) {
                System.out.print((char) ('A' + safeSet[i]) + " ");
            }
            //End of Printing safe sequence

            //Printing ChangeInAvailable
            System.out.println("\n");
            System.out.println("Change in availabale resource matrix : ");
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    System.out.print(availableChange[i][j] + " ");
                }
                System.out.println();
            }
            //End of Printing availableChange
            System.out.println("");

        } 
        else {
            System.out.println("Unsafe");
        }
        

    }
}
