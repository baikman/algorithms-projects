Project #5 <Roads Scholar>
CS 3410– Spring 2026
<Emmett Bicknell & Brandon Aikman>

# Requirements
Restate the problem specification, and any detailed requirements

We were given a description of a graph that represents a road network. The nodes of the graph represent intersections of the roads, and certain intersections were specified as cities. Certain locations along some of the roads were designated as having road signs.
We were tasked with finding the shortest path from the intersections adjacent to the signs to the cities and determining if the shortest path included the road the sign was on. If it did include this road, the city should be listed on the sign, as well as the distance from the *sign* to the city.
We were recommended to use Floyd-Warshall for a simple approach to the problem.

Once we had determined what each sign should include, we were to outprint a formatted list of what each sign should include.

# Design
How did you attack the problem? What choices did you make in your design, and why? Show class diagrams for more complex designs.


We decided to use Floyd-Warshall to find the all-pairs shortest paths. This way we did not have to repeatedly do single-source shortest path algorithms, but rather could run one algorithm to calculate all of the distances. While doing this, we kept track of a predecessor (pi) matrix so that we could easily determine if a city should be included on a given sign. If B is the predecessor to A on the path from D to A, then road AB is part of the shortest path from A to D, which would mean that we would put D on a sign between A and B.

# Security Analysis
State the potential security vulnerabilities of your design. How could these vulnerabilities be exploited by an adversary? What would be the impact if the vulnerability is exploited?

There are no known security vulnerabilities. The program cannot run any terminal commands so nothing bad should be able to happen.

# Implementation
Outline any interesting implementation details.

We used a hash map to store the cities that would be put on each sign. Then, we could sort the distances to put the cities in the correct order by distance. Once the cities were ordered, we did a formatted print.

# Testing
Explain how you tested your program, enumerating the tests if possible.
Explain why your test set was sufficient to believe that the software is working properly,
i.e., what were the range of possibilities of errors that you were testing for.

We used the Gradel test cases. Since the test cases include a variety of sizes of problems with a variety of graph layouts, we believe that the test cases were sufficient.

# AI Use
How did you use generative AI in this project?  Be specific!

We did not use generative AI on this project.

# Summary/Conclusion
Present your results. Did it work properly? Are there any limitations? If it is an analysis-type project, this section may be significantly longer than for a simple implementation-type project.

Floyd-Warshall runs in O(n^3) time. The rest of the program was asymptotically faster than this, which means that the whole program runs in O(n^3) time. Our code does not explicitly sort ties alphabetically, but as long as the cities are inputted in alphabetical order, it will output ties alphabetically.

# Lessons Learned
List any lessons learned, especially in regards to AI use.

Utilizing known approaches to map to new problems (applying F-W) is useful - this also demonstrates the importance of formal education. If we had done this ourselves, without any guidance, we would have approached it much less efficiently. After learning algorithms in class, we were able to approach it in a much more effective manner.
We were reminded that it is important to initialize arrays if the values stored will be sorted. Before we did this, when we sorted our distance array, null values were put at the beginning, which messed everything up. Once we initialized the values to infinity, it worked as expected.

# Time Spent
Approximately how much time did you spend on this project?

Brandon worked around 4 hours or so. Emmett put in about an hour and a half.