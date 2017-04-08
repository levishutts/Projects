#include <stdlib.h>
#include <stdio.h>

typedef struct ELE *tree_ptr;

struct ELE {
	long val;
	tree_ptr left;
	tree_ptr right;
};

long trace(tree_ptr tp){
	long a = 0;
	while(tp != 0){
		a = tp -> val;
		tp = tp -> right;
	}
	return a;
}

int main(){
	tree_ptr tp1 = malloc(sizeof(struct ELE));
	(*tp1).val = 1;
	tree_ptr tp2 = malloc(sizeof(struct ELE));
	(*tp2).val = 9;
	(*tp2).left = tp1;
	(*tp1).right = tp2;
	
	printf("Should be 9: %x\n", trace(tp1));
}
// B)	trace takes a pointer and returns a node. If the pointer is 0, return 0.
//		Set the pointer to the next pointer to the right will return the pointer
//		furthest on the right.