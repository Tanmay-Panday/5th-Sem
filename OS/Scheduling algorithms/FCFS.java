import java.io.*;
import java.util.*;
public class FCFS{
    private static class Process{
        int pid;
        double at;
        double bt;
        Process(int pid,double at,double bt){
            this.at=at;
            this.pid=pid;
            this.bt=bt;
        }
    }
    public static void main(String[] args) throws IOException{
        Scanner sc=new Scanner(System.in);
        System.out.println("number of processes = ");
        int n=sc.nextInt();
        Process[] processes=new Process[n];
        for(int i = 0; i < n; i++){
            int id=sc.nextInt();
            double at=sc.nextDouble();
            double bt=sc.nextDouble();
            processes[i] = new Process(id,at, bt);
        }
        Arrays.sort(processes , (a,b) -> {
            return (int)(a.at-b.at);
        });
        // sorted by arrival time
        double rt=0,tat=0,wt=0;
        double t=processes[0].at;
        for(int i = 0; i < n; i++){
            System.out.println(processes[i].pid);
            System.out.print("response time = ");
            System.out.println(t-processes[i].at);
            rt+=t-processes[i].at;

            System.out.print("waiting time = ");
            System.out.println(t-processes[i].at);
            wt+=t-processes[i].at;

            t+=processes[i].bt;

            System.out.print("turnaround time = ");
            System.out.println(t-processes[i].at);
            tat+=(t-processes[i].at);
        }
        System.out.println("avg tat = "+tat/n);
        System.out.println("avg wt = "+wt/n);
        System.out.println("average rt = "+rt/n);
    }
}