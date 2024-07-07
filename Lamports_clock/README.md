# Lamport's clock 

Lamport's Clock is a logical clock algorithm used in distributed systems to order events without relying on physical clocks. 
 It was introduced by Leslie Lamport in 1978 to provide a way to determine the sequence of events in a distributed system where there is no global clock and communication delays can vary.

- It is widely used for it's simplicity and lack of need for synchronization.

## what is a Logical Time 

- In distributed systems, Instead of relying on physical time, Lamport's Clock assigns a logical timestamp to each event.

Logical time is a numerical value that increases as events occur, ensuring a partial ordering of events

**Events Ordering**
Events within a single process are ordered sequentially.
Events in different processes are ordered based on the logical timestamps assigned to them, which helps to determine causality between events.

## Algorithm

#### Initialization:

Each process in the distributed system has a counter, which starts at 0.

#### Event Occurrence:

When an event occurs within a process (e.g., sending a message, receiving a message, internal computation), the process increments its counter by 1 and assigns the new value as the timestamp of the event.

#### Message Sending:

When a process sends a message, it includes its current counter value (timestamp) in the message.

#### Message Receiving:

- When a process receives a message, it compares its own counter value with the timestamp in the message.
- The process sets its counter to the maximum of its current counter value and the received timestamp, then increments it by 1.
- This ensures that the logical clock is synchronized across processes and reflects the causal relationships between events.

### Example :

Assume we have two processes, P1 and P2:

- P1 starts with a counter value of 0.
- P1 performs an internal event and increments its counter to 1.
- P1 sends a message to P2 with a timestamp of 1.
- P2 receives the message and sets its counter to the maximum of its current counter (initially 0) and the received timestamp (1), then increments it by 1, resulting in a counter value of 2.
- P2 performs an internal event and increments its counter to 3.
- P2 sends a message to P1 with a timestamp of 3.
- P1 receives the message and sets its counter to the maximum of its current counter (1) and the received timestamp (3), then increments it by 1, resulting in a counter value of 4.