import java.util.Scanner;

/**
 * 请用Java语言写一个函数printArray(int[i][j] array)，该函数将一个二维数组以顺时针螺旋的方式打印出来。
 * 例如：i=4,j=5,数组
 * 1  2  3  4  5
 * 14 15 16 17 6
 * 13 20 19 18 7
 * 12 11 10 9  8
 * 输出序列是：1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20
 * @author lifen
 * 
 */
public class PrintNums {
	public static int[][] array = { { 1, 2, 3, 4, 5 }, { 14, 15, 16, 17, 6 },
			{ 13, 20, 19, 18, 7 }, { 12, 11, 10, 9, 8 } };

	public static void main(String[] args) {
		PrintNums program = new PrintNums();
		program.printArray(array);

	}

	public void printArray(int[][] array) {
		Character[] dire = { 'E', 'S', 'W', 'N' };
		// System.out.println(dire.length);
		char p = dire[0];
		Boolean loop1 = true;
		int h1 = 0, h2 = array[0].length - 1;
		int v1 = 0, v2 = array.length - 1;
//		System.out.println(v2);
		int i = 0;
		while (loop1) {
			p = dire[i];
			if (v1 > v2 || h1 > h2)
				loop1 = false;
			if (p == 'E') {
				forwardPrint(h1, h2, v1, v1, array);
				// ++h1;
				++v1;
			}
			if (p == 'S') {
				forwardPrint(h2, h2, v1, v2, array);
				// --v2;
				--h2;
			}
			if (p == 'W') {
				reversePrint(h1, h2, v2, v2, array);
				// --h2;
				--v2;
			}
			if (p == 'N') {
				reversePrint(h1, h1, v1, v2, array);
				// ++v1;
				++h1;
			}
			++i;
			if (i == 4)
				i = 0;
		}
	}

	public void forwardPrint(int a1, int a2, int b1, int b2, int[][] c) {
		for (int j = b1; j <= b2; j++) {
			for (int k = a1; k <= a2; k++) {
				System.out.print(c[j][k] + ",");
			}
		}
	}

	public void reversePrint(int a1, int a2, int b1, int b2, int[][] c) {
		for (int j = b2; j >= b1; j--) {
			for (int k = a2; k >= a1; k--) {
				System.out.print(c[j][k] + ",");
			}
		}
	}

}