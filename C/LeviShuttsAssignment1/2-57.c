#include <stdio.h>

typedef unsigned char *byte_pointer;

void show_bytes(byte_pointer start, int len){
	int i;
	for(i=0; i<len; i++)
		printf("%.2x", start[i]);
	printf("\n");
}

void show_short(short x){
	show_bytes((byte_pointer) &x, sizeof(short));
}

void show_long(long x){
	show_bytes((byte_pointer) &x, sizeof(long));
}

void show_double(double x){
	show_bytes((byte_pointer) &x, sizeof(double));
}

int main(){
	short s = 2;
	long l = 33333;
	double d = 6.9;
	show_short(s);
	show_long(l);
	show_double(d);
}