import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

// Create client
public class RMIClient {
    public static void main(String[] args) {
        try {
            // Get a reference to the remote object from the registry
            // Retrieves a reference to the RMI registry running on localhost at port 1099.
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            //ooks up the remote object named "RemoteInterface" in the RMI registry.
            RemoteInterface stub = (RemoteInterface) registry.lookup("RemoteInterface");

            // Invoke the remote method : this method is executed in server side
            String response = stub.sayHello();
            System.out.println("Response from server: " + response);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }

        
    }
}

//Next steps : terminal

//1. compile
//javac RemoteInterface.java RMIClient.java

//2. Start RMI registry
//start rmiregistry

//3. run the server

//4. run the client
// java RMIClient

