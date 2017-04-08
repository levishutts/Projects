#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/time.h>
#define VECTOR_SIZE 8

typedef long long data_t;
typedef data_t *vec_ptr;

data_t * get_vec_start(vec_ptr v) {
	return v;
}

int vec_length(vec_ptr v) {
	return VECTOR_SIZE;
}

void inner4(vec_ptr u, vec_ptr v, data_t *dest) {
	int length = vec_length(u);
	data_t *udata = get_vec_start(u);
	data_t *vdata = get_vec_start(v);
	data_t sum = (data_t) 0;
	
	for (int i = 0; i < length; i++) {
		sum = sum + udata[i] * vdata[i];
	}
	
	*dest = sum;
}

void populateArray(vec_ptr u) {
	for(int i = 0; i < VECTOR_SIZE; i++) {
		u[i] = i;
	}
}

int main() {
	vec_ptr u = (vec_ptr) malloc(sizeof(data_t) * VECTOR_SIZE);
	vec_ptr v = (vec_ptr) malloc(sizeof(data_t) * VECTOR_SIZE);
	populateArray(u);
	populateArray(v);
	data_t sum;
	
	struct timeval start;
	gettimeofday(&start, NULL);
	
	for (int i = 0; i < 1; i++) {
		inner4(u, v, &sum);
	}
	
	struct timeval current;
	gettimeofday(&current, NULL);
	long long elapsed = (current.tv_sec - start.tv_sec)*1000000LL + (current.tv_usec - start.tv_usec);
	printf("Sum is %llu\n", sum);
	printf("%f time elapsed\n", elapsed/1000000.0);
}