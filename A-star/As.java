/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Onkar
 */
import java.util.*;
class Entry implements Comparable<Entry>{
   public int source,destination;
   public int cos;
 
 Entry(){}
 Entry(int c,int s,int d)
 {
    
     source=s;
     destination=d;
     cos=c;
 }
@Override
    public int compareTo(Entry other) {
            return Integer.compare(this.cos, other.cos);
        //return this.cost().compareTo(other.cost());
    }}
    
class Node{
int no,heu,pre,mylen;
int closed[];
int ispath[];
//28/10
int costarr[];
ArrayList<Integer> canbe;
Node()
    {}
Node(int n,int h,int mat[] )
    {
        mylen=0;
        no=n;
        heu=h;
        closed=new int[5];
        ispath=new int[mat.length];
        initpath(mat);
    
        canbe=new ArrayList<>();
        costarr=new int[5];
    }
void Display()
    {
    System.out.println(this.no+"-"+this.heu);
    }

int checkOpen()
{
  
    int ans=0;
  //  System.out.print(this.no+" ");
    for(int i=0;i<ispath.length;i++){
        ans+=ispath[i];
   
    //System.out.print(ispath[i]+"\t");
    }
    //System.out.println();
    return ans;
}
void initpath(int mat[])
{
    for(int i=0;i<mat.length;i++)
        if(mat[i]!=999)
            ispath[i]=1;
        else
            ispath[i]=0;
}

}
public class As {
 public static Scanner s=new Scanner(System.in);
 public static void main(String args[]) 
 {
     //hardcoded declaration
     int noofnodes=5;
     int arrofheu[]={7,6,2,1,0};
     Node node[]=new Node[5];
     int pathmat[][]={{999,1,4,999,999},{999,999,2,5,12},{999,999,999,2,999},{999,999,999,999,3},{999,999,999,999,999}};
     for(int i=0;i<noofnodes;i++)
     {
         node[i]=new Node(i,arrofheu[i],pathmat[i]);
         node[i].Display();
     }
     System.out.println();
     for(int i=0;i<noofnodes;i++)
     {
         for(int j=0;j<noofnodes;j++)
         {
             System.out.print(pathmat[i][j]+"\t");
         }
         System.out.println();
     }
     //declaration end
     int source=0,current_cost=0,previouspre=0;
     ArrayList<Node> open=new ArrayList();
     //added source node in the open list
     open.add(node[source]);
     //iterate till open is not empty 
     PriorityQueue<Entry> q = new PriorityQueue<>();
     //Queue for finding minimum path
     while(!open.isEmpty())
     {
         //find minimum in this node's pathmat
         for(int i=0;i<noofnodes;i++){
             if(pathmat[source][i]!=999)
                 q.add(new Entry(current_cost+pathmat[source][i]+arrofheu[i],source,i));
        
         }
         
//         while(!q.isEmpty()){
//          Entry e=q.poll();
//          System.out.println(e.cos+" "+e.source+" "+e.destination);
//        }
        if(!q.isEmpty()){
        Entry E=q.poll();
        
        if(node[E.source].closed[E.destination]!=1){
                
                node[E.source].ispath[E.destination]=0;
                node[E.source].closed[E.destination]=1;
                //node[E.destination].pre=E.source;
                node[E.destination].canbe.add(E.source);
                source=E.destination;
                if(!open.contains(node[source]))
                  open.add(node[source]);
        //finding current_cost
                //int k=source;
                current_cost=0;
                for(int j=0;j<node[source].canbe.size();j++)
                {
                    node[source].pre=node[source].canbe.get(j);
                    int k=source;
                    current_cost=0;
                    while(k!=0)
                    {
                        current_cost+=pathmat[node[k].pre][k];
                        k=node[k].pre;
                    }
                    node[source].costarr[node[source].canbe.get(j)]=current_cost;
                }
        //finding minimum cost    
            int min=999,lock=source;
            for(int i=0;i<node[source].costarr.length;i++)
                {
                    if((min>node[source].costarr[i])&&(node[source].costarr[i]!=0))
                    {
                        min=node[source].costarr[i];
                        lock=i;
                    }
                }
            current_cost=node[source].costarr[lock];
            node[source].pre=lock;

//                while(k!=0)
//                {
//                    
//                    current_cost+=pathmat[node[k].pre][k];
//                    k=node[k].pre;
//                }
               
        }}
        
        System.out.println("Previous Node: "+node[source].pre+" Current Node: "+source+" current cost: "+current_cost);
        node[source].mylen=current_cost;
        if(source==4)
        {
            int k=source;   
            while(k!=0)
                {
                k=node[k].pre;
                System.out.print("-"+k);
                }
            System.out.println();
        }
        
        
        
        
        
           // System.out.println(open.size());
     
            for(int i=0;i<open.size();i++)
            if(open.get(i).checkOpen()==0){
            System.out.println("Object "+open.get(i).no+"removed from open");
                open.remove(i);
            
            }
     
     }

 }
}
