import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BankersAlgorithmInteractive {

    private int[][] max;          // Maximum demand of each process
    private int[][] allocation;   // Resources allocated to each process
    private int[] available;      // Available resources
    private int[][] need;         // Remaining resource need of each process
    private int numProcesses;     // Number of processes
    private int numResources;     // Number of resource types

    private Scanner scanner;

    public BankersAlgorithmInteractive() {
        scanner = new Scanner(System.in);
    }

    public void initialize() {
        // Input number of processes and resource types
        System.out.print("Enter number of processes: ");
        numProcesses = scanner.nextInt();

        System.out.print("Enter number of resource types: ");
        numResources = scanner.nextInt();

        // Initialize arrays
        max = new int[numProcesses][numResources];
        allocation = new int[numProcesses][numResources];
        available = new int[numResources];
        need = new int[numProcesses][numResources];

        // Input maximum resource needs for each process
        System.out.println("Enter maximum resource needs for each process:");
        for (int i = 0; i < numProcesses; i++) {
            System.out.print("Process " + i + ": ");
            for (int j = 0; j < numResources; j++) {
                max[i][j] = scanner.nextInt();
            }
        }

        // Input current allocations for each process
        System.out.println("Enter current allocations for each process:");
        for (int i = 0; i < numProcesses; i++) {
            System.out.print("Process " + i + ": ");
            for (int j = 0; j < numResources; j++) {
                allocation[i][j] = scanner.nextInt();
            }
        }

        // Input available resources
        System.out.println("Enter available resources:");
        for (int j = 0; j < numResources; j++) {
            available[j] = scanner.nextInt();
        }

        // Calculate need matrix
        calculateNeed();
    }

    private void calculateNeed() {
        // Calculate need = max - allocation
        for (int i = 0; i < numProcesses; i++) {
            for (int j = 0; j < numResources; j++) {
                need[i][j] = max[i][j] - allocation[i][j];
            }
        }
    }

    public ArrayList<Integer> isSafeState() {
        int[] work = Arrays.copyOf(available, available.length);  // Available resources copy
        boolean[] finish = new boolean[numProcesses];  // Finish array

        // Initialize finish array
        Arrays.fill(finish, false);

        // Safe sequence to store the result
        ArrayList<Integer> safeSequence = new ArrayList<>();

        // Check if the system is in a safe state
        int count = 0;
        while (count < numProcesses) {
            boolean found = false;
            for (int i = 0; i < numProcesses; i++) {
                if (!finish[i] && canAllocate(work, need[i])) {
                    // Process can be allocated resources
                    allocateResources(work, allocation[i]);
                    finish[i] = true;
                    safeSequence.add(i);  // Add process to safe sequence
                    found = true;
                    count++;
                }
            }
            if (!found) {
                // If no process found that can be allocated resources, system is not in a safe state
                return null;
            }
        }
        return safeSequence;  // Return safe sequence if found
    }

    public boolean requestResources(int process, int[] request) {
        // Check if request is within need and available limits
        for (int i = 0; i < numResources; i++) {
            if (request[i] > need[process][i] || request[i] > available[i]) {
                return false;  // Request exceeds need or available resources
            }
        }

        // Pretend to allocate resources
        for (int i = 0; i < numResources; i++) {
            available[i] -= request[i];
            allocation[process][i] += request[i];
            need[process][i] -= request[i];
        }

        // Check if system is still in a safe state
        ArrayList<Integer> safeSequence = isSafeState();
        if (safeSequence != null && !safeSequence.isEmpty()) {
            // System is in a safe state, show the safe sequence
            System.out.print("Safe sequence: ");
            for (int i = 0; i < safeSequence.size(); i++) {
                System.out.print("P" + safeSequence.get(i));
                if (i != safeSequence.size() - 1) {
                    System.out.print(" -> ");
                }
            }
            System.out.println();
            return true;
        } else {
            // Restore previous state if not safe
            for (int i = 0; i < numResources; i++) {
                available[i] += request[i];
                allocation[process][i] -= request[i];
                need[process][i] += request[i];
            }
            return false;
        }
    }

    private boolean canAllocate(int[] work, int[] need) {
        // Check if resources can be allocated to a process
        for (int i = 0; i < numResources; i++) {
            if (need[i] > work[i]) {
                return false;
            }
        }
        return true;
    }

    private void allocateResources(int[] work, int[] allocation) {
        // Allocate resources to a process
        for (int i = 0; i < numResources; i++) {
            work[i] += allocation[i];
        }
    }

    public void displayState() {
        System.out.println("\nCurrent state:");
        System.out.println("Maximum:");
        for (int i = 0; i < numProcesses; i++) {
            System.out.println(Arrays.toString(max[i]));
        }
        System.out.println("Allocation:");
        for (int i = 0; i < numProcesses; i++) {
            System.out.println(Arrays.toString(allocation[i]));
        }
        System.out.println("Available: " + Arrays.toString(available));
        System.out.println("Need:");
        for (int i = 0; i < numProcesses; i++) {
            System.out.println(Arrays.toString(need[i]));
        }
    }

    public static void main(String[] args) {
        BankersAlgorithmInteractive bankersAlgorithm = new BankersAlgorithmInteractive();
        bankersAlgorithm.initialize();

        bankersAlgorithm.displayState();

        ArrayList<Integer> safeSequence = bankersAlgorithm.isSafeState();
        if (safeSequence != null && !safeSequence.isEmpty()) {
            System.out.print("\nSystem is in a safe state. Safe sequence: ");
            for (int i = 0; i < safeSequence.size(); i++) {
                System.out.print("P" + safeSequence.get(i));
                if (i != safeSequence.size() - 1) {
                    System.out.print(" -> ");
                }
            }
            System.out.println();
        } else {
            System.out.println("\nSystem is in an unsafe state.");
        }

        // Example of requesting resources by process
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter process number to request resources: ");
        int process = scanner.nextInt();

        int[] request = new int[bankersAlgorithm.numResources];
        System.out.println("Enter resource request for Process " + process + ": ");
        for (int i = 0; i < bankersAlgorithm.numResources; i++) {
            request[i] = scanner.nextInt();
        }

        if (bankersAlgorithm.requestResources(process, request)) {
            System.out.println("Request by Process " + process + " granted.");
            bankersAlgorithm.displayState();
        } else {
            System.out.println("Request by Process " + process + " denied.");
        }
    }
}
