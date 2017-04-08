import math
import argparse

def my_pi(terms):
    sum = 0
    for i in range(terms):
        sum = sum + (-1)**i / ((2*i+1) * 3**i)
    return sum * math.sqrt(12)

def main():
    parser = argparse.ArgumentParser()
    parser.add_argument("TERMS", type = int)
    args = parser.parse_args()
    terms = args.TERMS
    approx_pi = my_pi(terms)
    print('Approximation for pi after summing', terms, 'terms is', approx_pi)
    difference = math.fabs(approx_pi - math.pi)
    print('Percentage error relative to math.pi is', :.5%.format(difference/math.pi))

if __name__ == "__main__":
    main()
