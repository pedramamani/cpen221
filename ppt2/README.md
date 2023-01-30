**Email and Mailboxes**
# CPEN 221 - Fall 2018 - PPT #2

## Problem Description
An email message can be represented as a datatype that encapsulates information about the email:

* message id: a **unique** identifier for the message
* sender: text that represents the sender of the email
* receiver: text that represents receiver of the email
* timestamp: a positive integer that allows us to sequence/order email
* subjiokelct: text representing the email's subject
* body: text that forms the email body
* response-to id: the identifier of the email that an email is written in response too. (Newly generated email will use a special identifier to indicate that there is no prior email.)

You have been provide with the implementation of the email datatype (in `email.Email`). `Email` is an immutable type. You should **not change** the provided implementation of `Email`. Also, two instances of `Email`, `m1` and `m2` are duplicates of each other if `m1.equals(m2)` is true.

You have to implement a `MailBox` datatype that is a mutable collection of email with the following operations:

* `addMsg`: to add a new message to the `MailBox`
* `delMsg`: to delete/remove a message from the `MailBox`
* `getTimestampView`: to obtain a list of messages in the `MailBox` sorted using the timestamp, with newer messages appearing before older messages
* `getThreadedView`: to obtain a list of messages in the `MailBox` in a **threaded** view that we will discuss in more detail next.

The skeleton code in `email.MailBox` provides specs for the methods that you need to implement.

### Email Threads

We define two emails, `m1` and `m2`, to be part of the same email thread if:

* `m2` is a direct response to `m1`, 
* or `m2` was written in response to any message that is part of the same thread as `m1`.

Clearly the definition above holds if we swap `m1` and `m2`. A message created fresh (not a response to another email) is considered to be a separate thread that may or may not grow.

**Examples of Email Threads**

* `m1` is a new email message and it gets an id of `id1`. `m1` is in an email thread that contains only itself.
* `m2` is an email message with id `id2` created in response to `m1`. `m2` will have a response-to id of `id1`. Now `m1` and `m2` are part of one email thread.
* An email `m3` created in response to `m2` will be part of the thread that contains `m1`, `m2` and `m3`.
* An email `m4`, different from `m2`, could be created in response to `m1` and then `m1`, `m2`, `m3` and `m4` are part of the same thread.

### Threaded Email View

A `MailBox` may consist of many email threads. The most recent email thread will be defined as the one that has the most recent email (i.e., the email with the highest timestamp). In the threaded email view, the messages are sequenced by threads with the most recent thread appearing first in the list. Within a thread, emails are also sorted by timestamps with the most recent email appearing first.

Suppose a `MailBox` has messages `m1`, `m11`, `m17` belonging to one thread and messages `n2`, `n3`, `n11` belonging to a different thread. Here we use the numbers to denote the timestamps of the messages. The threaded email view of this `MailBox` is the list `m17, m11, m1, n11, n3, n2`. (The timestamp view of this mailbox can be either `m17, m11, n11, n3, n2, m1` or `m17, n11, m11, n3, n2, m1`.)

### Returning `List`s

The `MailBox.getTimestampView` and the `MailBox.getThreadedView` operations return references to objects with type `List<Email>`. Changes to these lists should not affect the `MailBox` that they present a view of. The `Email.clone` method may be of use.

`MailBox` is mutable but should be mutated only via the operations that are indicated by the public interface.

### Sorting Email

You will need to sort instances of `Email` to achieve the objectives of this PPT. To help you with this effort, a `email.TimestampComparator` class has been provided along with a test case in `email.ComparatorTest` to illustrate how such comparators can be used. 

You can add to `email.TimestampComparator` to complete your work. The provided test case in `email.ComparatorTest` should not be changed and should work even after you complete your work.

You may also want to take note of the fact that `Email` implements the `TimestampedObject` interface.

## Logistics

**Duration**

You have 72 minutes to complete this task.

**Partial Credit**

If you did not succeed in the first PPT then:
if you complete all the methods except `email.MailBox.getThreadedView` then you will be awarded 0.75 points. Your code must still compile although tests for `email.MailBox.getThreadedView` may fail. If you do get 0.75 points because of this clause then you will not be awarded an extra 0.75 points later for passing 75% of the test cases in two PPTs but not succeeding in all three PPTs. 

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
