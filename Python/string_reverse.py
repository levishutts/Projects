"""
string_reverse.py: Recursive implementation of string_reverse(input string)
Authors: Levi Shutts

CIS 210 assignment 4, part 2, Fall 2015. 
"""
import argparse      # Used in main program to get PIN code from command line
from test_harness import testEQ  # Used in CIS 210 for test cases 

## Constants used by this program

def string_reverse(the_string):
    """
    This function reverses a string recursively
    """
    if the_string == "":
        return the_string
    return string_reverse(the_string[1:]) + the_string[0]

def run_tests():
    """
    This function runs a set of tests to help you debug your
    program as you develop it.
    """
    print("**** TESTING --- Check string reversal functionality")
    testEQ("'12345' -> '54321'", string_reverse('12345'), '54321')
    testEQ("'zyxwvut' -> 'tuvwxyz'", string_reverse('zyxwvut'), 'tuvwxyz')
    testEQ("'a string' -> 'gnirts a'", string_reverse('a string'), 'gnirts a')
    testEQ("'able was i ere i saw elba' -> 'able was i ere i saw elba'",
           string_reverse('able was i ere i saw elba'),
           'able was i ere i saw elba')
    print("*** End of provided test cases.  Add some of your own? ****")

def main():
    """
    Interaction if run from the command line.
    """
    parser = argparse.ArgumentParser(description="Recursive implementation of string_reverse()")
    parser.add_argument("string", type=str, help="String to reverse.")
    args = parser.parse_args()  # gets arguments from command line
    the_string = args.string
    print(string_reverse(the_string))

if __name__ == "__main__":
    # run_tests()  #FIXME: Comment this out when your program is working
    main()     #FIXME: Uncomment this when your program is working
