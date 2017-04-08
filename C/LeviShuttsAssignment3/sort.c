#include <stdio.h>
#include <stdlib.h>

//Sorts the array in ascending	order using Bubble Sort.
int * BubbleSort(int *array, int length){
	int notSorted = 0;
	int swap;
	while(notSorted == 0){
		notSorted = 1;
		for(int i = 0; i < (length-1); i++){
			if(array[i] > array[i + 1]){
				swap = array[i];
				array[i] = array[i + 1];
				array[i + 1] = swap;
				notSorted = 0;
			}	
		}
	}
	return array;
}

int main(){
	
// Prompts the user	for	an integer array length.
// Uses	the	malloc() function to allocate the array.
	int length;
	printf("Enter an array length: \n");
	scanf("%d", &length);
	int* array = malloc(length * sizeof(int));
	
// Prompts the user	to enter an	integer	for	each array element.
	for(int i = 0; i < length; i++){
		int element;
		printf("Enter an element for array: \n");
		scanf("%d", &element);
		array[i] = element;
	}
	array = BubbleSort(array, length);
	
// Prints the sorted contents of the array.
	for(int i = 0; i < length; i++){
		printf("%d", array[i]);
	}
	printf("\n");
	
// Frees the memory	allocated for the array using the free() function.
	free(array);
}