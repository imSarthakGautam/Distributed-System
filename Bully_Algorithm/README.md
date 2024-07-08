# Bully Algorithm

In many distributed systems, certain tasks need a single point of control to avoid conflicts and ensure orderly execution of tasks. A leader or coordinator can manage these tasks efficiently.

Furthemore for all nodes to agree on certain critical decisions like load balancing, scheduling and resource allocation having a single leader helps in achieving consensus and coordination.

The Bully Algorithm is a classic algorithm in DS that provides a systematic way to elect the new leader among the set of nodes.

In a scenario where the leader fails, the system must detect this failure and elect a new leader to take over the responsibilities. 


## Key Concepts of Bully Algorithm

# Leader
In the context of distributed systems, a leader (or coordinator) is a designated node or process responsible for managing and coordinating specific tasks or functions across the entire system, maintaining consistency, resolving conflicts and driving consensus.

# Election Trigger
An election can be triggered if a node detects that the coordinator has failed (e.g., it doesn’t respond to a heartbeat message).

# Process IDs

Each process has a unique identifier (ID), and it’s assumed that these IDs are known to all processes. The process with the highest ID is the one that should be elected as the coordinator.

## Algorithmic Steps
  `Lets's assume we have five processes with IDs 1, 2, 3, 4, and 5`

### Step 1: Election Initiation:
 When a process detects that the coordinator has failed, it initiates an election by sending an election message to all processes with higher IDs.  

  `Suppose the coordinator was process 5.`

### Step 2: Response Handling:
 If a process receives an election message and has a higher ID, it responds to the initiating process and starts its own election.  

  `Process 3 detects the failure and starts an election. It sends an election message to processes 4 and 5.`

### Step 3: No Higher Response:
 If a process doesn’t receive any response from higher ID processes, it declares itself the coordinator and sends a coordinator message to all other processes.  

`Process 4 receives the election message from process 3 and responds because it has a higher ID.`  
`Process 5 is down, so it does not respond.`

`Process 4, upon receiving an election message, sends its own election message to process 5.`  
`Since process 5 is down, process 4 does not receive any response.` 
`Process 4 then declares itself as new co-ordinator`

### Step 4: Receiving Coordinator Message: 
 When a process receives a coordinator message, it updates its coordinator information to the ID specified in the message.
 
 ` All processes, upon receiving the coordinator message from process 4, update their coordinator information to process 4.`

 ## Pseudocode

 ```
 function startElection(processID):
    send "election" to all processes with ID > processID
    wait for responses for a certain timeout period
    
    if receive response:
        wait for coordinator message
    else:
        declare self as coordinator
        send "coordinator" to all processes

function onReceiveElectionMessage(senderID):
    if senderID < processID:
        send "response" to senderID
        startElection(processID)

function onReceiveCoordinatorMessage(coordinatorID):
    update current coordinator to coordinatorID

 ```