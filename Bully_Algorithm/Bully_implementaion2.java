package Bully_Algorithm;

import java.util.ArrayList;
import java.util.List;

// Class representing a single process/node in the distributed system
class Process {
    private int id; // Process ID
    private boolean isCoordinator; // Flag to indicate if this process is the coordinator
    private List<Process> processes; // List of all processes in the system

    public Process(int id) {
        this.id = id;
        this.isCoordinator = false;
        this.processes = new ArrayList<>();
    }

    // Function to start the election process
    public void startElection() {
        System.out.println("Process " + id + " starts election.");

        // Find higher numbered processes
        List<Process> higherProcesses = new ArrayList<>();
        for (Process p : processes) {
            if (p.getId() > id) {
                higherProcesses.add(p);
            }
        }

        // Send election message to all higher numbered processes
        for (Process p : higherProcesses) {
            p.receiveElection(id);
        }

        // Assume this process becomes coordinator if no higher numbered processes respond
        for (Process p : higherProcesses) {
            if (!p.isCoordinator()) {
                this.isCoordinator = true;
                System.out.println("Process " + id + " becomes coordinator.");
                break;
            }
        }

        // Notify all processes of coordinator election result
        for (Process p : processes) {
            p.notifyCoordinator(this.isCoordinator);
        }
    }

    // Function to receive election message
    public void receiveElection(int senderId) {
        System.out.println("Process " + id + " receives election message from process " + senderId);
    }

    // Function to notify all processes of coordinator election result
    public void notifyCoordinator(boolean isCoordinator) {
        if (isCoordinator) {
            System.out.println("Process " + id + " receives coordinator notification: 'I am the coordinator.'");
        } else {
            System.out.println("Process " + id + " receives coordinator notification: 'Someone else is the coordinator.'");
        }
    }

    public int getId() {
        return id;
    }

    public boolean isCoordinator() {
        return isCoordinator;
    }

    public void addProcess(Process p) {
        processes.add(p);
    }
}

public class Bully {

    public static void main(String[] args) {
        // Create processes
        Process p1 = new Process(1);
        Process p2 = new Process(2);
        Process p3 = new Process(3);
        Process p4 = new Process(4);

        // Add processes to each other's list
        p1.addProcess(p2);
        p1.addProcess(p3);
        p1.addProcess(p4);

        p2.addProcess(p1);
        p2.addProcess(p3);
        p2.addProcess(p4);

        p3.addProcess(p1);
        p3.addProcess(p2);
        p3.addProcess(p4);

        p4.addProcess(p1);
        p4.addProcess(p2);
        p4.addProcess(p3);

        // Start the election process
        p3.startElection();
    }
}
