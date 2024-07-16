# Algorithm for Distributed File System (DFS)

## 1. Initialize Metadata Server:
#### 1.1. Create an instance of MetadataServer called metadataServer.
#### 1.2. Initialize metadataServer.fileToNodeMap as an empty map.

## 2. Initialize Storage Nodes:
#### 2.1. Create storage nodes (e.g., storageNode1, storageNode2, etc.) with unique nodeIds.
#### 2.2. Initialize storageNodeX.fileStorage as an empty map for each storage node.

## 3. Add File Entries to Metadata Server:
#### 3.1. Call metadataServer.addFileEntry(filename, storageNode) for each file in the system.
#### 3.2. Associate each file (filename) with the appropriate storage node (storageNode) in the metadata server's fileToNodeMap.

## 4. Client Upload File:
#### 4.1. Create a Client instance called client, passing the metadataServer to the constructor.
#### 4.2. Call client.uploadFile(filename, data) to upload a file to the DFS.
#### 4.3. The uploadFile function does the following:
##### i. Look up the appropriate storage node for the file by calling metadataServer.getStorageNode(filename).
##### ii. Upload the file data (byte array) to the identified storage node (not shown in the simplified example).

## 5. Client Download File:
#### 5.1. Create a Client instance called client, passing the metadataServer to the constructor.
#### 5.2. Call client.downloadFile(filename) to download a file from the DFS.
#### 5.3. The downloadFile function does the following:
##### i. Look up the appropriate storage node for the file by calling metadataServer.getStorageNode(filename).
##### ii. Download the file data from the identified storage node (not shown in the simplified example).
##### iii. Return the file data as a byte array.

## 6. Main Function:
#### 6.1. In the main function, create the MetadataServer instance and the StorageNode instances.
#### 6.2. Add file entries to the metadata server to associate files with storage nodes.
#### 6.3. Use the Client to perform upload and download operations on the DFS.


