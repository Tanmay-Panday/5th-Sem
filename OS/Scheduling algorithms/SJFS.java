import java.io.*;
import java.util.*;

public class SJFS {
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

        // Sorting processes by Arrival Time
        Arrays.sort(processes, Comparator.comparingDouble(p -> p.at));

        double t = 0; // Current time
        double rt = 0, tat = 0, wt = 0; // Total response time, turnaround time, and waiting time

        PriorityQueue<Process> pq = new PriorityQueue<>((p1, p2) -> Double.compare(p1.bt, p2.bt));
        int index = 0;

        while (index < n || !pq.isEmpty()) {
            // Add all processes that have arrived by time `t` to the priority queue
            while (index < n && processes[index].at <= t) {
                pq.add(processes[index]);
                index++;
            }

            if (!pq.isEmpty()) {
                Process currentProcess = pq.poll();

                // Response Time: first time the process is scheduled
                double responseTime = t - currentProcess.at;
                rt += responseTime;

                // Update current time to when the process finishes
                t += currentProcess.bt;

                // Turnaround Time: total time from arrival to completion
                double turnaroundTime = t - currentProcess.at;
                tat += turnaroundTime;

                // Waiting Time: turnaround time minus burst time
                double waitingTime = turnaroundTime - currentProcess.bt;
                wt += waitingTime;

                // Print the results for the current process
                System.out.println("Process " + currentProcess.pid);
                System.out.println("Response Time = " + responseTime);
                System.out.println("Waiting Time = " + waitingTime);
                System.out.println("Turnaround Time = " + turnaroundTime);
            } else {
                // If the priority queue is empty, move the time to the next arriving process
                if (index < n) {
                    t = processes[index].at;
                }
            }
        }

        // Print average times
        System.out.println("Average Turnaround Time = " + tat / n);
        System.out.println("Average Waiting Time = " + wt / n);
        System.out.println("Average Response Time = " + rt / n);
    }
}
