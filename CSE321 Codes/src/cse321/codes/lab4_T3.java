package cse321.codes;
//Round Robin
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class lab4_T3
{
    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the Time Quantum: ");
        int q = Integer.parseInt(br.readLine());
        System.out.println("Please enter the number of Processes: ");
        int n = Integer.parseInt(br.readLine());
        int process[][] = new int[n + 1][4];
        for (int i = 1; i <= n; i++)
        {
            System.out.println("Arrival Time for Process " + i + ": ");
            process[i][0] = Integer.parseInt(br.readLine());
            System.out.println("Burst Time for Process " + i + ": ");
            process[i][1] = Integer.parseInt(br.readLine());
        }
        System.out.println();

        int totalTime = 0;
        for (int i = 1; i <= n; i++)
        {
            totalTime = totalTime + process[i][1];
        }

        //int timeChart[] = new int[totalTime];
        //System.out.println("Gantt chart: ");

        int selectProcess = 1;
        int currentQ = 0;
        
        for (int i = 0; i < totalTime; i++)
        {
            
            //timeChart[i] = selectProcess;

            
            process[selectProcess][1]--;

            //WT and TT Calculation
            for (int j = 1; j <= n; j++)
            {
                if (process[j][1] != 0)
                {
                    process[j][3]++;
                    if (j != selectProcess)
                    {
                        process[j][2]++;
                        
                    }
                } 
                else if (j == selectProcess)
                {
                    process[j][3]++;
                }
            }

            
/*
            //Printing the Time Chart

            if (i != 0)
            {
                if (selectProcess != timeChart[i - 1])
                
                {
                    System.out.print("--" + i + "--P" + selectProcess);
                }
            } 
            else
            {
                System.out.print(i + "--P" + selectProcess);
            }
            if (i == totalTime - 1)
            {
                System.out.print("--" + (i + 1));
            }
            
*/
            //Updating value of selectProcess for next iteration
            currentQ++;
            if (currentQ == q || process[selectProcess][1] == 0)
            {
                currentQ = 0;
   
                for (int j = 1; j <= n; j++)
                {
                    selectProcess++;
                    if (selectProcess == (n + 1)) //if it is a last process then we will go to the first process
                    {
                        selectProcess = 1;
                    }
                    if (process[selectProcess][1] != 0)
                    {
                        break;
                    }
                }
            }
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
