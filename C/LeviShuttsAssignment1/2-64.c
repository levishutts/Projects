#include <stdio.h>

int any_odd_one(unsigned x){
	// Creates a mask with 0's in the even positions
    unsigned int mask = 0xaaaaaaaa;
	// Masks x checks if it is not equal to 0
    return (x & mask) != 0;
}

int main(){
	unsigned int a = 0x0;
	unsigned int b = 0x1;
	unsigned int c = 0x2;
	unsigned int d = 0x3;
	unsigned int e = 0xffffffff;
	unsigned int f = 0x55555555;
	printf("%X\n", any_odd_one(a));
	printf("%X\n", any_odd_one(b));
	printf("%X\n", any_odd_one(c));
	printf("%X\n", any_odd_one(d));
	printf("%X\n", any_odd_one(e));
	printf("%X\n", any_odd_one(f));
}