# Code Overview

This document provides an explanation of the buffer management implementation classes in this project. These files are located in the root directory of the repository to preserve their original structure and timestamps.

## Files Overview

### DropPolicy.java
Abstract base class that defines the interface for all buffer drop policies. All specific drop policy implementations extend this class.

Key features:
- Defines the abstract `makeRoomForMessage` method that all policies must implement
- Handles configuration for whether messages being sent can be dropped

### MOFODropPolicy.java
Implements the Most Forwarded First drop policy. This policy drops messages that have been forwarded the most times (highest hop count) when buffer overflow occurs.

Key features:
- Uses a comparator to sort messages by hop count
- Drops messages with the highest hop count first
- Continues dropping until enough space is available

### DLADropPolicy.java
Implements the Drop Largest drop policy. This policy drops the largest messages first when buffer overflow occurs.

Key features:
- Uses a comparator to sort messages by size
- Drops messages with the largest size first
- Continues dropping until enough space is available

### DLDropPolicy.java
Implements the Drop Last drop policy. This policy drops the most recently received messages first (LIFO approach) when buffer overflow occurs.

Key features:
- Uses a comparator to sort messages by receive time
- Drops messages with the latest receive time first
- Continues dropping until enough space is available

### FIFODropPolicy.java
Implements the First In First Out drop policy. This policy drops the oldest messages first when buffer overflow occurs.

Key features:
- Uses a comparator to sort messages by receive time
- Drops messages with the earliest receive time first
- Continues dropping until enough space is available

## Integration with ONE Simulator

To use these drop policies in the ONE Simulator, you need to:

1. Copy these files to the appropriate package directory in the ONE Simulator source code
2. Register your desired drop policy in the simulation configuration file using: Group.dropPolicy = [PolicyName]

Where [PolicyName] is one of: MOFODropPolicy, DLADropPolicy, DLDropPolicy, or FIFODropPolicy

## Note on Buffer Management Algorithm

The core of each buffer management technique is implemented in the `makeRoomForMessage()` method, which:
1. Checks if the incoming message size exceeds the buffer size
2. Determines if there's enough free space for the message
3. If not, implements the specific drop policy to free up space
4. Continues dropping messages until enough space is available or no more eligible messages remain