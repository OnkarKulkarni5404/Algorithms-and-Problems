//multiplying matrix with vector
#include<stdio.h>
#include<stdlib.h>
#include "omp.h"
# define MAX 1000
void main()
{

int a[MAX][MAX];
int b[MAX];
int c[MAX];
int d[MAX];

for(unsigned int i=0;i<MAX;i++)
{
	b[i]=rand()%100;
	c[i]=0;
	d[i]=0;
for(unsigned int j=0;j<MAX;j++)
	a[i][j]=rand()%100;
}

//parallel
double start=omp_get_wtime();
# pragma omp parallel for schedule(dynamic,16)
for(unsigned int i=0;i<MAX;i++)
	for(unsigned int j=0;j<MAX;j++)
	{
		c[i]+=a[i][j]*b[j];
	}
double end=omp_get_wtime();
printf("Time required %f",end-start);

start=omp_get_wtime();
for(unsigned int i=0;i<MAX;i++)
	for(unsigned int j=0;j<MAX;j++)
	{
		d[i]+=a[i][j]*b[j];
	}
 end=omp_get_wtime();
printf("\nTime required %f",end-start);



}