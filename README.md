# CSX42: Assignment 3
## Group Member's Name: Abha Chaudhary and Sagar Toke

-----------------------------------------------------------------------
-----------------------------------------------------------------------
Point to note:
1.Used 3 slack days
2. input.txt should be in objectPoolPlay/ directory
3. Output file will be generated at objectPoolPlay/ directory

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

##References and citation
-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"We have done this assignment completely on our own. We have not copied
it, nor have we given our solution to anyone else. We understand that if
We are involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: [04/01/2020]


