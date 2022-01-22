package cse321.codes;
//Priority Scheduling
import java.util.*;

public class lab4_T2
{

    static int n, bt[], rt[], at[], et[], wt[], tt[], pr[];

    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the number of Processes: ");
        n = sc.nextInt();
        bt = new int[n];
        at = new int[n];
        et = new int[n]; //end time
        wt = new int[n];
        tt = new int[n];
        rt = new int[n]; //remaining time
        pr = new int[n]; //priority
        for (int i = 0; i < n; i++)
        {
            System.out.println("Arrival Time for Process " + (i + 1) + ": ");
            at[i] = sc.nextInt();
            System.out.println("Burst Time for Process " + (i + 1) + ": ");
            bt[i] = sc.nextInt();
            System.out.println("Priority for Process " + (i + 1) + ": ");
            pr[i] = sc.nextInt();
        }

        System.arraycopy(bt, 0, rt, 0, n);
        compute(); // calculation 

        for (int i = 0; i < n; i++) //waiting time and trunraround time
        {
            tt[i] = et[i] - at[i];
            wt[i] = tt[i] - bt[i];
        }
        System.out.println("P\t WT \t TT \t CT");
        for (int i = 0; i < n; i++)
        {
            System.out.printf("%d\t%2dms\t%2dms\t%2dms", (i + 1), wt[i], tt[i], et[i]);
            System.out.println();
        }

        System.out.println();

        double wtAvg = 0, ttAvg = 0;
        for (int i = 0; i < n; i++)
        {
            wtAvg = wtAvg + wt[i];
            ttAvg = ttAvg + tt[i];
        }
        wtAvg = wtAvg / n;
        ttAvg = ttAvg / n;
        System.out.printf("Average Turnaround Time: %.1f\n", ttAvg);
        System.out.printf("Average Waiting Time: %.1f\n", wtAvg);

    } // main method ends

    public static void compute()
    {
        int cpuTime = 0;
        while (notZero() == true)
        {

            ArrayList<Integer> arrList = new ArrayList<>();
            for (int i = 0; i < n; i++)
            {
                //check which processes arrived within the cpu time and has remaing time > 0
                //add them in the list
                if (rt[i] > 0 && at[i] <= cpuTime)
                {
                    arrList.add(i);
                }
            }

            int nextP = 0;
            // will compare it's time with other processes remaining time in the list
           
            boolean found = false;
            if (!arrList.isEmpty())
            {
                found = true;
                nextP = arrList.get(0);
            }
            for (int i = 1; i < arrList.size(); i++)
            {
                // if i-th process has same
                int x = arrList.get(i);
                if (pr[x] == pr[nextP])
                {
                    if (at[x] < at[nextP])
                    {
                        nextP = x;
                    }
                } 
                // if the i-th process has less remaining time, will select that
                else if (pr[x] < pr[nextP])
                {
                    nextP = x;
                }
            }
            if (found)
            {
                rt[nextP]--;
                if (rt[nextP] == 0)
                {
                    et[nextP] = cpuTime + 1;
                }
            }
            cpuTime++;
        }//while loop ends
    }

    public static boolean notZero()
    {
        for (int value : rt)
        {
            if (value > 0)
            {
                return true;
            }
        }
        return false;
    }
} // class ends
