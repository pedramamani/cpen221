CPEN 221

# Killing Cells and Stunting Development

A zygote is the first reproductive cell for an organism. When an organism is developing, each cell splits into two new cells or does not split at all. For mature organisms, cells do not split any further.

During an organism’s development, let us assign numbers to cells as they emerge. The zygote will be cell 0, and it may split into cells 1 and 2. Cell 2 may not split any further, but Cell 1 may split into Cell 3 and Cell 4. Cell 3 and Cell 4 are, like Cell 2, terminal cells that do not divide any further. This type of organism will thus have exactly three cells (Cells 2, 3 and 4).

If, for any reason, a cell is killed during the development process then it will be absent in the mature form of the organism/species. If a cell that splits is killed then, naturally, the descendants of that cell will be missing in the mature organism.

In the example above, if Cell 3 is killed then the mature organism has Cells 2 and 4. If Cell 1 is eliminated then the mature organism only has Cell 2.
 
Essentially, this evolutionary process is described by a binary tree. Suppose you are given an array encoding of this binary tree: there is an array of integers, `int[] evolutionaryTree`, that describes the evolution of the organism. In this array, the i<sup>th</sup> element, `evolutionaryTree[i]`, represents the parent cell of *Cell i* in the evolutionary process. Predictably, the array entry for the zygote is `-1` because the zygote has no parent. Note that the zygote need not be Cell 0. *The numbering can be arbitrary.*

+ Suppose you were given the `evolutionaryTree` array and the index of a cell that dies prematurely during the evolutionary process then you have to compute the number of cells that can be found in the mature form of the organism.
+ Similar to the case above, instead of simply counting the number of surviving cells in the mature organism, return a `Set<Integer>` with the cells that survive.
