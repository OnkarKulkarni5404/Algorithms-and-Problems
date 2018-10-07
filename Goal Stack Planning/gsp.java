import java.util.*;
public class gsp
{
public int nob;
public int On[][];
public int OnT[];
public int Clear[];
public int holding;
public boolean ae;
public static Scanner s;
public String goalarr[];
public String startarr[];

gsp(){s=new Scanner(System.in);}
gsp(int n)
{
	nob=n;
	s=new Scanner(System.in);
	On=new int[n][n];
	OnT=new int[n];
	Clear=new int[n];
	ae=true;
}

void Takeinput()
{
	System.out.println("Enter Start State:");
	String start="ontable(b)^on(d,b)^on(a,c)^ontable(c)^clear(d)^clear(a)";
	System.out.println("Enter Goal State:");
	String goal="ontable(a)^on(b,a)^on(c,b)^on(d,c)^clear(d)^armempty";
	startarr=start.split("['^']");
	goalarr=goal.split("['^']");
	for(int i=0;i<startarr.length;i++)
	{
		if(startarr[i].contains("ontable"))
			OnT[startarr[i].charAt(8)%97]=1;
		else if(startarr[i].contains("on"))
			On[startarr[i].charAt(3)%97][startarr[i].charAt(5)%97]=1;
		else if(startarr[i].contains("clear"))
			Clear[startarr[i].charAt(6)%97]=1;
		else if(startarr[i].contains("holding"))
			holding=startarr[i].charAt(8)%97;
		else if(startarr[i].contains("armempty"))
			ae=true;
		else
		{}
	}
}
void Display()
{
	System.out.println("ON");
	for(int i=0;i<nob;i++)
	{
		for(int j=0;j<nob;j++)
			System.out.print(On[i][j]+"\t");
		System.out.println();
	}
	System.out.println("On T");
	{
		for(int i=0;i<nob;i++)
			System.out.println("\t"+OnT[i]);
	}
	System.out.println("Clear");
	{	
		for(int i=0;i<nob;i++)
			System.out.println("\t"+Clear[i]);
	}
	System.out.println("Holding-:"+holding);
}

boolean  Isontable(int n)
{
	if(OnT[n]==1)
		return true;
	else
		return false;
}
boolean IsOn(int n,int m)
{
	if(On[n][m]==1)
		return true;
	else
		return false;
}

boolean Isclear(int n)
{
	if(Clear[n]==1)
		return true;
	else
		return false;
}

//This is an Action
boolean Putontable(int n)
{
	if(!Isclear(n))
	{
		int guilty=0;
		for(int i=0;i<nob;i++)
			if(On[i][n]!=0)
			{
				guilty=i;
				break;
			}
		Unstack(guilty,n);
	}
	if(!ae){
		Putdown();
	}
	
	holding=n;
	return Putdown();
}

//This is also an Action
boolean Putdown()
{
	Clear[holding]=1;
	OnT[holding]=1;
	holding=-99;
	ae=true;
	return true;
}
//This is Also an Action
void Unstack(int a,int b)
{
	int guilty=0;
	if(!Isclear(a)){
		for(int i=0;i<nob;i++)
			if(On[i][a]!=0)
			{
				guilty=i;
				break;
			}
			Unstack(guilty,a);
	}
	if(IsOn(a,b))
	{
		if(!ae)
			Putdown();
		Putontable(a);
		On[a][b]=0;
		Clear[b]=1;
		Clear[a]=1;
	}
}
void holdingandclear(int a,boolean x)
{
	int guilty=0;
	if(!Isclear(a)){
		for(int i=0;i<nob;i++)
			if(On[i][a]!=0)
			{
				guilty=i;
				break;
			}
			Unstack(guilty,a);
	}
	if(x){
		holding=a;
		ae=false;	
	}
}

void MyStack(int a,int b)
{
holdingandclear(b,false);
holdingandclear(a,true);
On[a][b]=1;
Clear[a]=1;
holding=-99;
ae=true;
}

public static void main(String[] args)
{
	gsp plannerr=new gsp(4);
	plannerr.Takeinput();
	plannerr.Display();
}
}