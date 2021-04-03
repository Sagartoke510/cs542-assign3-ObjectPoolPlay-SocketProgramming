# CSX42: Assignment 3
## Group Member's Name: Abha Chaudhary and Sagar Toke

-----------------------------------------------------------------------
-----------------------------------------------------------------------
Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in objectPoolPlay/src folder.

-----------------------------------------------------------------------
## Instruction to clean:

```commandline
ant -buildfile objectPoolPlay/src/build.xml clean
```

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

```commandline
ant -buildfile objectPoolPlay/src/build.xml all
```

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

#### Use the below command to run the persister service first.

```commandline
ant -buildfile objectPoolPlay/src/build.xml run-persister-service
-Dport="<Port number on which the server should listen>" 
-DoutputFile="<Name of the output file to which the data received on the port should be written>" 
```
#### Use the below command to run the prime detector.

```commandline
ant -buildfile objectPoolPlay/src/build.xml run-prime-detector
-DinputFile="<Port number on which the server should listen>" 
-DnumThreads="<The number of threads to be used: referred to as NUM_THREADS below>" 
-Dcapacity="<Capacity of the results data structure.>" 
-DpersisterServiceIp="<IP Address of the PersisterService>" 
-DpersisterServicePort="<Port number on which the PersisterService is listening for data>" 
-DdebugValue="<an integer that controls what is printed on stdout>"
```
-----------------------------------------------------------------------
## Description:
We have used Vector data structure to store results since it is synchronized.
Assumptions:
1. The first assumption would be the input is well formated with every number in new line.
  The input number are integer value only
  We are assuming that there are no duplicate
  
2. Data structures:
  Vector: Used for storing the prime number results as it is thread safe.
  ArrayList: Used for storing the threads.

External Materials:

  Socket Programming - geeksforgeeks
  Multithreading wait(), notify() - tutorials point

Compiling: Follow the instruction as mentioned above.

Run: Follow the instructions as mentioned above.

Challenges Faced:

One of our computer system crashed that lead to lesser co-ordination with each other in committing the code to the repository. Luckily, we got another system but it was too late for us to submit the assignment on time.

Due to this and multiple submissions during those days, we were bound to use "4 slack days" in order to complete our assignment.





