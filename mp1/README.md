# CPEN 221 | Fall 2018 | MP1
# Design Document
#### **Team Sentiment Analyzers:**

- Pedram Amani - 73993008
- Rehan Hafeez - 47057666

---
- **You will need to complete the implementation of the `Document` class. 
How should one represent a `Document`? By representation, we refer to the 
private data that you will want to store in a `Document` object. 
The choice here should be sufficient for solving Tasks 1-3, and should 
avoid having to perform redundant operations.**

   _As attributes of the `Document` data type, apart from its `id` and `docURL`, we 
   will have an `int` total word count of the document and a `StringBuilder` object
   where the entire document will be stored._ 

   ######Our implementation:
   The `language` of the document was added as an attribute since the Azure Text 
   Analytics services required that a language be specified for each document 
   and it made sense to include this as an attribute. Also, the `overallSentiment` 
   was stored as an attribute to increase the efficiency of retrieving its value 
   by not having to calculate it per request. Furthermore, instead of storing the 
   entire text as a `StringBuilder`, more processing was done on the text initially 
   to store it as a `HashMap` from words to their counts and a `TextCollection` of 
   ~5000 character chunks of text. Since these data types are directly applicable to 
   our methods, having them as attributes increases efficiency.



- **What tasks should be performed when a new `Document` object is created?**

  _When creating an instance of `Document`, the attributes `id` and `docURL` will 
  be initialized. And the `Scanner` method will be used to store the text as a 
  `StringBuilder` object._
  
  ######Our implementation:
  The `id` and `docURL` and `language` attributes are initialized. The `decomposeText()`
  method is run to create the `HashMap` of words to their counts and a `TextCollection` 
  of text chunks. The document's word count is also computed and assigned to the 
  attribute. We also decided to run the `computeSentiment()` method at the time of 
  initialization and store sentiment score in the `overallSentiment` attribute. All 
  of these changes increase the initialization time of `Document` but will prevent 
  unnecessary recalculations of the same attribute.



- **How do you plan to implement the method to find the most similar documents? Provide 
a high-level description of your algorithm in English.**

  _We will use Java's `Scanner` to get each word in the document. For the words, 
  we create a `HashMap` with keys being the words and values being their number 
  of occurrence in the document. Having a `List` of `Document` objects, for each 
  document pair, if a key from one document's map does not appear in the other 
  document's map, create it and set its value to zero. Then, for each key, compute 
  the corresponding probabilities and sum over all keys to get the JSD.
  Then, create an `ArrayList` of document pairs and compute JSD for each pair. Sort 
  the document pairs and select the one with lowest JSD as the most similar pair._
  
  ######Our implementation:
  The major difference is in our algorithm for calculating the JSD for a document 
  pair. To do so, we create a local copy of the other document's `wordMap`; looping 
  through the keys of the other document, calculate the probability of word appearing 
  in the other document. If the key exists in our map, calculate the probability and if
  not, set our document's probability to zero. Furthermore, for keys that are contained 
  in both documents, remove the key from the local copy of other document's map.
  Then, loop through the keys of our document and do the same.
  Sum over all words to get the JSD score.
  

- **How will you test the correctness of your work? What are some test cases that 
you will use to validate your implementation?**

  1. _To test restructuring of document, convert document back into entire string 
  and test for equality with original document. Test for length as well._
  2. _To test word HashMap, have some correct data (maybe from a shorter document) of 
  word frequency. Check data is consistent with map._
  3. _To test key equality for 2 slightly different documents:_
      - _Modify one document very slightly_
      - _Put each document map into a set of keys (ignore values)_
      - _Test for equality using Java's assertEquals_
  4. _To test that the JSD of a document pair is correct, simply test the JSD computed 
  from our algorithm, and compare it with the known value._
  
  ######Our implementation:
  1. The initial first test case was out of scope and unnecessary; restructuring and 
  reconverting a document to a String is not relevant to the implementation of the 
  methods. Finding the most similar documents requires 
        - Calculating jsDiv scores
        - Comparing jsDiv scores
        - Calculating sentiment
        - Comparing sentiment
  2.	The tests included in the project compare known values with the ones computed 
  from our methods. Multiple tests with different source documents creates a spread that 
  tests the methods in different situations. 
        - testWordCount for example, checks the word count calculated from the scanner 
        loop by comparing it with an expected value calculated by running the text 
        through a document reader’s word count function. 
        - testGetID checks the getID function by comparing its return value with 
        the document IDs i.e. their names.
