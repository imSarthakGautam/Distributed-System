import java.io.*;
import java.util.Scanner;

public class Bully {
    static int n;
    static int[] pro = new int[100]; //priority of each process
    static int[] sta = new int[100]; //status of process: alive or dead
    static int co;

    public static void main(String args[]) throws IOException {
        System.out.println("Enter the number of processes:");
        Scanner in = new Scanner(System.in);
        n = in.nextInt();

        // Input status and priority for each process
        for (int i = 0; i < n; i++) {
            System.out.println("For process " + (i + 1) + ":");
            System.out.println("Status:");
            sta[i] = in.nextInt();
            System.out.println("Priority:");
            pro[i] = in.nextInt();
        }

        // Which process will initiate election?
        System.out.println("Which process will initiate election (enter process number)?");
        int ele = in.nextInt();

        // Perform election
        elect(ele);

        // Print final coordinator
        System.out.println("Final coordinator is " + co);
    }

    static void elect(int ele) {
        ele = ele - 1; // Adjusting for 0-based indexing
        int highestPriorityProcess = ele;

        for (int i = ele + 1; i < n; i++) {
            if (sta[i] == 1 && pro[i] > pro[highestPriorityProcess]) {
                highestPriorityProcess = i;
            }
        }

        co = highestPriorityProcess + 1; // +1 to convert index to process number

        System.out.println("Process " + co + " becomes the coordinator.");
    }
}
