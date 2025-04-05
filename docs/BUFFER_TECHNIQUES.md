# Buffer Management Techniques

This document provides a detailed explanation of the buffer management techniques implemented in this project.

## Introduction

In Delay Tolerant Networks (DTNs), nodes have limited buffer space, and when this buffer becomes full, the node must decide which messages to drop to make room for new incoming messages. The choice of which messages to drop can significantly impact the overall network performance.

## Implemented Techniques

### MOFO (Most Forwarded First)

**Basic Principle**: Drop messages that have been forwarded the most times first.

**Implementation Details**:
- Messages are sorted based on their hop count (number of times they've been forwarded)
- Messages with the highest hop count are dropped first
- The assumption is that messages with high hop counts have a higher probability of already being delivered or existing elsewhere in the network

**Algorithm**:
1. Compare messages based on their hop count
2. Sort messages in ascending order of hop count
3. Remove messages starting from the one with the highest hop count
4. Continue until enough space is freed

**Use Case**: 
MOFO is particularly effective in networks with high node density, where messages are likely to be replicated multiple times.

### DLA (Drop Largest)

**Basic Principle**: Drop the largest messages first.

**Implementation Details**:
- Messages are sorted based on their size
- Largest messages are dropped first
- This approach maximizes the number of messages that can be kept in the buffer

**Algorithm**:
1. Compare messages based on their size
2. Sort messages in ascending order of size
3. Remove messages starting from the largest one
4. Continue until enough space is freed

**Use Case**: 
DLA is useful when message delivery count is more important than the volume of data delivered.

### DL (Drop Last)

**Basic Principle**: Drop the most recently received messages first (LIFO - Last In, First Out).

**Implementation Details**:
- Messages are sorted based on their receive time
- Messages with the latest receive time are dropped first
- The assumption is that older messages have higher priority

**Algorithm**:
1. Compare messages based on their receive time
2. Sort messages in ascending order of receive time
3. Remove messages starting from the one with the latest receive time
4. Continue until enough space is freed

**Use Case**: 
DL is effective when older messages are more important or time-sensitive.

### FIFO (First In, First Out)

**Basic Principle**: Drop the oldest messages first.

**Implementation Details**:
- Messages are sorted based on their receive time
- Messages with the earliest receive time are dropped first
- The assumption is that newer messages have higher priority

**Algorithm**:
1. Compare messages based on their receive time
2. Sort messages in ascending order of receive time
3. Remove messages starting from the one with the earliest receive time
4. Continue until enough space is freed

**Use Case**: 
FIFO is useful when newer messages contain more relevant or time-sensitive information.

## Performance Considerations

When choosing a buffer management technique, consider the following factors:

1. **Message Delivery Probability**: How does the technique affect the likelihood of messages reaching their destination?

2. **Overhead Ratio**: What is the cost in terms of network resources for each delivered message?

3. **Hop Count Average**: How many intermediate nodes do messages pass through before reaching their destination?

4. **Latency Average**: What is the average time from message creation to delivery?

Different buffer management techniques show different trade-offs between these metrics, and the optimal choice depends on the specific requirements and constraints of the network.

## Implementation in the ONE Simulator

In the ONE Simulator, buffer management techniques are implemented as Java classes that extend the abstract `DropPolicy` class. Each technique implements the `makeRoomForMessage` method, which is called when a new message arrives and the buffer is full.

The buffer management technique is specified in the simulation configuration file using the `Group.dropPolicy` parameter.