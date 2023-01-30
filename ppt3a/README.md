CPEN 221 / Fall 2018

Programming Proficiency Test 3a
=========

## General Instructions

+ There is one question that you need to complete.
+ You have 78 minutes (1h 18m) to complete the tasks.
+ You will start at 3:02 p.m. and finish by 4:20 p.m.
+ Take your time to read the questions.
+ Skeleton code can be obtained by cloning this repository. 
+ Best of luck!

## Submission Instructions

+ Submit your work to the Github classroom repository that was created for your.
+ **Do not alter the directory/folder structure. You should retain the structure as in this repository.**
+ Do not wait until the last minute to push your work to Github. It is a good idea to push your work at intermediate points as well. _I would recommend that you get your Git and Github workflow set up at the start._

## Honour Code

By submitting your work to Github you agree to the following:

+ You did not consult with any other person in completing the examination.
+ You did not aid any other person in the class in completing their examination.
+ If you consulted any external sources, such as resources available on the World Wide Web, in completing the examination then you have cited the source. (You do not need to cite class notes or Sun/Oracle Java documentation.)
+ You are not aware of any infractions of the honour code for this examination.

> Violations of this honour code will be treated as a case of academic misconduct and will dealt with under UBC policies governing such issues. A consequence of this may be to nullify this exam for everyone that submits work for grading!

## Problem Statement: Escape Plan
> The skeleton source code for this question is in the package `thegreatescape`. You have to implement the required method in the class `EscapeRooms`.

You are employed by an escape room consortium and you develop this clever idea of constructing a dungeon based on a tree (presumably for those that commit "treegeon"). In this setup, there are `n` rooms, numbered `0` through `n-1`. The game is then setup thus: A player starts in Room 0, and needs a special, electronic key, to exit this room. This special key is in Room `n-1`. From Room `i`, a player can go to Room `j` if the two rooms are connected. But the door between two rooms may be locked so a player would need to find a fob that has one-time use to open a door. Any fob can open any door (except the exit door) but a fob can be used exactly once. A door stays open once it has been opened with a fob or if it was open to begin with. Fobs are strategically placed in rooms so that the player has a chance of finding a path to Room `n-1`.

Upper management becomes concerned that some placements of fobs may result in a player not being able to reach Room `n-1`. They would like you to place fobs in rooms such that, irrespective of the decisions made by a player, they will eventually find a way to Room `n-1`. 

Each room can contain no fob or one fob. A fob can open a door from any direction. Room `n-1` only contains the master key needed to exit, and there is exactly one door that leads to this room.

We can break the gameplay down using the following turn-based approach. In each turn, a player can do one of the following:

+ pick up a fob/key from the current room (if there is one);
+ open a door using a fob/key;
+ go from one room to another through an open door.

Clearly, a player cannot open a door without having a fob.

**In how many ways can keys be arranged in the dungeon such that a player is guaranteed to (eventually) find the way out?**

There are 2^(n-1) (2 to the power of n-1) arrangements possible because there are n-1 rooms that can have fobs, and each room can or cannot have a key. Which of these arrangements actually lead to an always escapable dungeon?

The input, which is the dungeon layout, is provided as a list of doors. A door connects two rooms, and is either locked or open initially. We can think of a datatype `Door` with three elements: two `int`s that represent the two rooms connected by the door, and a `boolean` value that indicates whether a door is locked or not. Given the layout, you need to determine the **number of key placements** that will lead to a player succeeding. The list of doors will have exactly `n-1` entries because that is what is needed to represent a tree.

### Examples

+ Suppose the dungeon has three rooms, `R0`, `R1` and `R2`. One would need to get to `R2`. `R0` is connected to `R1` and `R2`, but both doors are locked. Our player does not which room is `R2` until [s]he reaches the room. Now, `R0` has to have a fob otherwise it is impossible to reach `R1` or `R2`. If `R0` has a fob and the player uses it to open the door to `R1` then the player cannot reach `R2` if `R1` does not contain a fob (fobs are one-time use). Only `R0` and `R1` can contain fobs, and that gives us a total of 4 possible fob distributions. Of these 4 possible fob allocations, only one (when `R0` and `R1` contain fobs) allows a player to succeed eventually. So there is only one feasible fob distribution.
+ Suppose the dungeon only has two rooms, `R0` and `R1` and the door between `R0` and `R1` is open. In this case there are two possible fob allocations (fob in `R0`, no fob in `R0`) that will allow a player to succeed because they do not need a fob to reach `R1`.
+ In the scenario with only two rooms, if the door between the rooms is locked then the player will need a fob in `R0` to succeed leaving us with only one possible fob distribution.
+ Suppose we have a total of 10 rooms, and we have the following doors/connections:
    + `R0`-`R1`: locked
    + `R0`-`R4`: locked
    + `R0`-`R7`: locked
    + `R1`-`R2`: open
    + `R2`-`R3`: locked
    + `R4`-`R5`: locked
    + `R5`-`R6`: locked
    + `R7`-`R8`: locked
    + `R8`-`R9`: open

    The goal is to reach `R9` from `R0`. There are 8 ways to distribute fobs to ensure that a player can reach `R9` eventually. These possible solutions can be described at a high level as below:
    + (`R1` or `R2`) ✕ (`R8` or not `R8`) => 4 possibilities
    + (`R1` and `R2`) ✕ (`R4` or `R7`) ✕ (`R8` or not `R8`) => 4 possibilities

    Written in full, the possible solutions are (where asterisks indicate the presence of a key)

    | `R0` | `R1` | `R2` | `R3` | `R4` | `R5` | `R6` | `R7` | `R8` |
    |------|------|------|------|------|------|------|------|------|
    | *    | *    |      | *    |    * |    * |    * |    * |    * |
    | *    | *    |      | *    |    * |    * |    * |    * |      |
    | *    |      | *    | *    |    * |    * |    * |    * |    * |
    | *    |      | *    | *    |    * |    * |    * |    * |      |
    | *    | *    |      | *    |    * |    * |    * |      |    * |
    | *    | *    |      | *    |    * |    * |    * |      |      |
    | *    | *    |      | *    |      |    * |    * |    * |    * |
    | *    | *    |      | *    |      |    * |    * |    * |      |


## What Should You Implement / Guidelines

+ You should implement all the methods that are indicated with `TODO`.
+ Passing the provided tests is the minimum requirement. Use the tests to identify cases that need to be handled. Passing the provided tests is *not sufficient* to infer that your implementation is complete and that you will get full credit. Additional tests will be used to evaluate your work. The provided tests are to guide you.
+ You can implement additional helper methods if you need to but you should keep these methods `private` to the appropriate classes.
+ You do not need to implement new classes.
+ You can use additional standard Java libraries by importing them.
+ Do not throw new exceptions unless the specification for the method permits exceptions.

## Answers to FAQs

#### Can I consult Java documentation and other Internet-based sources?

Yes, you can. The point of this test is not to demonstrate mastery over syntax but that you can solve a problem in a reasonable amount of time with reasonable resources.

*If you find useful information online outside the official Java documentation and the course material, you must cite the source. You should do so by adding comments in your source code.*

Naturally you are expected to adhere to all of the course and UBC policies on academic integrity.

#### Why am I not guaranteed full credit if my implementation passes all the provided tests?

It is easy to develop an implementation that passes the provided tests and not much else. A good-faith implementation that passes all the provided tests is very likely to pass other tests too.
