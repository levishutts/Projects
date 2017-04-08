#include <stdio.h>

int decode2(int x, int y, int z){
	int sub = y - z;
	sub = sub << 31;
	sub = sub >> 31;
	int mul = x * (y - z);
	return sub | mul;
}
	
int main(){
	int x;
	int y;
	int z;
	printf("Enter x: ");
	scanf("%d", &x);
	printf("Enter y: ");
	scanf("%d", &y);
	printf("Enter z: ");
	scanf("%d", &z);
	printf("docode2: %d", decode2(x, y, z));
}