import java.util.Arrays;
public class stablematch{


		public static void main(String[] args){
		stablematch test = new stablematch();
		int[] pee = {0,1,2};
		int[] poo = {0,1,2};
		int[][] oat = {{0,1,2},{0,2,1},{2,0,1}};
		int[][] oat2 = {{0,1,2},{0,1,2},{0,1,2}};
		System.out.println(test.isStable(pee,poo,oat,oat2)); 
		}
		
		public boolean isStable(int[] students, int[] employers, int[][] A, int [][] B){
			int n = students.length;
			int[] students2 = new int[n];
			int[] employers2 = new int[n];
			String stud = "";
			String emp = "";
			
			for(int i = 0; i < n; i++){
				students2[i] = -1;
				employers2[i] = -1;
				
			}
			
			for(int i = 0; i < n; i++){
					for(int j = 0; j < n; j++){
						if(students2[i] == -1 && employers2[i] == -1){
							String a = Integer.toString(B[i][j]);
							String b = Integer.toString(A[i][j]);
							if(A[i][j] == B[i][j] && stud.contains(a) == false && emp.contains(b) == false){
								students2[i] = B[i][j];
								employers2[i] = A[i][j];
								emp = emp + A[i][j];
								stud = stud + B[i][j];
							}else if(A[i][j] != B[i][j] && stud.contains(a) == false && emp.contains(b) == false){
								students2[i] = B[A[i][j]][j];
								employers2[i] = A[B[i][j]][j];
								emp = emp + A[i][j];
								stud = stud + B[i][j];
							}
						}
				}
			}
		
		
		if(Arrays.equals(students2,employers) && Arrays.equals(employers2, students)){
			return true;
		}
			return false;
	}
}