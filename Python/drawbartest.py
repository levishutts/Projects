"""
draw_barcode.py: Draw barcode representing a ZIP code using Turtle graphics
Authors: FIXME: your name(s) here, as listed in Duckweb
Credits: FIXME: list sources and collaborators.  If none, delete this line

CIS 210 assignment 3, part 2, Fall 2015.
"""
import argparse	# Used in main program to obtain 5-digit ZIP code from command
                # line
import time	# Used in main program to pause program before exit
import turtle	# Used in your function to print the bar code

## Constants used by this program
SLEEP_TIME = 30	# number of seconds to sleep after drawing the barcode
ENCODINGS = [[1, 1, 0, 0, 0],	# encoding for '0'
             [0, 0, 0, 1, 1],	# encoding for '1'
             [0, 0, 1, 0, 1],   # encoding for '2'
             [0, 0, 1, 1, 0],	# encoding for '3'
             [0, 1, 0, 0, 1],	# encoding for '4'
             [0, 1, 0, 1, 0],	# encoding for '5'
             [0, 1, 1, 0, 0],	# encoding for '6'
             [1, 0, 0, 0, 1],	# encoding for '7'
             [1, 0, 0, 1, 0],	# encoding for '8'
             [1, 0, 1, 0, 0]	# encoding for '9'
            ]
SINGLE_LENGTH = 25	# length of a short bar, long bar is twice as long

def compute_check_digit(digits):
    """
    Compute the check digit for use in ZIP barcodes
    args:
        digits: list of 5 integers that make up zip code
    returns:
        check digit as an integer
    """
    sum = 0
    for i in range(len(digits)):
        sum = sum + digits[i]
    check_digit = 10 - (sum % 10)
    if (check_digit == 10):
        check_digit = 0
    return check_digit

"""
Need a good docstring here - tell us what this does
"""
def draw_bar(my_turtle, digit):
    my_turtle.left(90)
    if digit == 0:
        length = SINGLE_LENGTH
    else:
        length = 2 * SINGLE_LENGTH
    my_turtle.forward(length)
    my_turtle.up()
    my_turtle.backward(length)
    my_turtle.right(90)
    my_turtle.forward(10)
    my_turtle.down()

"""
Need a good docstring here
"""
def draw_zip(my_turtle, zip):
    # your code to draw the barcode corresponding to the zip (an integer)
    # replaces the following print statement
    print("My code to draw the barcode needs to replace this print statement")

def main():
    parser = argparse.ArgumentParser()
    parser.add_argument("ZIP", type=int)
    args = parser.parse_args()
    zip = args.ZIP
    if zip <= 0 or zip > 99999:
        print("zip must be > 0 and < 100000; you provided", zip)
    else:
        my_turtle = turtle.Turtle()
        draw_bar(my_turtle, 0)
        time.sleep(SLEEP_TIME)

if __name__ == "__main__":
    main()
