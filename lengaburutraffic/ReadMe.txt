Set: Traffic
Problem 1: Lengaburu Traffic

Code structure:
    The solution is coded in java (1.8 compliant) and it is gradle based.
    The package named com.gt.tot contains all the code. The base package itself contains
    the main class - Main. It has main method that takes the input file path to execute the code.

    PLEASE NOTE: the initialize.txt path is hard coded for TourOfLengaburu.java from Main.java as I was not sure
        if that would be falling under "input to program" category. It is used to initialize orbit and vehicle details.
        There is not thorough input validation against this file & activity. The format is expected to be preserved
        should there be any change.

    The test cases are in test dir which mimics the source package structure. Building the project with
    gradle executes the test cases. The directory test/resources contains text files which are used for testing.
    There is no integration test written per say owing to repetitions in tied scenario.

    To execute the project, build it with gradle and execute generated geektrust.jar file with input file path
    passed in as command line argument.


What is considered:
    1. The input is read from command line in format provided in sample I/O, a very minimalistic validation is done
        ONLY on the content.
    2. The routes are derived from orbits (considering Lengaburu planet as context & not inter-planetary) as discussed on slack.
    3. Exception is thrown in case of missing dependency (input file e.g.).
    4. Available vehicles in context are considered as part of ruler's fleet.
    5. Orbit name in case of orbit, weather condition name in case of weather are treated as identifiers.
    6. Inability to be able to commute in particular weather is considered as vehicle type's local knowledge.
    7. King shan and Lengaburu planet are treated as part of base context.


What is not considered:
    1. The thorough evaluation of input from input file is not done, only basic cases are handled.
    2. Program expects the input file to adhere to standard I/O given as part of project explanation.
       The output is also generated in same way. Program does not evaluate I/O any more than basic things.
    3. Weather is enum and is not considered as part of input so are suburbs.
    4. Vehicle preference is left to king to choose and not considered as part of vehicle.
    5. Magical numbers and special characters are hard coded as they do not, mostly, repeat across code files.
    6. Base context elements are not initialized via file.