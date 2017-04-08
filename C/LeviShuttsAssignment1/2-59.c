#include <stdio.h>

unsigned int least_sig(int x, int y){
	//Masks the last 8 bits of y and the first 24 of x, then combines them
	return (y & 0xFFFFFF00) | (x & 0xFF);
}

int main(){
	unsigned int x = 0x89ABCDEF;
	unsigned int y = 0x76543210;
	printf("0x%X\n", least_sig(x, y));
}