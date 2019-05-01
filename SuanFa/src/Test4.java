import java.util.Scanner;

public class Test4{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int m=0,n=0;
		boolean row;
		boolean column;//记录第0行，第0列的元素是否被置为0
		int[][] matrix;
		Scanner scanner=new Scanner(System.in);
		System.out.println("请输入矩阵的大小：");
		m=scanner.nextInt();
		n=scanner.nextInt();
		row=false;
		column=false;
		matrix=new int[m][n];
		System.out.println("矩阵大小为行"+m+"列"+n);
		System.out.println("请输入矩阵中的元素：");
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				matrix[i][j]=scanner.nextInt();
				if(matrix[i][j]==0) {
					matrix[i][0]=0;
					matrix[0][j]=0;
					if(i==0) {
						row=true;
					}
					if(j==0) {
						column=true;
					}
				}
			}
		}//初始化矩阵，赋初值X
		
		for(int i=1;i<m;++i) {
			if(matrix[i][0]==0) {
				for(int j=1;j<n;++j) {
					matrix[i][j]=0;
				}
			}
		}//若为0,将第i列置为0
		
		for(int j=1;j<n;++j) {
			if(matrix[0][j]==0) {
				for(int i=1;i<m;++i) {
					matrix[i][j]=0;
				}
			}
		}//若为0,将第j行置为0
		
		if(row) {
			for(int j=0;j<n;j++) {
				matrix[0][j]=0;
			}
		}//设置第0行
		
		if(column) {
			for(int i=0;i<m;i++) {
				matrix[i][0]=0;
			}
		}//设置第0列
		//输出矩阵
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				System.out.print(" "+matrix[i][j]);				
			}
			System.out.println(" ");
		}
			
	}

}

