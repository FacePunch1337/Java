package step.learning.basics;

public class BasicsDemo {
    public void run(){
        System.out.println("Java basics");

        byte b = 10;
        short s = 1000;
        int i = 10000000;
        long l = 100000000000L;

    }

    public void arrOut(){
        int[][] arr2 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
        };

        for (int[] row : arr2) {
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }

    }


    public void multiplyMatrices() {
        int[][] arr1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        int[][] arr2 = {
                {9, 8, 7},
                {6, 5, 4},
                {3, 2, 1}
        };

        int rows1 = arr1.length;
        int columns1 = arr1[0].length;
        int columns2 = arr2[0].length;

        int[][] result = new int[rows1][columns2];

        // Вычисление произведения матриц и заполнение массива result
        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < columns2; j++) {
                int sum = 0;
                for (int k = 0; k < columns1; k++) {
                    sum += arr1[i][k] * arr2[k][j];
                }
                result[i][j] = sum;
            }
        }

        // Вывод матриц в требуемом формате
        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < columns1; j++) {

                if(j == 0){
                    System.out.print("|");
                }
                System.out.print(" " + arr1[i][j] + " ");
                if(j == 2){
                    System.out.print("|");
                }

            }
            if(i == 1){
                System.out.print("  *  ");
            }
            else{
                System.out.print("     ");
            }

            for (int j = 0; j < columns1; j++) {
                if(j == 0){
                    System.out.print("|");
                }
                System.out.print(" " + arr2[i][j] + " ");
                if(j == 2){
                    System.out.print("|");
                }
            }
            if(i == 1){
                System.out.print("  =  ");
            }
            else{
                System.out.print("     ");
            }

            for (int j = 0; j < columns2; j++) {
                if(j == 0){
                    System.out.print("|");
                }
                System.out.print("\t" + result[i][j] + "\t");
                if(j == 2){
                    System.out.print("|");
                }
            }
            System.out.println();
        }


    }










}
