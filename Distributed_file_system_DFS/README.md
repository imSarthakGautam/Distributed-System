# Distributed File System (DFS)

A **distributed file system (DFS)** is a type of file system that allows access to files from multiple hosts sharing via a computer network.

This makes it possible for users on different machines to share files and storage resources seamlessly. 

![Working of DFS](https://media.geeksforgeeks.org/wp-content/uploads/20240705120347/Working-of-DFS.webp)

## Key Features and Concepts

1. **Transparency**:
   - **Location Transparency**: Users do not need to know the physical location of the files.
   - **Access Transparency**: Accessing a remote file is similar to accessing a local file.
   - **Failure Transparency**: The system hides hardware and software failures to provide continuous service.
   - **Replication Transparency**: Users are unaware of the replication of files across the system for fault tolerance.

2. **Scalability**:
   - DFS can handle a large number of nodes and huge amounts of data, scaling out by adding more machines.

3. **Reliability and Availability**:
   - Redundancy and replication ensure that data is not lost even if some machines fail.
   - High availability is maintained by distributing data across multiple servers.

4. **Performance**:
   - Data is distributed to balance the load and optimize performance.
   - Caching mechanisms are often used to improve access speeds.

5. **Security**:
   - Authentication and authorization mechanisms ensure that only authorized users can access the files.
   - Encryption may be used to protect data in transit and at rest.

## Examples of Distributed File Systems

1. **Network File System (NFS)**:
   - Developed by Sun Microsystems.
   - Allows a computer to access files over a network as if they were on its local disks.

2. **Andrew File System (AFS)**:
   - Developed at Carnegie Mellon University.
   - Provides a scalable environment and supports features like file caching and replication.

3. **Google File System (GFS)**:
   - Developed by Google to meet its large-scale data processing needs.
   - Designed to handle large files and high throughput.

4. **Hadoop Distributed File System (HDFS)**:
   - Part of the Hadoop ecosystem.
   - Designed for large-scale data processing with high fault tolerance and throughput.

## Components of a Distributed File System

1. **Client Nodes**:
   - Machines where users access and interact with the file system.

2. **Server Nodes**:
   - Machines that store the actual data and manage file metadata.

3. **Metadata Server**:
   - Manages metadata (information about file locations, permissions, etc.) and directs client requests to the appropriate server nodes.

## Example Use Case

Consider a company with offices in multiple locations. A DFS can be used to ensure that employees from any office can access the same files and resources without needing to worry about the physical location of the files. If an employee in New York updates a file, the changes are immediately available to an employee in San Francisco.

## Advantages

- **Resource Sharing**: Users can share files and storage resources easily.
- **Fault Tolerance**: Data is replicated across multiple nodes to prevent data loss.
- **Load Balancing**: Data distribution helps in balancing the load across the network.

## Challenges

- **Consistency**: Ensuring that all users see the most recent version of a file can be complex.
- **Latency**: Network delays can affect file access times.
- **Security**: Protecting data from unauthorized access and ensuring secure communication is critical.

In summary, a distributed file system is essential for efficiently managing and accessing files in a distributed computing environment, providing a seamless and reliable user experience while handling large amounts of data across multiple locations.


# Algortihm for Implementation of DFS in code


**1. Initialize Metadata Server:**
1.1. Create an instance of MetadataServer called
metadataServer.
1.2. Initialize metadataServer.fileToNodeMap as an empty map.

**2. Initialize Storage Nodes:**
2.1. Create storage nodes (e.g., storageNode1, storageNode2,
etc.) with unique nodeIds.
2.2. Initialize storageNodeX.fileStorage as an empty map for
each storage node.


**3. Add File Entries to Metadata Server:**
3.1. Call metadataServer.addFileEntry(filename, storageNode)
for each file in the system.
3.2. Associate each file (filename) with the appropriate
storage node (storageNode) in the metadata server's
fileToNodeMap.

**4. Client Upload File:**
4.1. Create a Client instance called client, passing the
metadataServer to the constructor.
4.2. Call client.uploadFile(filename, data) to upload a file
to the DFS.
4.3. The uploadFile function does the following:
i. Look up the appropriate storage node for the file by
calling metadataServer.getStorageNode(filename).
ii. Upload the file data (byte array) to the identified
storage node (not shown in the simplified example).

**5. Client Download File:**
5.1. Create a Client instance called client, passing the
metadataServer to the constructor.
5.2. Call client.downloadFile(filename) to download a file
from the DFS.
5.3. The downloadFile function does the following:
i. Look up the appropriate storage node for the file by
calling metadataServer.getStorageNode(filename)
ii. Download the file data from the identified storage node
(not shown in the simplified example).
iii. Return the file data as a byte array.

**6. Main Function:**
6.1. In the main function, create the MetadataServer instance
and the StorageNode instances.
6.2. Add file entries to the metadata server to associate
files with storage nodes.
6.3. Use the Client to perform upload and download operations
on the DFS.




