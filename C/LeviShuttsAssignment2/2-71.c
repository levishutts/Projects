#include <stdio.h>

typedef unsigned packed_t;

// A) The shifts gets rid of the leading bits, yet only puts 0's on the front.

int xbyte(packed_t word, int bytenum)
{
	int sig_byte = (word<<(24-(bytenum<<3)));
	return sig_byte>>24;
}

int main(){
	printf("0x%X\n", xbyte(0x11223344, 0));
	printf("0x%X\n", xbyte(0x11223344, 2));
	printf("0x%X\n", xbyte(0xAABBCCDD, 0));
	printf("0x%X\n", xbyte(0xAABBCCDD, 2));
}