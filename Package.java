import java.math.*;
import java.util.*;

/**
 * http://acm.nyist.net/JudgeOnline/problem.php?pid=311
 * 
 * 描述
 * 直接说题意，完全书包定义有N种物品和一个容量为V的书包，每种物品都有无限件可用。第i种物品的体积是c，价值是w。求解将哪些物品装入书包可使这些物品的体积总和不超过书包容量，
 * 且价值总和最大。本题要求是书包恰好装满书包时，求出最大价值总和是多少。如果不能恰好装满书包，输出NO
 * 
 * 输入
 * 第一行： N 表示有多少组测试数据（N<7）。
 * 接下来每组测试数据的第一行有两个整数M，V。 M表示物品种类的数目，V表示书包的总容量。(0<M<=2000，0<V<=50000)
 * 接下来的M行每行有两个整数c，w分别表示每种物品的重量和价值(0<c<100000，0<w<100000)
 * 
 * 输出
 * 对应每组测试数据输出结果（如果能恰好装满书包，输出装满书包时书包内物品的最大价值总和。 如果不能恰好装满书包，输出NO）
 * 
 * 样例输入
 * 2
 * 1 5
 * 2 2
 * 2 5
 * 2 2
 * 5 1
 * 
 * 样例输出
 * NO
 * 1
 * 
 * */
public class Package{
	
	Scanner sc=new Scanner(System.in);
	
	public void init(){
		int N=sc.nextInt();
		while(N--!=0){
			int n=sc.nextInt();
			int w=sc.nextInt();
			int[][] array=new int[2][w+1];
			int[] W=new int[n+1];
			int[] V=new int[n+1];
			
			for(int i=1;i<=n;i++){
				W[i]=sc.nextInt();
				V[i]=sc.nextInt();
			}

			Arrays.fill(array[0], -1);
			array[0][0]=0;
			for(int i=1;i<n+1;i++){
				for(int j=1;j<w+1;j++){
					if(W[i]>j||array[1][j-W[i]]==-1){
						array[1][j]=array[0][j];
					}else{
						array[1][j]=Math.max(array[0][j],array[1][j-W[i]]+V[i]);
						array[0][j]=array[1][j];
					}
				}
			}
			if(array[1][w]==-1){
				System.out.println("NO");
			}else{
				System.out.println(array[1][w]);
			}
		} 

	} 

	public static void main(String[] args) throws Exception{
		new Package().init();
	}

}
