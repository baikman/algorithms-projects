Project #5 <Raods Scholar>
CS 3410– Spring 2026
<Emmett Bicknell & Brandon Aikman>

# Requirements
Restate the problem specification, and any detailed requirements

Roads Scholar.

# Design
How did you attack the problem? What choices did you make in your design, and why? Show class diagrams for more complex designs.

Floyd-Warshall + Pi matrix implementation.

# Security Analysis
State the potential security vulnerabilities of your design. How could these vulnerabilities be exploited by an adversary? What would be the impact if the vulnerability is exploited?

There are no known security vulnerabilities. The program cannot run any terminal commands so nothing bad should be able to happen.

# Implementation
Outline any interesting implementation details.



# Testing
Explain how you tested your program, enumerating the tests if possible.
Explain why your test set was sufficient to believe that the software is working properly,
i.e., what were the range of possibilities of errors that you were testing for.

we should add a few extra test cases.

# AI Use
How did you use generative AI in this project?  Be specific!

We did not use generative AI on this project.

# Summary/Conclusion
Present your results. Did it work properly? Are there any limitations? If it is an analysis-type project, this section may be significantly longer than for a simple implementation-type project.

Add time analysis stuff here probably. Should be O(n^3) but can take note of space complexity maybe.

# Lessons Learned
List any lessons learned, especially in regards to AI use.

Utilizing known approaches to map to new problems (applying F-W) is useful - this also demonstrates the importance of formal education.

# Time Spent
Approximately how much time did you spend on this project?

I worked around 4 hours or so.

TEST CASES BELOW (remove before submit):
Gradel test case 1:
8 17 4
0 1 7.12
0 2 8.34
0 3 5.33
0 4 5.36
1 2 4.21
1 6 6.99
1 7 10.26
2 3 2.74
2 6 5.04
3 4 4.12
3 5 7.72
3 6 5.71
4 5 8.94
4 6 10.29
5 6 5.47
5 7 8.55
6 7 6.01
0 Allentown
1 Bobtown
6 Charlestown
7 Downville
3
0 3 2.17
3 2 0.45
4 3 3.14

Gradel test case 2:
2 1 1
0 1 10.0
1 Target
1
0 1 2.5

Gradel test case 3:
2 1 2
0 1 10.0
1 Target
0 Source
2
0 1 2.6
1 0 4.3

Gradel test case 4:
10 17 5
0 1 5.2
0 4 7.7
1 2 8.1
1 3 3.3
2 4 1.3
2 6 6.6
3 6 4.2
3 9 8.6
4 5 4.4
4 7 5.5
5 6 2.3
5 7 3.3
6 7 7.0
6 8 2.1
6 9 2.2
7 8 4.4
8 9 6.6
0 AA
3 BB
5 CC
7 DD
9 EE
4
7 5 1.0
7 4 1.0
3 6 1.0
0 4 1.0