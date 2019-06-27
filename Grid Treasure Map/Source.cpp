#include<iostream>
#include<algorithm>
#include<vector>
#include<string>
#include<queue>
# define n 4
using namespace std;
class node {
public:
	int row;
	int col;
	int dist;
	node(int x, int y, int z) {
		row = x;
		col = y;
		dist = z;
	}
};



int main()
{
	char ar[4][4] = { {'o','o','o','o'},{'D','o','D','o'},{'o','o','o','o'},{'X','D','D','o'} };
	node source(0,0,0);
	bool visited[n][n];
	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < n; j++) {
			if (ar[i][j] == 'D')
				visited[i][j] = true;
			else
				visited[i][j] = false;
		}
	}
	queue<node> q;
	q.push(source);
	visited[source.row][source.col] = true;
	while (!q.empty()) {
		node p = q.front();
		q.pop();
		if (ar[p.row][p.col] == 'X')
		{
			printf("%d", p.dist);
			break;
		}
		if (p.row - 1 >= 0 && visited[p.row - 1][p.col] == false)
		{
			q.push(node(p.row-1,p.col,p.dist+1));
			visited[p.row - 1][p.col] = true;
		}
		if (p.row + 1 < n && visited[p.row + 1][p.col] == false)
		{
			q.push(node(p.row + 1, p.col, p.dist + 1));
			visited[p.row + 1][p.col] = true;
		}
		if (p.col - 1 >= 0 && visited[p.row ][p.col-1] == false)
		{
			q.push(node(p.row, p.col - 1, p.dist + 1));
			visited[p.row][p.col - 1] = true;
		}
		if (p.col + 1 < n && visited[p.row][p.col + 1] == false)
		{
			q.push(node(p.row, p.col + 1, p.dist + 1));
			visited[p.row][p.col + 1] = true;
		}

	}
	printf("this is it");
	system("Pause");

	return 0;
}