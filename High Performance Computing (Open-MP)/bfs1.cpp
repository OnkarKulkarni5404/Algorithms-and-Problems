#include<iostream>
#include<stdlib.h>
#include<queue>
#include "omp.h"
using namespace std;

struct node{
int data;
struct node * left=NULL;
struct node * right=NULL;
};

struct node * root=NULL;

void insert(int d)
{
	node * temp=new node();
	temp->data=d;
	node *p=root;

	if(root==NULL)
		root=temp;
	else
		while(1){
			if(temp->data<p->data){
				if(p->left==NULL){
					p->left=temp;
					break;
				}
				p=p->left;
			}
			else if(temp->data>p->data){
				if(p->right==NULL){
					p->right=temp;
					break;
				}
				p=p->right;
			}
			else
				break;
		}

}

void traverse(node *root)
{
	if(root==NULL)
		return;
	traverse(root->left);
	cout<<root->data<<"\t";
	traverse(root->right);
}

void bfs()
{
	queue<node *> q;
	q.push(root);
	int qsize;
	while(!q.empty())
	{
		qsize=q.size();
		#pragma omp parallel for
		for(int i=0;i<qsize;i++)
		{
			node * currnode;
			#pragma omp critical
			{
				currnode=q.front();
				q.pop();
				cout<<currnode->data<<"\t";
			}
			#pragma omp critical
			{
				if(currnode->left)
					q.push(currnode->left);
				if(currnode->right)
					q.push(currnode->right);
			}
		}

	}
}

int main()
{
	int t=0;
	for(int i=0;i<10;i++)
	{
		t=rand()%20;
		insert(t);
	}
	traverse(root);
	cout<<"\n";
	bfs();
	cout<<"\n";
	return 0;
}