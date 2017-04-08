"""
drawflower.py:  Draw flower from multiple squares using Turtle graphics
Authors: Levi Shutts

CIS 210 assignment 1, Fall 2015. 
"""
import argparse      # Used in main program to get numSquares and sideLength
                     # from command line
import time          # Used in main program to pause program before exit
import turtle        # using turtle graphics

## Constants used by this program

#drawSquare function from page 34 of Miller and Ranum
def drawSquare(myTurtle, sideLength):
    for i in range(4):
        myTurtle.forward(sideLength)
        myTurtle.right(90)

def drawFlower(myTurtle, numSquares, sideLength):
    """
    Draw flower shape using squares
    args:
        myTurtle:  Turtle object to draw with
        numSquares: number of squares to use for flower
        sideLength: length of a side for each square
    returns:
        nothing, draws flower in the Turtle graphics window
    """
    for i in range(numSquares):
        drawSquare(myTurtle, sideLength)
        myTurtle.right(360 / numSquares)

def main():
    """
    Interaction if run from the command line.
    Magic for now; we'll look at what's going on here
    in the next week or two. 
    """
    parser = argparse.ArgumentParser(description="Draw flower using squares")
    parser.add_argument("numSquares", type=int, 
                        help="number of squares to use (an integer)")
    parser.add_argument("sideLength", type=int, 
                        help="length of side for each square (an integer)")
    args = parser.parse_args()  # gets arguments from command line
    numSquares = args.numSquares
    sideLength = args.sideLength
    myTurtle = turtle.Turtle()
    drawFlower(myTurtle, numSquares, sideLength)
    time.sleep(10)              # delay for 10 seconds

if __name__ == "__main__":
    main()


