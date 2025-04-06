# Configuration Files

This directory contains information about configuring the ONE Simulator to run with different buffer management techniques.

## Setting Up the ONE Simulator Configuration

To configure the ONE Simulator to use these buffer management techniques, you need to add specific parameters to your simulation configuration files.

## Buffer Management Configuration

To specify the buffer management technique, use: Group.dropPolicy = [PolicyName]

Where `[PolicyName]` is one of:
- `MOFODropPolicy` - Most Forwarded First drop policy
- `DLADropPolicy` - Drop Largest drop policy
- `DLDropPolicy` - Drop Last drop policy
- `FIFODropPolicy` - First In First Out drop policy

## Buffer Size Configuration

To specify the buffer size (in MB), use: Group.bufferSize = [size]

Where `[size]` is one of: 5M, 10M, 15M, 20M, 25M, 30M, 35M, or 40M

## Routing Protocol Configuration

To specify PRoPHET routing protocol, use: Group.router = PRoPHETRouter
## Sample Configuration Snippet

Here's a sample configuration snippet that you can use as a template:

# Scenario settings
Scenario.name = prophet-buffer-management
Scenario.simulateConnections = true
Scenario.updateInterval = 0.1
Scenario.endTime = 43200

# Interface configuration
btInterface.type = SimpleBroadcastInterface
btInterface.transmitSpeed = 2M
btInterface.transmitRange = 10

# Group-specific settings
Group.movementModel = MapBasedMovement
Group.router = PRoPHETRouter
Group.bufferSize = 5M
Group.dropPolicy = MOFODropPolicy

# PRoPHET router settings
ProphetRouter.secondsInTimeUnit = 30
ProphetRouter.beta = 0.9
ProphetRouter.gamma = 0.999