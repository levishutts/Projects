#include <stdio.h>

#define M 13

typedef int MArray_t[M][M];

void transpose(MArray_t A){
	
	int *col_init = &A[0][0];
	
	for(int i = 0; i < M; ++i){
		for(int j = 0; j < i; j++){
			int *row = col_init + (i * M + j);
			int *col = col_init + (i * M + j);
			int temp = *row;
			*row = *col;
			*col = temp;
		}
	}
}

void printArray(MArray_t A){
	for(int i = 0; i < M; ++i){
		for(int j = 0; j < M; j++){
			printf("%d ", A[i][j]);
		}
		printf("\n");
	}
	printf("\n");	
}

void main(){
	MArray_t a = {{1,2,3,4,5,6,7,8,9,10,11,12,13},
				  {1,2,3,4,5,6,7,8,9,10,11,12,13},
				  {1,2,3,4,5,6,7,8,9,10,11,12,13},
				  {1,2,3,4,5,6,7,8,9,10,11,12,13},
				  {1,2,3,4,5,6,7,8,9,10,11,12,13},
				  {1,2,3,4,5,6,7,8,9,10,11,12,13},
				  {1,2,3,4,5,6,7,8,9,10,11,12,13},
				  {1,2,3,4,5,6,7,8,9,10,11,12,13},
				  {1,2,3,4,5,6,7,8,9,10,11,12,13},
				  {1,2,3,4,5,6,7,8,9,10,11,12,13},
				  {1,2,3,4,5,6,7,8,9,10,11,12,13},
				  {1,2,3,4,5,6,7,8,9,10,11,12,13},
				  {1,2,3,4,5,6,7,8,9,10,11,12,13},
				  };
				  
	printArray(a);
	transpose(a);
	printArray(a);
}