import java.util.ArrayList;

public class Test2 {
public static ArrayList<ArrayList<Integer>> SearchResult(int n,int k) {
	ArrayList<ArrayList<Integer>> result=new ArrayList<ArrayList<Integer>>();
	ArrayList<Integer> list=new ArrayList<Integer>();
	int [] num=new int[n];
	for(int i=0;i<n;i++) {
		num[i]=i+1;
		System.out.println(num[i]);
	}
	subsets(n,k,num,0,list,result);
	return result;
	}
  private static void subsets(int n,int k,int[] num,int begin,ArrayList<Integer> list,ArrayList<ArrayList<Integer>> result) {
	  if(list.size()>=k) {
		  ArrayList<Integer> c=new ArrayList<Integer>(list);
		  result.add(c);
	  }
	  else {
		  for(int j=begin;j<num.length;j++) {
			  list.add(num[j]);
			  subsets(n,k,num,j+1,list,result);
			  list.remove(list.size()-1);
			  
			  
		  }
	  }
  }
	
	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> array=SearchResult(3,0);
		for(int m=0;m<array.size();m++) {
			System.out.println(array.get(m));
		}
	}
}

