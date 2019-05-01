
public class Test1 {
 public int SearchResult(String j,String s) {
	int number=0;
	char[] before=j.toCharArray();
	char[] after=s.toCharArray();
	for(int i=0;i<before.length;i++) {
		for(int p=0;p<after.length;p++)
		{
			if(before[i]==after[p])
				number++;
		}
	}
	System.out.println(number);
	 return number;
	 
 }
 public static void main(String[] args) {
	 Test1 m=new Test1();
	 m.SearchResult("a", "");
 }
}
