import java.io.*;
import java.util.*;

public class FCFS {
    private static class Process {
        int pid;
        double at;
        double bt;

        Process(int pid, double at, double bt) {
            this.at = at;
            this.pid = pid;
            this.bt = bt;
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Number of processes = ");
        int n = sc.nextInt();
        Process[] processes = new Process[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter PID, Arrival Time, and Burst Time for Process " + (i + 1) + ": ");
            int id = sc.nextInt();
            double at = sc.nextDouble();
            double bt = sc.nextDouble();
            processes[i] = new Process(id, at, bt);
        }

        // Sort processes by arrival time
        Arrays.sort(processes, (a, b) -> Double.compare(a.at, b.at));

        double rt = 0, tat = 0, wt = 0;
        double t = 0; // Current time

        for (int i = 0; i < n; i++) {
            if (t < processes[i].at) {
                t = processes[i].at; // If the CPU is idle, jump to the arrival time of the next process
            }

            System.out.println("Process " + processes[i].pid);

            double responseTime = t - processes[i].at;
            System.out.println("Response Time = " + responseTime);
            rt += responseTime;

            double waitingTime = t - processes[i].at;
            System.out.println("Waiting Time = " + waitingTime);
            wt += waitingTime;

            t += processes[i].bt; // Time after executing this process

            double turnaroundTime = t - processes[i].at;
            System.out.println("Turnaround Time = " + turnaroundTime);
            tat += turnaroundTime;
        }

        System.out.println("Average Turnaround Time = " + tat / n);
        System.out.println("Average Waiting Time = " + wt / n);
        System.out.println("Average Response Time = " + rt / n);
    }
}
