package hospital;

import java.util.ArrayList;

class Hasher {
	ArrayList <pair> l;
	Hasher()
	{
		l=new ArrayList<>();
		l.add(new pair(104743,1301081));		//Doctor id Hash
		l.add(new pair(105871,1300391));		//Nurse id Hash
		l.add(new pair(104953,1300709));		//original to Lvl3 table hash 
		l.add(new pair(105401,1299853));		//original to Lvl2 table hash
		l.add(new pair(105229,1300997));		//original to Lvl1 table hash
	}
	String HashingFunction(String s,int i)
	{
		return HashingUtil(s,l.get(i));
	}
	String HashingUtil(String s,pair py)
	{
		long id=Long.parseLong(s);
		long res=power(id,py.p,py.q);
		return String.valueOf(res);
	}
	long power(long base,long exp,long mod)
	{
		base=base%mod;
		if(exp==0) return 1;
		else if(exp==1) return base;
		else if(exp%2==0)
		{
			long temp=power(base,exp/2,mod)%mod;
			return (temp*temp)%mod;
		}
		else
		{
			return base*(power(base,exp-1,mod)%mod)%mod;
		}
	}

}
