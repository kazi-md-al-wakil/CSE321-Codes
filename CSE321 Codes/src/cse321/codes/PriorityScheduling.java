package cse321.codes;
import java.util.*;

public class PriorityScheduling {

    public static Scanner sc = new Scanner(System.in);
    static int priority[], end_time[], burst_time[], arrival_time[], waiting_time[], remaining_time[],
            turnaround_time[], total_number_of_process;

    public static void main(String[] args) {
        System.out.println("Enter total number of processes:");
        total_number_of_process = sc.nextInt();//taking input for total number of processes
        burst_time = new int[total_number_of_process];
        arrival_time = new int[total_number_of_process];
        end_time = new int[total_number_of_process];
        waiting_time = new int[total_number_of_process];
        turnaround_time = new int[total_number_of_process];
        remaining_time = new int[total_number_of_process];
        priority = new int[total_number_of_process];

        take_input_of_arrival_time();

        take_input_of_burst_time();

        take_input_of_priority();

        System.arraycopy(burst_time, 0, remaining_time, 0, total_number_of_process);

        compute();

        computer_turnaround_time_and_waiting_time();

        print_completion_time_turnaround_time_waiting_time();

        print_average_turnaround_time_and_average_waiting_time();
    }

    static void take_input_of_arrival_time() {
        for (int i = 0; i < total_number_of_process; i++) {
            //taking input of arrival time
            System.out.println("Enter the arrival time of process p" + (i + 1) + ":");
            arrival_time[i] = sc.nextInt();
        }
    }

    static void take_input_of_burst_time() {
        for (int i = 0; i < total_number_of_process; i++) {
            //taking input of burst of time
            System.out.println("Enter the burst time of process p" + (i + 1) + ":");
            burst_time[i] = sc.nextInt();
        }
    }

    static void take_input_of_priority() {
        for (int i = 0; i < total_number_of_process; i++) {
            //taking input of priority
            System.out.println("Enter the priority of process p" + (i + 1) + ":");
            priority[i] = sc.nextInt();
        }
    }

    static void compute() {
        int cpu_time = 0;
        while (remaining_time_is_not_zero()== true) {//while loop starts

            ArrayList<Integer> alist = new ArrayList<>();
            for (int i = 0; i < total_number_of_process; i++) {
                //check which processes arrived within the cpu time and has remaing time > 0
                //add them in the list
                if (remaining_time[i] > 0 && arrival_time[i] <= cpu_time) {
                    alist.add(i);
                }
            }

            int next_process_to_execute = 0;
            // will compare it's time with other processes remaining time with the other processes in
            // the list
            boolean found = false;
            if (!alist.isEmpty()) {
                found = true;
                next_process_to_execute = alist.get(0);
            }
            for (int i = 1; i < alist.size(); i++) {
                // if i-th process has same
                int x = alist.get(i);
                if (priority[x] == priority[next_process_to_execute]) {
                    if (arrival_time[x] < arrival_time[next_process_to_execute]) {
                        next_process_to_execute = x;
                    }
                } // if the i-th process has less remaining time, will select that
                else if (priority[x] < priority[next_process_to_execute]) {
                    next_process_to_execute = x;
                }
            }
            if (found) {
                remaining_time[next_process_to_execute]--;
                if (remaining_time[next_process_to_execute] == 0) {
                    end_time[next_process_to_execute] = cpu_time + 1;
                }
            }
            cpu_time++;
        }//while loop ends
    }

    static boolean remaining_time_is_not_zero() {
        for (int value : remaining_time) {
            if (value > 0) {
                return true;
            }
        }
        return false;
    }

    static void computer_turnaround_time_and_waiting_time() {
        for (int i = 0; i < total_number_of_process; i++) {
            turnaround_time[i] = end_time[i] - arrival_time[i];
            waiting_time[i] = turnaround_time[i] - burst_time[i];
        }
    }

    static void print_completion_time_turnaround_time_waiting_time() {
        System.out.println("\n==============================================================");
        System.out.println("Process|   Completion Time|   Turnaround Time|   Waiting Time|");
        System.out.println("--------------------------------------------------------------");
        for (int i = 0; i < total_number_of_process; i++) {
            System.out.print("  P" + (i + 1) + "   |");
            print_space(end_time[i]);
            System.out.print(end_time[i] + "        |");
            print_space(turnaround_time[i]);
            System.out.print(turnaround_time[i] + "        |");
            print_space(waiting_time[i]);
            System.out.println(waiting_time[i] + "     |");
            if (i < total_number_of_process - 1) {
                System.out.println("--------------------------------------------------------------");
            }
        }
        System.out.println("==============================================================");
    }

    static void print_space(int time) {
        int temp = time;
        String s = "" + temp;
        int count = 0;
        while (temp > 0) {
            System.out.print(" ");
            temp /= 10;
            count++;
        }
        for (int j = 0; j < 10 - count - s.length(); j++) {
            System.out.print(" ");
        }
    }

    static void print_average_turnaround_time_and_average_waiting_time() {
        double wt = 0, tt = 0;
        for (int i = 0; i < total_number_of_process; i++) {
            wt += waiting_time[i];
            tt += turnaround_time[i];
        }
        wt /= total_number_of_process;
        tt /= total_number_of_process;
        System.out.printf("Average Turnaround Time: %.2f\n", tt);
        System.out.printf("Average Waiting Time: %.2f\n", wt);
    }
}
//0 1 3 4 5 6 10 8 2 4 1 6 5 1 3 4 4 5 2 6 1
