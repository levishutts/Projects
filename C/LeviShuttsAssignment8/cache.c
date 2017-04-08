#include <stdio.h>
#include <stdlib.h>

typedef unsigned char *byte_pointer;

struct cache_block{
	unsigned char valid;
	unsigned int tag;
	unsigned int val;
};

struct cache_block cache[16];

void show_bytes(byte_pointer start, int len){
	int i;
	for(i = 0; i < len; i++){
		printf(" %.2x", start[i], "\n");
	}
	printf("\n");
}

unsigned int getTag(unsigned char address){
	//returns first 26 bits
	return address >> 6;
}

unsigned int getSet(unsigned char address){
	//returns middle 4 bits
	return (address << 26) >> 28;
}

unsigned int getOffset(unsigned char address){
	//returns last 2 bits
	return (address << 30) >> 30;
}

void write(int add, int val){
	int setw = getSet(add);
	int tagw = getTag(add);
	if(cache[setw].valid == 1){
		printf("evicting block - set: %d - tag: %d - valid: %d - value: ", setw, cache[setw].tag, cache[setw].valid);
		show_bytes((byte_pointer) &cache[setw].val, sizeof(cache[setw].val));
		printf("\n");
	}
	cache[setw].val = val;
	cache[setw].tag = tagw;
	cache[setw].valid = 1;
	printf("wrote set: %d - tag: %d - valid: %d - value: ", setw, tagw, cache[setw].valid);
	show_bytes((byte_pointer) &cache[setw].val, sizeof(cache[setw].val));
}

void read(int add){
	int setr = getSet(add);
	int tagr = getTag(add);
	int offsetr = getOffset(add);
	printf("looking for set: %d - tag: %d\n", setr, tagr);
	if(cache[setr].valid == 1){
		printf("found set: %d - tag: %d - valid: %d - value: ", setr, cache[setr].tag, cache[setr].valid);
		char valr = cache[setr].val >> offsetr*8;
		show_bytes((byte_pointer) &valr, sizeof(valr));
		printf("\n");
		if(cache[setr].tag == tagr){
			printf("hit\n");
		}else{
			printf("tags don't match - miss!\n");
		}
	}
	else{
		printf("no valid set found - miss!\n");
	}
}

void printCache(){
	int i;
	for(i = 0; i < 16; i ++){
		if(cache[i].valid == 1){
		printf("set: %d - tag: %d - valid: %d - value: ", i, cache[i].tag, cache[i].valid);
		show_bytes((byte_pointer) &cache[i].val, sizeof(cache[i].val));	
		}
	}
}

void main(){
	struct cache_block *cache = malloc(sizeof(struct cache_block) * 16);
	int done = 0;
	char rwpq;
	unsigned int add;
	unsigned int val;
	while(done != 1){
		printf("Enter 'r' for read, 'w' for write, 'p' to print, 'q' to quit: ");
		scanf(" %c", &rwpq);
		switch(rwpq){
			case 'r':
				printf("Enter 32-bit unsigned hex address: ");
				scanf(" %x", &add);
				read(add);
				printf("\n");
				break;
			case 'w':
				printf("Enter 32-bit unsigned hex address: ");
				scanf(" %x", &add);
				printf("Enter 32-bit unsigned hex value: ");
				scanf(" %x", &val);
				write(add, val);
				printf("\n");
				break;
			case 'p':
				printCache();
				break;
			case 'q':
				printf("Thank you, come again.\n");
				done = 1;
				break;
			default:
				printf("Unrecognised query. Try again.\n");
				break;
		}
	}
}