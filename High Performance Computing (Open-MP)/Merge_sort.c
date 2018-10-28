#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include "omp.h"
#define max 1000

void generate_list(int *x,int n)
{
	for(int i=0;i<n;i++)
		x[i]=rand()%n;
}

void print_list(int *x,int n)
{
	for(int i=0;i<n;i++)
		printf("%d\t",x[i]);
}

void merge(int *x,int n,int *temp)
{
	int i=0;
	int j=n/2;
	int ti=0;

	while(i<n/2 && j<n){
		if(x[i]<x[j]){
			temp[ti]=x[i];
			ti++;
			i++;
		}
		else
		{
			temp[ti]=x[j];
			ti++;
			j++;
		}
	}
	while(i<n/2){
		temp[ti]=x[i];
		ti++; 
		i++;
	}
	while(j<n){
		temp[ti]=x[j];
		ti++;
		j++;
	}

	memcpy(x,temp,n*sizeof(int));
}

void merge_sort(int *x,int n,int *temp)
{
	if(n<2) return;

	#pragma omp task firstprivate(x,n,temp)
	merge_sort(x,n/2,temp);

	#pragma omp task firstprivate(x,n,temp)
	merge_sort(x+(n/2),n-(n/2),temp);

	#pragma omp taskwait

	merge(x,n,temp);
}

void main()
{
	int n=100;
	double start,stop;
	int data[max],tmp[max];
	generate_list(data,n);
	printf("\nThe list before Sorting is:");
	print_list(data,n);
	printf("\n");
	start=omp_get_wtime();
	#pragma omp parallel
	{
		#pragma omp single
		{
			merge_sort(data,n,tmp);
		}
	}
	stop=omp_get_wtime();
	printf("\n\n\n");
	print_list(data,n);
	printf("Time required is %g\n",stop-start);
}