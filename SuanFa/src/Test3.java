
public class Test3 {
	public static void Search(int[][] array) {
		boolean[] rows=new boolean[array.length];
		boolean[] columns=new boolean[array[0].length];
		//System.out.println(array.length);
		for(int i=0;i<array.length;i++) {
			for(int j=0;j<array[0].length;j++) {
				if(array[i][j]==0) {
					rows[i]=true;
				    columns[j]=true;}
			}
			
		}
		for(int i=0;i<array.length;i++) {
			for(int j=0;j<array[0].length;j++) {
				System.out.println("rows "+i+" "+rows[i]);
				System.out.println("columns "+j+" "+columns[j]);
				if(rows[i]||columns[j])
					array[i][j]=0;
			}
		}
	}
	public static void main(String[] args) {
		int [][] list= {{0,4,5},{1,0,9},{1,2,1}};
		System.out.println(list[0][0]);
		Search(list);
		for(int i=0;i<list.length;i++) {
			for(int j=0;j<list[0].length;j++) {
				System.out.print(list[i][j]);
				System.out.print(" ");
			}
			System.out.println("\t");
		}
	}

}
