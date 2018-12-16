/*

You are given a 2D matrix of dimension  and a positive integer . You have to rotate the matrix  times and print the resultant matrix. Rotation should be in anti-clockwise direction.

Rotation of a  matrix is represented by the following figure. Note that in one rotation, you have to shift elements by one step only.

matrix-rotation

As an example rotate the Start matrix by 2:

Start         First           Second
 1 2 3 4        2  3  4  5      3  4  5  6
12 1 2 5  ->   1  2  3  6 ->   2  3  4  7
11 4 3 6      12  1  4  7       1  2  1  8
10 9 8 7      11 10  9  8     12 11 10  9

*/


#include<iostream>
#include<stdlib.h>
using namespace std;
int main()
{
	//enter m and n
	int n,m;
	cin>>n>>m;
	int mat[n][m];
	int p,temp,prev;

	//enter number of iterations
	cin>>p;
	int lc,ur,lr,rc;
	
	for(int i=0;i<n;i++)
		for(int j=0;j<m;j++)
			mat[i][j]=rand()%100;

		for(int i=0;i<n;i++){
		for(int j=0;j<m;j++)
		{
		cout<<mat[i][j]<<"\t" ;
		}
		cout<<endl;
	}



	for(int i=0;i<p;i++)
	{
		lc=0;ur=0;lr=n-1;rc=m-1;
	
		

		while(lc!=rc)
		{
		temp=mat[ur][rc];
		for(int j=rc-1;j>=lc;j--)
		{
			prev=mat[ur][j];
			mat[ur][j]=temp;
			temp=prev;
		}

		for(int j=ur+1;j<=lr;j++)
		{
			prev=mat[j][lc];
			mat[j][lc]=temp;
			temp=prev;
		}

		for(int j=lc+1;j<=rc;j++)
		{
			prev=mat[lr][j];
			mat[lr][j]=temp;
			temp=prev;
		}

		for(int j=lr-1;j>=ur;j--)
		{
			prev=mat[j][rc];
			mat[j][rc]=temp;
			temp=prev;
		}

		ur++;lc++;rc--;lr--;
		}
	}


	cout<<"\n\n";
	for(int i=0;i<n;i++){
		for(int j=0;j<m;j++)
		{
		cout<<mat[i][j]<<"\t" ;
		}
		cout<<endl;
	}


	return 0;
}

