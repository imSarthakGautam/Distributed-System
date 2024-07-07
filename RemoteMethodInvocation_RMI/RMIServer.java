import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

// Create server
public class RMIServer {
    //entry point of server application
    public static void main(String[] args) {
        try {
            // Create an instance of the remote object
            RemoteInterfaceImpl obj = new RemoteInterfaceImpl();
            //This is the actual object that will handle remote method invocations from clients.



            //Creates an RMI registry on port 1099.
            //The RMI registry acts as a naming service where remote objects are registered under unique names for clients to look up.
            Registry registry = LocateRegistry.createRegistry(1099);
            
            // Bind the remote object's stub in the registry
            registry.rebind("RemoteInterface", obj);

            System.out.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}