Set: Tame Of Thrones
Problem 1: A Golden Crown

Code structure:
    The solution is coded in java (1.8 compliant) and it is gradle based.
    The package named com.gt.tot contains all the code. The base package itself contains
    the main class - Geektrust. It has main method that takes the input file path to execute the code.
    The test case in BidsToRuleSoutherosTest.java can be run as integration test case.

    The test cases are in test dir which mimics the source package structure. Building the project with
    gradle executes the test cases. The directory test/resources contain input.txt file which contains
    the inputs for integration testing, while file exepctedoutput.txt contains the expected output for given
    input per input.txt. The testoutput.txt is generated as part of execution of integration test case.

    To execute the project, build it with gradle and execute generated geektrust.jar file with input file path
    passed in as command line argument.


What is considered:
    1. As understood from problem statement, the driver or primary actor of the application is King Shan hence
    most of the code is very specific to that understanding and hence driver is not generic, while internal logic is.
    2. Code base has unit test cases, again considering King Shan as primary actor. Integration test case is in
    BidsToRuleSoutherosTest.java
    3. Code treats kingdoms and Southeros universe as singly existing entities like singleton.

What is not considered:
    1. The evaluation of input is not done. Program expects the input file to adhere to standard I/O given as part of
        project explanation. The output is also generated in same way. Program does not evaluate I/O.
    2. Also, unit testing of other kingdoms is not considered.
    3. Magical numbers and special characters are hard coded as they do not, mostly, repeat across code files.