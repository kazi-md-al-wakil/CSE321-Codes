package cse321.codes;
//SRTF

import java.io.*;

public class lab4_T1
{

    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n;
        System.out.println("Please enter the number of Processes: ");
        n = Integer.parseInt(br.readLine());
        int process[][] = new int[n + 1][4];
        for (int i = 1; i <= n; i++)
        {
            System.out.println("Arrival Time for Process " + i + ": ");
            process[i][0] = Integer.parseInt(br.readLine());
            System.out.println("Burst Time for Process " + i + ": ");
            process[i][1] = Integer.parseInt(br.readLine());
        }
        System.out.println();

        //Calculation of Total Time and Initialization of Time Chart array
        int totalTime = 0;
        for (int i = 1; i <= n; i++)
        {
            totalTime = totalTime + process[i][1];
        }
        
  
        //int timeChart[] = new int[totalTime];
        //System.out.println("Gantt Chart: ");

        for (int i = 0; i < totalTime; i++) /*This loop is tracking the total time, and want to know if 
                                            in a particular time a process has arrived or not.*/
        {

            //Selection of shortest process which has arrived
            int selectProcess = 0;
            int min = 99999;
            for (int j = 1; j <= n; j++)/*This loop is tracking 
                                            if on a certain time a process has been arrived or not
                                                if a process has arrived, 
                                                    does the process has low Burst time than the currently running process. */
            {
                if (process[j][0] <= i)//Condition to check if Process has arrived
                {
                    if (process[j][1] < min && process[j][1] != 0)/*Checking if the arrived process has low B.T than 
                                                                    the currently running process*/

                    {
                        min = process[j][1];
                        selectProcess = j;
                    }
                }
            }
            //timeChart[i] = selectProcess;

            process[selectProcess][1]--;//decreasing B.T time

            //WT and TT Calculation
            for (int j = 1; j <= n; j++)
            {
                if (process[j][0] <= i)
                {
                    if (process[j][1] != 0)
                    {
                        process[j][3]++;
                        if (j != selectProcess)
                        {
                            process[j][2]++;
                        }
                    } 
                    else if (j == selectProcess)//This is a special case in which the process has been assigned CPU and has completed its execution
                    {
                        process[j][3]++;
                    }
                }
            }
            /*
            if (i != 0)
            {
                if (selectProcess != timeChart[i - 1])
                {
                    System.out.print("--"+i + "--P" + selectProcess);
                }
            } 
            else
            {
                System.out.print(i +"--P" + selectProcess);
            }
            if (i == totalTime - 1)
            {
                System.out.print("--" + (i + 1));
            }
            */
            
        }
        System.out.println();
        System.out.println();

        //Printing the WT and TT for each Process
        System.out.println("P\t WT \t TT \t CT");
        for (int i = 1; i <= n; i++)
        {
            System.out.printf("%d\t%2dms\t%2dms\t%2dms", i, process[i][2], process[i][3], (process[i][3]+process[i][0]));
            System.out.println();
        }

        System.out.println();

        //Printing the average WT & TT
        float WT = 0, TT = 0;
        for (int i = 1; i <= n; i++)
        {
            WT += process[i][2];
            TT += process[i][3];
        }
        WT /= n;
        TT /= n;
        System.out.println("The Average WT is: " + WT + "ms");
        System.out.println("The Average TT is: " + TT + "ms");
    }

}
