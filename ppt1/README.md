**Wanderings and Gatherings**
# CPEN 221 - Fall 2018 - PPT #1

Many CPEN 221 students in an alternative universe decide that they have to decompress by participating in a club crawl on Halloween. (The alternative universe is not that dissimilar to our own.) All the bars, pubs, clubs that they want to visit are on the same street called Broadway and these locations are all simply identified by their door number, which is an integer (Bar 41, Club 77, etc.) The students live in different apartments that are all on University Avenue, which is parallel to Broadway. Their apartments are also easily identified by integers (22 University Ave., 89 University Ave., etc.).

Students visit different establishments on Broadway and, after an evening of much-needed celebration, walk back to their apartments. They want to head back home along the shortest path, which is simply the line from the Broadway establishment to their apartment, so that they can prepare for their proficiency test. But, since shared sorrow is half the sorrow, they stop to chat and share a kombucha (or beer, as your preference may be) with every peer they meet on the way back. Of course, as luck would have it, **each student meets every other student they have a chance of meeting along their paths back to their apartments.**

If **every pair of students** that meet on the way back to University Avenue shares a bottle of kombucha, how many bottles are consumed on the way home? Who consumes the most? Students share drinks only if they meet on the way. Leaving the same location on Broadway or sharing an apartment do not count as a meeting on the way back.

You are given a list of source-destination pairs (`SDPair`) where the source is the location on Broadway and the destination is the apartment number on University Avenue. Each source-destination pair corresponds to one student's path, which is essentially the straight line from source to destination. Further, the ordering of buildings is identical on the two streets/roads. (You can essentially think of two roads as two number lines.)

![An `SDPair`](img/ppt1.png)

You will need to implement two methods: 
1. `Halloween.bottleCount()` that returns the number of bottles consumed;
2. `Halloween.highPoint()` that returns the index in the list that corresponds to the student that consumes the maximum amount of liquids on the way home.

Both methods about take as input a `List<SDPair>` and return an `int`. Ties are broken by returning the lower value that satisfies the required condition.

You are provided the implementation of `SDPair`, which you **should not change**.

## Logistics

**Duration**

You have 72 minutes to complete this task.

**Submission Instructions**

+ Submit your work to the Github classroom repository that was created for you.
+ **Do not alter the directory/folder structure. You should retain the structure as in this repository.**
+ Do not wait until the last minute to push your work to Github. It is a good idea to push your work at intermediate points as well. _I would recommend that you get your Git and Github workflow set up at the start._

**What Should You Implement / Guidelines**

+ You should implement all the methods that are indicated with `TODO`.
+ Passing the provided tests is the minimum requirement. Use the tests to identify cases that need to be handled. Passing the provided tests is *not sufficient* to infer that your implementation is complete and that you will get full credit. Additional tests will be used to evaluate your work. The provided tests are to guide you.
+ You can implement additional helper methods if you need to but you should keep these methods `private` to the appropriate classes.
+ You do not need to implement new classes.
+ You can use additional standard Java libraries by importing them.
+ Do not throw new exceptions unless the specification for the method permits exceptions.


## Honour Code

By submitting your work to Github you agree to the following:

+ You did not consult with any other person for the purpose of completing this activity.
+ You did not aid any other person in the class in completing their activity.
+ If you consulted any external sources, such as resources available on the World Wide Web, in completing the examination then you have cited the source. (You do not need to cite class notes or Sun/Oracle Java documentation.)
+ You are not aware of any infractions of the honour code for this examination.

## Answers to FAQs

* **Can I consult Java documentation and other Internet-based sources?**

    Yes, you can. The point of this test is not to demonstrate mastery over syntax but that you can solve a problem in a    reasonable amount of time with reasonable resources.

    *If you find useful information online outside the official Java documentation and the course material, you must cite the source. You should do so by adding comments in your source code.*

    Naturally you are expected to adhere to all of the course and UBC policies on academic integrity.

* **Isn't one hour too short to produce a working implementation?**

    The questions are straightforward, and these are not very different from what one might sometimes encounter on a job interview (for example). The difference is that you get less time during an interview (10-15 minutes) with no access to additional resources. So the time allotted is reasonable in that regard and I am expecting that everyone will be able to clear this bar. The goal is that it is possible to say, at a minimal level, what everyone who completes this course can achieve.

* **Why am I not guaranteed full credit if my implementation passes all the provided tests?**

    It is easy to develop an implementation that passes the provided tests and not much else. A good-faith implementation that passes all the provided tests is very likely to pass other tests too.
