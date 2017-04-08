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

void unrolledParallel(vec_ptr u, vec_ptr v, data_t *dest) {
	
	long int i;
	int length = vec_length(u);
	data_t *udata = get_vec_start(u);
	data_t *vdata = get_vec_start(v);
	data_t sum = (data_t) 0;
	long int limit = length - 3;
	data_t sum2 = (data_t) 0;
	data_t sum3 = (data_t) 0;
	data_t sum4 = (data_t) 0;

	for (i = 0; i < limit; i += 4) {
		sum = sum + udata[i] * vdata[i];
		sum2 = sum2 + udata[i+1] * vdata[i+1];
		sum3 = sum3 + udata[i+2] * vdata[i+2];
		sum4 = sum4 + udata[i+3] * vdata[i+3];
	}
	
	for (; i < length; i++) {
		sum = sum + udata[i] * vdata[i];
	}

	*dest = sum + sum2 + sum3 + sum4;
}

void populateArray(vec_ptr u) {
	for(int i = 0; i<VECTOR_SIZE; i++) {
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
		unrolledParallel(u, v, &sum);
	}
	
	struct timeval current;
	gettimeofday(&current, NULL);
	long long elapsed = (current.tv_sec - start.tv_sec)*1000000LL + (current.tv_usec - start.tv_usec);
	printf("Sum is %llu\n", sum);
	printf("%f time elapsed\n", elapsed/1000000.0);
}
