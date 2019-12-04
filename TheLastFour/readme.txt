Problem 2: The Last Four

Code structure:
    The solution is coded in java (1.8 compliant) and it is gradle based.
    The package named com.gt.cricket contains all the code. The base package itself contains
    the main class - LetTheChaseBegins. It has main method that takes the input file path to execute the code.
    The test case in TargetChaseTest.java can be run as integration test case.

    The test cases are in test dir which mimics the source package structure. Building the project with
    gradle executes the test cases. The directory test/resources contain various input files which contains
    the inputs for integration testing, while some output files are generated as part of execution of integration test case.

    To execute the project, build it with gradle and execute generated geektrust.jar file.


What is considered:
    1. As per response on slack, chosen option to record per per ball commentary and play it after final result & score card.
    2. Code treats Player as part of team while when player comes to bat he is treated as Batsman.
    3. See bottom for weighted Random number generation.
    4. As confirmed on slack, the player scoring probabilities are considered as local knowledge.
    5. The players are initialized (for main) from file, there is very little/no validation done so code expects it to be
        correct as per input constraint.


What is not considered:
    1. While result is tried to match closest to I/O samples, code does not do optimization on I/O creation and there may be
    some discrepancies.
    2. Plurality in I/O e.g. 1 run v/s 2 runs is ignored.
    3. Magical numbers and special characters are hard coded as they do not, mostly, repeat across code files.
    4. Win or lose of chase is not displayed as part of last of commentary.
    5. If not batted, scorecard just mentions DNB for that player


Weighted Random number generation to score run for batsman:
    Inverse transform sampling method is used (https://en.wikipedia.org/wiki/Inverse_transform_sampling)
    More also at: https://stackoverflow.com/questions/17250568/randomly-choosing-from-a-list-with-weighted-probabilities/17253335
