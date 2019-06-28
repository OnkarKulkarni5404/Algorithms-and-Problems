#include<iostream>
#include<map>
#include<tgmath.h>
using namespace std;
#define size1 6
double dist(int m, int n, int p, int q)
{
	return sqrt(((m - p) * (m - p) + (n - q) * (n - q)));

}



int main()
{
	multimap<double, int, greater <double> > mymap;
	int ar[6][2] = { {-16, 5},{-1, 2},{4, 3},{10, -2},{0, 3},{-5, -9}};
	int ptx = 0, pty = 0;
	double k ;

	
	for (int i = 0; i < size1; i++)
	{
		k=dist(ptx, pty, ar[i][0], ar[i][1]);
		
		mymap.insert(make_pair(k, i));
	}
	multimap<double, int>::iterator it;
	int count=3;
	for (it = mymap.end(); it!=mymap.begin(); it--)
	{
		count--;
		cout<<ar[(*it).second][0]<<"\t"<<ar[(*it).second][1]<<"\t";
		cout << (*it).first << endl;
	}

	return 0;
}