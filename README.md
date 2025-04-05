# Enhanced PRoPHET Routing Protocol with Buffer Management Techniques in DTN

## About This Repository
This repository contains the code and documentation from my undergraduate thesis project completed in 2021 at Ahmadu Bello University Zaria. 

**Note on Academic Integrity**: The original implementation, code files, and results remain completely unchanged from the 2021 thesis submission. Only documentation has been added in 2025 to improve accessibility and understanding of the project, without altering any of the original work. The original file structure and commit timestamps have been preserved to maintain the integrity of the academic submission.

## Overview
This project implements and analyzes four buffer management techniques (MOFO, DLA, DL, and FIFO) with the PRoPHET routing protocol in Delay Tolerant Networks (DTNs). The research evaluates how these different buffer management techniques affect network performance under various buffer size constraints.

## Background
Delay Tolerant Networks (DTNs) are designed for environments with intermittent connectivity where traditional networks fail. In scenarios such as emergency response, disaster areas, or underdeveloped regions, communication infrastructure might be limited or damaged.

DTNs operate using a store-carry-forward paradigm, where:
- Nodes store messages in their buffers
- Carry them while moving
- Forward them when encountering other nodes

Due to this mechanism, effective buffer management becomes crucial for optimal performance. This project focuses on enhancing the PRoPHET routing protocol with efficient buffer management techniques.

## Objectives
- Implement and analyze four buffer management techniques: MOFO, DLA, DL, and FIFO
- Evaluate their performance with PRoPHET routing protocol under variable buffer sizes (5MB-40MB)
- Compare techniques using delivery probability, overhead ratio, hop count average, and latency average
- Determine the optimal buffer management technique for PRoPHET routing

## Buffer Management Techniques Implemented
1. **MOFO (Most Forwarded First)**: Drops messages with the highest hop count first
2. **DLA (Drop Largest)**: Drops messages with the largest size first
3. **DL (Drop Last)**: Drops the most recently received messages first (LIFO)
4. **FIFO (First In First Out)**: Drops the oldest messages first

## Implementation
The project extends the ONE (Opportunistic Network Environment) Simulator with custom buffer management classes:
- `DropPolicy.java`: Abstract base class for all drop policies
- `MOFODropPolicy.java`: Most Forwarded First drop policy
- `DLADropPolicy.java`: Drop Largest drop policy
- `DLDropPolicy.java`: Drop Last drop policy
- `FIFODropPolicy.java`: First In First Out drop policy

## Key Findings
- Increasing buffer size improves delivery probability and reduces overhead ratio across all techniques
- Increasing buffer size negatively affects latency average
- MOFO slightly outperforms other techniques in terms of delivery probability, overhead ratio, and hop count average
- DLA shows promising results for hop count average in some buffer size configurations

## Simulation Environment
- **Simulator**: ONE (Opportunistic Network Environment) v1.6.0
- **Map**: Helsinki, Finland
- **Nodes**: 126 nodes (80 pedestrians, 40 cars, 6 trams)
- **Simulation Time**: 43,200 seconds (12 hours)
- **Buffer Sizes**: 5MB, 10MB, 15MB, 20MB, 25MB, 30MB, 35MB, 40MB
- **Message TTL**: 90 minutes

## How to Use
1. Install the ONE Simulator v1.6.0
2. Copy the buffer management classes to the simulator's source directory
3. Configure the simulator using the sample configuration files
4. Run simulations with varying buffer sizes

## Repository Structure
This repository maintains its original structure from 2021 for academic integrity purposes. The file organization is as follows:

- Root directory - Contains the original buffer management Java files
- `/docs` - Additional documentation about the buffer management techniques and installation guides
- `/config` - Information about ONE simulator configuration
- `/results` - Performance analysis and visualization
  - `/results/visualization` - Visualization graphs for performance metrics
  - `/results/simulation_result` - Detailed tables of simulation results

**Note on Repository Organization**: The original file structure and timestamps have been preserved to maintain academic integrity. All Java source files remain in their original locations with original commit dates from 2021.

## License
MIT License

## Contact
Aliyu Abubakar - aliyuabubakar599@gmail.com