#include<stdio.h>
#include<stdlib.h>
#include "omp.h"
#define max 1000

void main()
{
	int id,size;
	int i,*A,*B,*C;

	//allocating space dynamically

	A=(int *)malloc(max*sizeof(int));
	B=(int *)malloc(max*sizeof(int));
	C=(int *)malloc(max*sizeof(int));

	for(unsigned int i=0;i<max;i++){
		A[i]=rand()%100;
		B[i]=rand()%100;
		C[i]=0;
	}

	double start=omp_get_wtime();
	#pragma omp parallel for default(none) shared(A,B,C)
	for(i=0;i<max;i++)
	{
		printf("%d thread  is executing row %d\n",omp_get_thread_num(),i);
		C[i]=A[i]+B[i];
	}
	double end=omp_get_wtime();
	printf("Total time required is %f",end-start);

	printf("\nDone");

}