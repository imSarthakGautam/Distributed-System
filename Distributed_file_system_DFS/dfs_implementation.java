import java.util.*;

// Class representing the Metadata Server (Librarian)
class MetadataServer {
    Map<String, Integer> fileToNodeMap; // Catalog to map files to storage nodes

    // Constructor to initialize the metadata server
    public MetadataServer() {
        fileToNodeMap = new HashMap<>();
    }

    // Method to add a file entry to the metadata server
    public void addFileEntry(String filename, int storageNode) {
        fileToNodeMap.put(filename, storageNode);
    }

    // Method to get the storage node for a given filename
    public int getStorageNode(String filename) {
        return fileToNodeMap.getOrDefault(filename, -1); // Return -1 if file not found
    }
}

// Class representing a Storage Node (Bookshelf)
class StorageNode {
    private int nodeId; // Unique identifier for the storage node
    private Map<String, String> fileStorage; // Storage for files (simplified as filename -> content)

    // Constructor to initialize the storage node
    public StorageNode(int nodeId) {
        this.nodeId = nodeId;
        fileStorage = new HashMap<>();
    }

    // Method to upload a file to the storage node
    public void uploadFile(String filename, String data) {
        fileStorage.put(filename, data);
    }

    // Method to download a file from the storage node
    public String downloadFile(String filename) {
        return fileStorage.getOrDefault(filename, "File not found"); // Return message if file not found
    }
}

// Class representing a Client (Visitor)
class Client {
    private MetadataServer metadataServer; // Reference to the metadata server (librarian)
    private Scanner scanner; // Scanner for user input

    // Constructor to initialize the client with the metadata server
    public Client(MetadataServer metadataServer, Scanner scanner) {
        this.metadataServer = metadataServer;
        this.scanner = scanner;
    }

    // Method for the client to upload a file to the DFS
    public void uploadFile() {
        System.out.print("Enter filename to upload: ");
        String filename = scanner.nextLine();
        System.out.print("Enter content of the file: ");
        String data = scanner.nextLine();

        int storageNode = metadataServer.getStorageNode(filename); // Find where to upload the file
        if (storageNode != -1) { // If storage node found
            System.out.println("Uploading " + filename + " to StorageNode " + storageNode);
            // In a real system, this is where the file would be uploaded to the identified storage node.
        } else {
            System.out.println("File " + filename + " not found in the DFS.");
        }
    }

    // Method for the client to download a file from the DFS
    public void downloadFile() {
        System.out.print("Enter filename to download: ");
        String filename = scanner.nextLine();

        int storageNode = metadataServer.getStorageNode(filename); // Find where the file is stored
        if (storageNode != -1) { // If storage node found
            System.out.println("Downloading " + filename + " from StorageNode " + storageNode);
            // In a real system, this is where the file content would be retrieved from the storage node.
        } else {
            System.out.println("File " + filename + " not found in the DFS.");
        }
    }
}

// Main class to simulate the DFS operations
public class InteractiveDistributedFileSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize the Metadata Server
        MetadataServer metadataServer = new MetadataServer();

        // Initialize Storage Nodes
        StorageNode storageNode1 = new StorageNode(1);
        StorageNode storageNode2 = new StorageNode(2);

        // Add file entries to the Metadata Server based on user input
        addFileEntries(metadataServer, scanner);

        // Create a Client to interact with the DFS
        Client client = new Client(metadataServer, scanner);

        // Interaction loop with the user
        boolean exit = false;
        while (!exit) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Upload a file");
            System.out.println("2. Download a file");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    client.uploadFile();
                    break;
                case 2:
                    client.downloadFile();
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter again.");
                    break;
            }
        }

        // Close the scanner
        scanner.close();
    }

    // Helper method to add file entries to the Metadata Server based on user input
    private static void addFileEntries(MetadataServer metadataServer, Scanner scanner) {
        boolean addMore = true;
        while (addMore) {
            System.out.print("Enter filename to add: ");
            String filename = scanner.nextLine();
            System.out.print("Enter storage node (1 or 2) for " + filename + ": ");
            int storageNode = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            metadataServer.addFileEntry(filename, storageNode);

            System.out.print("Do you want to add more files? (yes/no): ");
            String response = scanner.nextLine().toLowerCase();
            if (!response.equals("yes")) {
                addMore = false;
            }
        }
    }
}
