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
public class GSP {
    int position[][];
    int ontable[];
    int clear[];
    int holding;
    boolean armempty;
    String source[];
    String goal[];
    
    GSP(){}
    GSP(int n)
    {
        position=new int[n][n];
        ontable=new int[n];
        clear=new int[n];
        armempty=true;
        holding=999;
    }
    
    void splitme(String s,String g)
    {
       source=s.split("['^']");
       goal=g.split("['^']");
    }
    void fillvar()
    {
        //"ontable(b)^on(d,b)^on(a,c)^ontable(c)^clear(d)^clear(a)"
        for(int i=0;i<source.length;i++)
        {
            if(source[i].contains("ontable"))
                ontable[source[i].charAt(8)%97]=1;
            else if(source[i].contains("on"))
                position[source[i].charAt(3)%97][source[i].charAt(5)%97]=1;
            else if(source[i].contains("clear"))
                clear[source[i].charAt(6)%97]=1;
            else if(source[i].contains("holding"))
                holding=(source[i].charAt(8)%97);
            else if(source[i].contains("armempty"))
                armempty=true;
            else
                {}
        } 
    }
    //goal=ontable(a)^on(b,a)^on(c,b)^on(d,c)^clear(d)^armempty
    void stack(int a,int b)
    {
            if(clear[b]!=1)
                clearthis(b);
            if(clear[a]!=1)
                clearthis(a);
         
            position[a][b]=1;
            clear[b]=0;
            clear[a]=1;
            armempty=true;
            holding=999;
            ontable[a]=0;
                
    }
    boolean isclear(int a)
    {
        boolean flag=true;
        for(int i=0;i<clear.length;i++){
            if(position[i][a]==1){
                flag=false;
                break;
            }
            else
                flag=true;
        }
        return flag;
    }
    void clearthis(int a)
    {
            //see whos up
            int up=0;
            for(int i=0;i<clear.length;i++)
                if(position[i][a]==1){
                    up=i;
                    break;
                }
            unstack(up,a);
            
    }
    void unstack(int a,int b)
    {
        if(!isclear(a))
            clearthis(a);
        ontable[a]=1;
        position[a][b]=0;
        clear[a]=1;
        clear[b]=1;
    }
    void putontable(int a){
    
        if(!isclear(a))
            clearthis(a);
        
        if(somethingisdown(a)!=999){
            clear[somethingisdown(a)]=1;
            position[a][somethingisdown(a)]=0;
            ontable[a]=1;
        }
       
    }
    int somethingisdown(int a)
    {
        int ans=999;
        for(int i=0;i<clear.length;i++)
        {
            if(position[a][i]==1){
                ans=i;
                break;
            }
        }
        return ans;
    }
    
    
    void process_me()
    {
        for(int i=0;i<goal.length;i++)
        {
            
            if(goal[i].contains("ontable"))
            {
                putontable(goal[i].charAt(8)%97);
            }
            else if(goal[i].contains("on"))
            {
                stack(goal[i].charAt(3)%97,goal[i].charAt(5)%97);
            }
            else
            {}
        }
    }
    void display()
    {
        System.out.println("\nShowing Position matrix");
        for(int i=0;i<clear.length;i++){
            for(int j=0;j<clear.length;j++){
                System.out.print(position[i][j]+"\t");
            }
            System.out.println();
            }
        System.out.println("Showing clear Array");
        for(int i=0;i<clear.length;i++)
            System.out.print(clear[i]+"\t");
        System.out.print("\nShowing ontable Array\n");
        for(int i=0;i<clear.length;i++)
            System.out.print(ontable[i]+"\t");

    }
    
    public static void main(String Args[]){
    
    String s="ontable(b)^ontable(d)^on(a,b)^on(c,d)^clear(a)^clear(c)";
    String g="ontable(d)^on(b,d)^on(a,b)^on(c,a)";
    GSP go=new GSP(4);
    go.splitme(s, g);
    go.fillvar();
    go.display();
    System.out.println("\n\n");
    go.process_me();
    go.display();
    }
}
