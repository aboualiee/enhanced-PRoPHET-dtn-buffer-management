# Installation and Setup Guide

This guide provides step-by-step instructions to set up and run the enhanced PRoPHET routing protocol with buffer management techniques.

## Prerequisites

- Java Development Kit (JDK) 8 or later
- Eclipse IDE (recommended for easier integration with ONE Simulator)
- ONE Simulator v1.6.0

## Setting Up the ONE Simulator

1. **Download the ONE Simulator**
   - Download the ONE Simulator v1.6.0 from [https://akeranen.github.io/the-one/](https://akeranen.github.io/the-one/)
   - Alternatively, clone the repository: `git clone https://github.com/akeranen/the-one.git`

2. **Install the ONE Simulator**
   - Extract the downloaded archive to a directory of your choice
   - Verify the installation by running the compile script:
     - On Windows: `compile.bat`
     - On Linux/Mac: `./compile.sh`

3. **Import into Eclipse (Optional)**
   - Open Eclipse
   - Select File > Import > Existing Projects into Workspace
   - Browse to the ONE Simulator directory and click Finish

## Integrating Buffer Management Classes

1. **Create the Buffer Management Package**
   - In the ONE Simulator source directory, create a new package called `buffermanagement`
   - This is typically under `src/` or `src/core/` depending on your version

2. **Copy the Source Files**
   - Copy the following files from this repository's root directory to the `buffermanagement` package:
     - `DropPolicy.java`
     - `MOFODropPolicy.java`
     - `DLADropPolicy.java`
     - `DLDropPolicy.java`
     - `FIFODropPolicy.java`

3. **Modify Router Classes (if needed)**
   - In some cases, you may need to modify the router classes to integrate with the buffer management policies
   - Specifically, ensure that the `ActiveRouter` class calls the appropriate drop policy methods

4. **Compile the Project**
   - Recompile the ONE Simulator with the new classes:
     - On Windows: `compile.bat`
     - On Linux/Mac: `./compile.sh`

## Running Simulations

1. **Configure the Simulation**
   - Copy one of the configuration files from the `config/` directory to your ONE Simulator directory
   - Or modify an existing configuration file to include:
     ```
     Group.router = PRoPHETRouter
     Group.bufferSize = [size]M  # Replace [size] with 5, 10, 15, etc.
     Group.dropPolicy = [PolicyName]  # Replace [PolicyName] with MOFODropPolicy, DLADropPolicy, etc.
     ```

2. **Run the Simulation**
   - From the command line:
     ```
     java -jar one.jar path/to/config-file.txt
     ```
   - Or from Eclipse:
     - Set up a run configuration pointing to the ONE Simulator main class
     - Add the configuration file path as a program argument

3. **View the Results**
   - Results will be generated in the `reports/` directory (or wherever specified in your configuration)
   - The report files include data on delivery probability, overhead ratio, hop count average, and latency average

## Troubleshooting

### Common Issues

1. **Compilation Errors**
   - Ensure that the package declaration in your Java files matches the directory structure
   - Check that all required classes are imported correctly

2. **Runtime Errors**
   - Verify that the configuration file paths are correct
   - Ensure that the drop policy class names are spelled correctly in the configuration file

3. **No Performance Difference**
   - Make sure the buffer size is set small enough to create congestion (e.g., 5MB)
   - Ensure that enough messages are generated to fill the buffer

### Getting Help

If you encounter any issues not covered in this guide, you can:

1. Check the ONE Simulator documentation
2. Inspect the log files for error messages
3. Contact the author of this repository for assistance

## Next Steps

After successful installation and setup, you can:

1. Run simulations with different buffer sizes
2. Try different combinations of routing protocols and buffer management techniques
3. Analyze the results to determine the optimal configuration for your specific scenario