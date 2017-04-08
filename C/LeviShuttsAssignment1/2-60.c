#include <stdio.h>

unsigned int replace_byte(unsigned int bit, int place, unsigned int replacement){
	//The mask for the bit
	unsigned int remover = 0xFF;
	//Shifts the mask and replacement to the right position,
	//then combines the bit and replacement
	return (bit & ~(remover <<= place * 8)) | (replacement <<= place * 8);
}

int main(){
	unsigned int bit = 0x12345678;
	int place = 2;
	unsigned int replacement = 0xAB;
	printf("0x%X\n", replace_byte(bit, place, replacement));
}