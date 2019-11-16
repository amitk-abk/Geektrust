Set: Tame Of Thrones
Problem 1: Breaker Of Chains

Code structure:
    The solution is coded in java (1.8 compliant) and it is gradle based.
    The package named com.gt.tot contains all the code. The base package itself contains
    the main class - Geektrust. It has main method that takes the input file path to execute the code.
    Please note the secrets.txt path is hard coded in RuleSoutherosUniverse.java as I was not sure
    if that would be falling under "input to program" category.

    The test cases are in test dir which mimics the source package structure. Building the project with
    gradle executes the test cases. The directory test/resources contains text files which are used for testing.
    There is no integration test written per say owing to repetitions in tied scenario.

    To execute the project, build it with gradle and execute generated geektrust.jar file with input file path
    passed in as command line argument.


What is considered:
    1. Any kingdom can participate into competition but it has to enter into competition first - program treats it
        as hard constraint. Yet, kingdoms can send messages to each other.
    2. As from problem 1, Space kingdom's king's name is taken - King Shan and same is printed when ruler name is asked.
        Other kingdom's king's names are not given but code does make provision for it but treats kingdom name as king's name.
    3. High priest does only choosing a few from ballot & handing over messages to kingdoms not declaring results or conducting repoll.
    4. Balloting shoulders the responsibility of polling and only declares if it is tie or not. Balloting does not do repoll.


What is not considered:
    1. The evaluation of input from input file is not done.
    2. Program expects the input file to adhere to standard I/O given as part of project explanation.
       The output is also generated in same way. Program does not evaluate I/O.
    3. The tied case is unique & polling may repeat more than one, that is not evaluated via test case.
    4. For recording repoll result in format like Results after round one/two/three ballot count, the digit (1,2 etc)
        to word conversion is not considered.
    5. Magical numbers and special characters are hard coded as they do not, mostly, repeat across code files.