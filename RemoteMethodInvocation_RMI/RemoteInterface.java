import java.rmi.Remote;
//This imports the Remote interface from the java.rmi package.
// Any remote object in RMI must implement this interface.

import java.rmi.RemoteException;
//This imports the RemoteException class from the java.rmi package.
// Remote methods must declare that they throw this exception to handle potential network-related issues that may occur during remote method calls.


//------------| Define remote interface |--------
public interface RemoteInterface extends Remote {

    String sayHello() throws RemoteException;
    // declare a method sayHello that must return a String and can throw a RemoteException.

}