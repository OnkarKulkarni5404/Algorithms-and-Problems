#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;
int main()
{
	int d = 250, L, R, res = 0, n = 7;
	vector<int> v;
	v.push_back(90);
	v.push_back(85);
	v.push_back(75);
	v.push_back(60);
	v.push_back(120);
	v.push_back(150);
	v.push_back(125);

	for (int i = 0; i < n - 1; i++)
	{
		for (int j = 1; j < n; j++)
		{
			if (v[i] + v[j]<(d - 30) && v[i] + v[j]>res)
			{
				L = i;
				R = j;
				res = v[i] + v[j];
			}
		}
	}

	cout << v[L]<<"\t"<<v[R]<<"\t"<<res<<endl;




	system("Pause");
	return 0;
}