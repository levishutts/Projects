'''
alphacode.py:  Convert code to mnemonic alphabetic code
Author: Levi Shutts

CIS 210 assignment 1, Fall 
'''
import argparse      # Used in main program to get PIN code from command line
from test_harness import testEQ  # Used in CIS 210 for test cases 

'''
encode function takes the last two digits of PIN then:
    1) divides by five with no remainder to return a value (0 - 19).
    This value selects a letter out of the consonants list.
    
    2) divides by five and returns the remainder to return a value (0 - 4).
    This value selects a letter out of the vowels list.
'''

def encode(pin):
    two_dig = pin % 100
    con = consonants[two_dig // 5]
    vow = vowels[two_dig % 5]

    pair = con + vow
    
    return pair

# String of consonants
consonants = "bcdfghjklmnpqrstvwyz"
# String of vowels
vowels = "aeiou"
# Empty list to combine and rearrange results from encode function
fin = []
parser = argparse.ArgumentParser(description="Create mnemonic for PIN code")
parser.add_argument("PIN", type=int, 
                    help="personal identifier number (an integer)")
args = parser.parse_args()  # gets arguments from command line
pin = args.PIN
# Empty string used turn 'fin' list into a  string
mnemonic = ""
# Keeps original PIN# for preinting result
orig_pin = pin

'''
While loop puts PIN# through encode function until all numbers are encoded.
'''
while pin > 0:

    fin.append(encode(pin))

    pin = pin // 100

# Reverses order of the pair of letters to correspond with given PIN#
fin.reverse()

# Turns 'fin' list into 'mnemonic' string
mnemonic = ''.join(fin)

if orig_pin <= 0:
    print("Not a valid PIN#")

else:
    print("Encoding of ", orig_pin, " is ", mnemonic)
