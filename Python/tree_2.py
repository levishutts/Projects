# Passcode = Free BSD
import argparse

class Stack:
    """
    Create a data structure with stack (LIFO: last in, first out) property:
    """

    def __init__(self):
        """
        Using a list as the underlying container
        """
        self.li = []

    def push(self, item):
        """
        Adds item to the stack.
        """
        self.li.append(item)

    def pop(self):
        """
        Returns the last item added to the stack or the None object if the
        stack is empty.
        """
        if self.li.__len__() == 0:
            return None
        return self.li.pop(-1)

    def __bool__(self):
        """
        Boolean operator, returns true when stack is >not< empty
        """
        return self.li.__len__() > 0


def readTree(inputFile):
    """
    readTree reads the lines in the input file, and forms a tree using dictionaries.
    The result tree is a dictionary, whose keys are the indexes of nodes and the values of the
    dictionary are pairs of the value of nodes and the list of their children.
    For example tree[1] = (15, [2, 4]) means that node 1 has value 15 and it has two children with indexes of 2 and 4.
    Also, tree[2] = (20, [] ) means that node 2 has value 20, and it has no children (it is a leaf).
    """
    tree = {}
    for line in inputFile:
        items = line.split()
        id = int(items[0])
        value = int(items[1])
        childrenList = []
        if len(items) > 2:
            for it in items[2:]:
                child = int(it)
                childrenList.append(child)
        tree[id] = (value, childrenList)

    return tree


def printTree_itr(tree, index, level):
    """
    iterative function for a depth first tree traversal.

    prints the tree in the given format.
    index is the index of the current node in the tree
    and level is the level of the current node.
    """
    s = Stack()
    s.pop(index)
    
    while len(s) > 0:
        s.pop(Stack)
        value, children = tree[index]
        print(level, index, ":", value)
        s.push(Stack)


def printTree_rec(tree, index, level):
    """
    recursive function for a depth first tree traversal

    prints the tree in the given format.
    index is the index of the current node in the tree
    and level is the level of the current node.
    """
    if index not in tree:
        return
    value, children = tree[index]
    print(level, index, ":", value)
    for child in children:
        printTree_rec(tree, child, level + '-')


def printTree(tree, index, level):
    """
    helper function to call either the recursive or iterative functions
    """
    printTree_rec(tree, index, level)
    print()
    printTree_itr(tree, index, level)


def main():
    parser = argparse.ArgumentParser(description="Print a tree illustration of the given input data")
    parser.add_argument("treefile", type=argparse.FileType('r'), help="tree data file")
    args = parser.parse_args()
    tree = readTree(args.treefile)
    # We suppose that the root of the tree has index 1 and level 0.
    printTree(tree, 1, '')


if __name__ == "__main__":
    main()
