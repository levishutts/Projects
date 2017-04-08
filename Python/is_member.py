"""
is_member.py: Recursive implementation of is_member() on a set
              represented by a sorted list of integers
Authors: Syd Lynch, Levi Shutts

CIS 210 assignment 4, part 1, Fall 2015. 
"""
import argparse      # Used in main program to get PIN code from command line
from test_harness import testEQ  # Used in CIS 210 for test cases 

def gen_set(set_file):
    """
    Reads a text file and loops through its contents, converting
    each string to an integer then appending to the_set list. 
    Sorts the list.
    args:
        set_file: a text file containing numbers
    returns:
        a sorted list of integers
    """
    the_set = []
    for num in set_file:
        num = int(num)
        the_set.append(num)
    the_set.sort()
    return the_set

def is_member(set, number):
    """
    Determines whether a provided integer is within a set
    using recursion.
    args:
        set: the returned list from gen_set function
    returns:
        True or False
    """
    if len(set) == 0:
        return False
    length = len(set) // 2
    if number == set[length]:
        return True
    if len(set) == 1:
        return False
    if number > set[length]:
        set = set[length:]
        return is_member(set, number)
    if number < set[length]:
        set = set[0:length]
        return is_member(set, number)

def run_tests():
    """
    This function runs a set of tests to help you debug your
    program as you develop it.
    """
    l = [-27, -12, -5, -1, 0, 2, 3, 6, 8, 10, 13, 25, 46, 99]
    print("**** TESTING --- Check membership of locally-defined set")
    print(l)
    testEQ("-99 is False", is_member(l, -99), False)
    testEQ("115 is False", is_member(l, 115), False)
    testEQ("-27 is True", is_member(l, -27), True)
    testEQ("99 is True", is_member(l, 99), True)
    testEQ("0 is True", is_member(l, 0), True)
    testEQ("-4 is False", is_member(l, -4), False)
    testEQ("14 is False", is_member(l, 14), False)
    print("*** End of provided test cases.  Add some of your own? ****")

def main():
    """
    Interaction if run from the command line.
    """
    parser = argparse.ArgumentParser(description="Recursive implementation of is_member()")
    parser.add_argument("set", type=argparse.FileType('r'),
                        help="A text file containing set elements, one per line.")
    parser.add_argument("number", type=int, help="number to check for membership")
    args = parser.parse_args()  # gets arguments from command line
    set_file = args.set
    number = args.number
    the_set = gen_set(set_file)
    if is_member(the_set, number):
        print(number, "is an element of the set")
    else:
        print(number, "is not an element of the set")

if __name__ == "__main__":
    # run_tests()  #FIXME: Comment this out when your program is working
    main()     #FIXME: Uncomment this when your program is working
