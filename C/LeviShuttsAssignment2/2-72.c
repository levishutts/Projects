#include <stdio.h>
#include <string.h>

// A) It is only checking unsigned ints which will always be greater than 0.

void copy_int(int val, void *buf, int maxbytes) {
	if (maxbytes >= sizeof(val)){
		printf("This works\n");
		memcpy(buf, (void *) &val, sizeof(val));
	}
	else (printf("Too small\n"));
} 

int main(){
	char array[24];
	copy_int(1, array, 0);
	copy_int(1, array, 3);
	copy_int(1, array, 4);
}