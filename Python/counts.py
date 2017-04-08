"""
Counting Majors - Assignment 2, CIS 210
Count the number of occurrences of each major code in a file.
Authors: Levi Shutts, Syd Lynch

Input is a file in which major codes (e.g., "CIS", "UNDL", "GEOG")
appear one to a line. Output is a sequence of lines containing major code
and count, one per major.
"""
import argparse
def count_codes(majors_file):
    """
    Loops through a sorted list of majors derived from a text file,
    counts the frequency of a major using accumulation. Prints the major and
    its count, then moves on to next string.
    args:
        majors_file: a txt file to count majors within
    returns:
        major and the frequency it appears
    """
    majors = [ ]
    for line in majors_file:
        majors.append(line.strip())
    majors = sorted(majors)
    
    total_count = 0          # Accumulates the total amount of counts for all majors
    count = 0
    if len(majors) == 0:        # Accounts for empty txt file
        print('File is empty')
        return
    majors.append('End of list')      # Allows end of list to work with loop
    for major in majors:
        if majors[count] == majors[count + 1]:     # If first index string is same as the next, add one to count
            count += 1
        else:
            print(majors[count], ((count + 1) - total_count))     # If index str not equal, print that str and its count - the total count
            count += 1
            total_count = count
        if majors[count] == majors[-1]:     # If index str is the last in list (the dummy str), break the loop
            break
    
            
        

        
def main( ):
    """
    Interaction if run from the command line.
    Usage:  python3 counts.py  majors_code_file.txt
    """
    parser = argparse.ArgumentParser(description="Count major codes")
    parser.add_argument('majors', type=argparse.FileType('r'),
                        help="A text file containing major codes, one major code per line.")
    args = parser.parse_args()  # gets arguments from command line
    majors_file = args.majors
    count_codes(majors_file)
    
    
    
if __name__ == "__main__":
    main( )
