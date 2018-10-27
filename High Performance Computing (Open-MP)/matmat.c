#include<stdlib.h>
#include<stdio.h>
#include "omp.h"
 
#define rowsA 100
#define colsA 100
#define colsB 100

int main()
{
	int tid,nthreads,i,j,k,chunk;
	double  a[rowsA][colsA],
			b[colsA][colsB],
			c[rowsA][colsB],
			d[rowsA][colsB];
	chunk=10;

	//spawing a parallel region explicitly scoping all variables
	//parallel

	#pragma omp parallel shared(a,b,c,nthreads,chunk) private(tid,i,j,k)
	{
		tid=omp_get_thread_num();
		if(tid==0)
		{
			nthreads=omp_get_num_threads();
			printf("%d = number of threads in the system\n",nthreads );
		}
		#pragma omp for schedule(static,chunk)
		for(i=0;i<rowsA;i++)
			for(j=0;j<colsA;j++)
				a[i][j]=rand()%10;
		#pragma omp for schedule(static,chunk)
		for(i=0;i<colsA;i++)
			for(j=0;j<colsB;j++)
				b[i][j]=rand()%10;
		#pragma omp for schedule(static,chunk)
		for(i=0;i<rowsA;i++)
			for(j=0;j<colsB;j++)
				c[i][j]=0;
		#pragma omp for schedule(static,chunk)
		for(i=0;i<rowsA;i++)
			for(j=0;j<colsB;j++)
				d[i][j]=0;

		
			printf("Threads %d starting matrix multiply...\n",tid);
			#pragma omp for schedule(static, chunk)
			for( i=0;i<rowsA;i++)
			{
				printf("Thread=%d did row=%d\n",tid,i);
    			for(j=0;j<colsB;j++)
    			for(k=0;k<colsA;k++)
    				c[i][j]+=a[i][k]*b[k][j];
			}
	}
	//serial
	
    	
   	     for(i=0;i<rowsA;i++)
			{
				for(j=0;j<colsB;j++)
    			for(k=0;k<colsA;k++)
    				d[i][j]+=a[i][k]*b[k][j];
			}
	
		printf("Done\n");		

	}