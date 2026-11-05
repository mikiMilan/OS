package vjezbe2;

import java.util.*;

class Process{
    private static int counter = 0;

    public int id;
    public float burstTime;
    public float remainingTime;
    public int priority;

    public Process(int id, float burstTime){
        this.id = id;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
    }

    public Process(int id, float burstTime, int priority){
        this.id = id;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Proces " + this.id + " remaining time: " + this.remainingTime + " prioritet: " + this.priority;
    }
}

public class Scheduling {

    public static ArrayList<Process> processes =
            new ArrayList<Process>(Arrays.asList(
                    new Process(1, 10, 1),
                    new Process(2, 5, 2),
                    new Process(3, 8, 1)
            ));

    public void JSFScheduling(){
        Queue<Process> queue = new PriorityQueue<Process>(
          (p1,p2)->{
              return Float.compare(p1.burstTime, p2.burstTime);
          }
        );

        for (Process p : processes)
            queue.add(p);

        while (!queue.isEmpty()){
            Process current = queue.poll();
            System.out.println("Trenutno izvrsavamo proces: " + current);
            try {
                Thread.sleep((long)current.burstTime*1000);
            }catch (Exception e){
                System.out.println("Greska: " + e.getMessage());
            }
        }
    }

    public void RoundRobinScheduling(float quantum){
        Queue<Process> queue = new LinkedList<Process>();

        for (Process p : processes)
            queue.add(p);

        while (!queue.isEmpty()){
            Process current = queue.poll();

            float runingtime = Math.min(quantum, current.remainingTime);

            System.out.println("Trenutno izvrsavamo proces: " + current);
            try {
                Thread.sleep((long)runingtime*1000);
            }catch (Exception e){
                System.out.println("Greska: " + e.getMessage());
            }

            current.remainingTime -= runingtime;

            if(current.remainingTime>0)
                queue.add(current);
        }
    }

    public void PriorityScheduling(){
        Queue<Process> queue = new PriorityQueue<Process>(
                (p1,p2)->{
                    return Float.compare(p1.priority, p2.priority);
                }
        );

        for (Process p : processes)
            queue.add(p);

        while (!queue.isEmpty()){
            Process current = queue.poll();
            System.out.println("Trenutno izvrsavamo proces: " + current);
            try {
                Thread.sleep((long)current.burstTime*1000);
            }catch (Exception e){
                System.out.println("Greska: " + e.getMessage());
            }
        }
    }
}
