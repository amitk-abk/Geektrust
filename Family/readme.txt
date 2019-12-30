Set: The Family
Problem 1

Code structure:
    The solution is coded in java (1.8 compliant) and it is gradle based.
    The package named com.gt.tot contains all the code. The base package itself contains
    the main class - Geektrust. It has main method that takes the input file path to execute the code.
    The test case in MeetFamilyTest.java can be run as integration test case.

    The test cases are in test dir which mimics the source package structure. Building the project with
    gradle executes the test cases. The directory test/resources contain input.txt file which contains
    the inputs for integration testing, while file exepctedoutput.txt contains the expected output for given
    input per input.txt. The testoutput.txt is generated as part of execution of integration test case.

    To execute the project, build it with gradle and execute generated geektrust.jar file with input file path
    passed in as command line argument.


What is considered:
    1. As understood from problem statement, head couple is King Shan and Queen Anga, but code treats them same
    as others in tree, so that child addition or relationships queries can be run against them.
    2. Persons family is considered as person, his/her spouse and children.
    3. Tree is loaded at start of application from a fixed location from file, the format of the family addition file is
    very much specific to the code and is expected not to be influenced by external factors.
    4. Program returns UNSUPPORTED_RELATIONSHIP when queried for e.g. Grand-Father as it is not handled.


What is not considered:
    1. The evaluation of input is not done exclusively.
        Program expects the input file to adhere to standard I/O given as part of project explanation.
        The output is also generated in same way.
        Program does not evaluate I/O very exclusively and expects the capitalization to be honoured.
    2. Magical numbers and special characters are hard coded as they do not, mostly, repeat across code files.
    3. Once person has a family with spouse, change of that spouse is not possible & hence person can not have more
        than one family.