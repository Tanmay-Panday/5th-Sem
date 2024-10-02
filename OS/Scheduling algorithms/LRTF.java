import java.io.*;
import java.util.*;

public class LRTF {
    private static class Process {
        int pid;
        double at;
        double bt;
        double remainingBt;

        Process(int pid, double at, double bt) {
            this.pid = pid;
            this.at = at;
            this.bt = bt;
            this.remainingBt = bt;
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
        Arrays.sort(processes, Comparator.comparingDouble(p -> p.at));

        double t = 0; // Current time
        double rt = 0, tat = 0, wt = 0; // Total response time, turnaround time, and waiting time
        int completed = 0;
        boolean[] isResponseCalculated = new boolean[n];

        // Change priority queue comparator to sort by longest remaining burst time
        PriorityQueue<Process> pq = new PriorityQueue<>((p1, p2) -> Double.compare(p2.remainingBt, p1.remainingBt));

        while (completed < n) {
            // Add all processes that have arrived by time `t` to the priority queue
            for (int i = 0; i < n; i++) {
                if (processes[i].at <= t && processes[i].remainingBt > 0 && !pq.contains(processes[i])) {
                    pq.add(processes[i]);
                }
            }

            if (!pq.isEmpty()) {
                Process currentProcess = pq.poll();

                // If the response time for the current process has not been calculated yet
                if (!isResponseCalculated[currentProcess.pid - 1]) {
                    rt += t - currentProcess.at;
                    isResponseCalculated[currentProcess.pid - 1] = true;
                }

                // Execute the process for 1 unit of time
                currentProcess.remainingBt--;
                t++;

                // If the process is completed
                if (currentProcess.remainingBt == 0) {
                    completed++;
                    double turnaroundTime = t - currentProcess.at;
                    tat += turnaroundTime;

                    double waitingTime = turnaroundTime - currentProcess.bt;
                    wt += waitingTime;

                    System.out.println("Process " + currentProcess.pid);
                    System.out.println("Response Time = " + (t - turnaroundTime - currentProcess.at));
                    System.out.println("Waiting Time = " + waitingTime);
                    System.out.println("Turnaround Time = " + turnaroundTime);
                } else {
                    // If the process is not finished, put it back into the priority queue
                    pq.add(currentProcess);
                }
            } else {
                // If no processes are ready to execute, move time forward to the next arrival
                t = processes[completed].at;
            }
        }

        // Print average times
        System.out.println("Average Turnaround Time = " + tat / n);
        System.out.println("Average Waiting Time = " + wt / n);
        System.out.println("Average Response Time = " + rt / n);
    }
}
