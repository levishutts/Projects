#include <stdio.h>

void replace_byte(int bit, int place, int replacement){
	unsigned int remover = 0xFF;
	place *= 8;
	remover <<= place;
	replacement <<= place;
	bit = bit & ~remover;
	bit = bit | replacement;
	printf("0x%X\n", bit);
}

int main(){
	unsigned int bit = 0x12345678;
	int place = 2;
	unsigned int replacement = 0xAB;
	replace_byte(bit, place, replacement);
}