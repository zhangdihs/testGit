import java.util.Scanner;

public class Test4{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int m=0,n=0;
		boolean row;
		boolean column;//��¼��0�У���0�е�Ԫ���Ƿ���Ϊ0
		int[][] matrix;
		Scanner scanner=new Scanner(System.in);
		System.out.println("���������Ĵ�С��");
		m=scanner.nextInt();
		n=scanner.nextInt();
		row=false;
		column=false;
		matrix=new int[m][n];
		System.out.println("�����СΪ��"+m+"��"+n);
		System.out.println("����������е�Ԫ�أ�");
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
		}//��ʼ�����󣬸���ֵX
		
		for(int i=1;i<m;++i) {
			if(matrix[i][0]==0) {
				for(int j=1;j<n;++j) {
					matrix[i][j]=0;
				}
			}
		}//��Ϊ0,����i����Ϊ0
		
		for(int j=1;j<n;++j) {
			if(matrix[0][j]==0) {
				for(int i=1;i<m;++i) {
					matrix[i][j]=0;
				}
			}
		}//��Ϊ0,����j����Ϊ0
		
		if(row) {
			for(int j=0;j<n;j++) {
				matrix[0][j]=0;
			}
		}//���õ�0��
		
		if(column) {
			for(int i=0;i<m;i++) {
				matrix[i][0]=0;
			}
		}//���õ�0��
		//�������
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				System.out.print(" "+matrix[i][j]);				
			}
			System.out.println(" ");
		}
			
	}

}

