
import java.util.PriorityQueue;


class node{
    public int no;
    public int heu;
    public int pathmat[];
    public int pre;
    node(){}
    node(int n,int h){
    no=n;
    heu=h;
    pathmat=new int[9];
    }
}
class entry implements Comparable<entry>
{
    int cost,source,destination;
    entry(){}
    entry(int c,int s,int d){
       cost=c;
       source=s;
       destination=d;
    }
    
    @Override
    public int compareTo(entry o) {
        return Integer.compare(this.cost, o.cost);
    }
}


public class bfs {

public static void display(node n)
{
        System.out.println("Heuristic Value for node "+n.no+"is "+n.heu+"\n");
        for(int j=0;j<n.pathmat.length;j++)
            System.out.print(n.pathmat[j]+"\t");
        System.out.println();
}
    
    
    
public static void main(String args[])
{
    int pathmat[][]={{0,1,1,1,0,0,0,0,0},{0,0,0,0,0,0,1,0,0},{0,0,0,0,0,0,0,1,0},{0,0,0,0,1,1,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,1},{0,0,0,0,0,0,0,0,0}};
    int heuarr[]={0,4,5,3,7,8,5,2,1};
    node nobj[]=new node[9];
    int n=9;
    for(int i=0;i<n;i++)
    {
        nobj[i]=new node(i,heuarr[i]);
        for(int j=0;j<n;j++)
            nobj[i].pathmat[j]=pathmat[i][j];
         display(nobj[i]);  
    }
    System.out.println("--------------------------------------------------------");
    System.out.println("-------------------Calculating BFS----------------------");
    
    int start=0;
    int goal=n-1;
   
    PriorityQueue<entry> q=new PriorityQueue();
    while(start!=goal)
    {
        //finding min heu from start
        for(int i=0;i<n;i++)
            if(pathmat[start][i]==1)
                    q.add(new entry(nobj[i].heu,start,i));
       
        entry e=q.poll();
        start=nobj[e.destination].no;
        nobj[start].pre=e.source;
    }
    System.out.print(nobj[goal].no);
    while(goal!=0)
    {
        goal=nobj[goal].pre;
        System.out.print("-"+goal);
    }
    System.out.println("\nEND");
           
}
}
