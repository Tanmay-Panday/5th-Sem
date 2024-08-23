import java.util.Arrays;

class Process {
    int PID, AT, BT, St, CT, TAT, WT;

    public Process(int PID, int AT, int BT) {
        this.PID = PID;
        this.AT = AT;
        this.BT = BT;
    }
}

public class Scheduler {

    static void fcfs(Process[] processes) {
        System.out.println("FCFS");
        Arrays.sort(processes, (a, b) -> a.AT - b.AT);
        int currentTime = 0, totalTAT = 0, totalWT = 0, idleTime = 0;

        for (Process p : processes) {
            if (currentTime < p.AT) idleTime += (p.AT - currentTime);
            p.St = currentTime = Math.max(currentTime, p.AT);
            p.CT = currentTime += p.BT;
            p.TAT = p.CT - p.AT;
            p.WT = p.TAT - p.BT;

            totalTAT += p.TAT;
            totalWT += p.WT;
        }

        double avgTAT = (double) totalTAT / processes.length;
        double avgWT = (double) totalWT / processes.length;
        double cpuUtil = 100.0 * (currentTime - idleTime) / currentTime;
        double throughput = (double) processes.length / currentTime;

        System.out.printf("Avg Turnaround Time: %.2f\nAvg Waiting Time: %.2f\nCPU Utilization: %.2f%%\nThroughput: %.2f processes/unit time\n", avgTAT, avgWT, cpuUtil, throughput);
    }

    static void sjfs(Process[] p) {
        System.out.println("SJFS : ");
        int t = 0, completed = 0, n = p.length, idle = 0;
        boolean[] done = new boolean[n];
        double totalTAT = 0, totalWT = 0;
        Arrays.sort(p, (a, b) -> a.AT - b.AT);

        while (completed < n) {
            int idx = -1, minBT = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if (!done[i] && p[i].AT <= t && p[i].BT < minBT) {
                    minBT = p[i].BT;
                    idx = i;
                }
            }
            if (idx == -1) {
                idle++;
                t++;
                continue;
            }

            p[idx].St = t;
            p[idx].CT = t += p[idx].BT;
            p[idx].TAT = p[idx].CT - p[idx].AT;
            p[idx].WT = p[idx].TAT - p[idx].BT;

            totalTAT += p[idx].TAT;
            totalWT += p[idx].WT;
            done[idx] = true;
            completed++;
        }

        System.out.printf("Avg TAT: %.2f\nAvg WT: %.2f\nCPU Util: %.2f%%\nThroughput: %.2f proc/unit time\n",
                totalTAT / n, totalWT / n, 100.0 * (t - idle) / t, (double) n / t);
    }
    static void srtf(Process[] p) {
        int t = 0, completed = 0, n = p.length, idle = 0;
        int[] remainingBT = new int[n];
        boolean[] isCompleted = new boolean[n];
        double totalTAT = 0, totalWT = 0;

        for (int i = 0; i < n; i++) {
            remainingBT[i] = p[i].BT;
        }

        while (completed < n) {
            int idx = -1, minBT = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if (p[i].AT <= t && !isCompleted[i] && remainingBT[i] < minBT) {
                    minBT = remainingBT[i];
                    idx = i;
                }
            }

            if (idx == -1) {
                idle++;
                t++;
                continue;
            }

            if (remainingBT[idx] == p[idx].BT) {
                p[idx].St = t;
            }

            remainingBT[idx]--;
            t++;

            if (remainingBT[idx] == 0) {
                p[idx].CT = t;
                p[idx].TAT = p[idx].CT - p[idx].AT;
                p[idx].WT = p[idx].TAT - p[idx].BT;

                totalTAT += p[idx].TAT;
                totalWT += p[idx].WT;
                isCompleted[idx] = true;
                completed++;
            }
        }

        System.out.printf("Avg TAT: %.2f\nAvg WT: %.2f\nCPU Util: %.2f%%\nThroughput: %.2f proc/unit time\n",
                totalTAT / n, totalWT / n, 100.0 * (t - idle) / t, (double) n / t);
    }

    public static void main(String[] args) {
        Process[] processes = {
            new Process(1, 0, 4),
            new Process(2, 1, 3),
            new Process(3, 2, 1),
            new Process(4, 3, 2),
            new Process(5, 4, 5)
        };

        fcfs(processes);
        sjfs(processes);
        srtf(processes);
    }
}
