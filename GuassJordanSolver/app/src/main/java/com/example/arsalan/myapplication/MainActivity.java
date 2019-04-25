package com.example.arsalan.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    static EditText[][] textarr=new EditText[3][3];
    static EditText[] consarr=new EditText[3];

    public void Refresh(View view){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                //convert string to int
                textarr[i][j].setText("");

            }
            consarr[i].setText("");

        }
    }



    public void Calculate(View view){

        double [][]mat = new double[3][3];
        double []constants = new double[3];


        InitializeeditText(textarr,consarr);

        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                //convert string to int
                Double num=new Double(textarr[i][j].getText().toString());
                mat[i][j]=num;
            }
            Double num_c=new Double(consarr[i].getText().toString());
            constants[i]=num_c;
        }

        Gauss_Jordan_Elimination.test(mat,constants);
        //Send it to double [][]mat = new double[n][n];
        //         double []constants = new double[n];

    }
    public void InitializeeditText(EditText[][] textarr,EditText[] consarr){
        textarr[0][0]=findViewById(R.id.a11);
        textarr[0][1]=findViewById(R.id.a12);
        textarr[0][2]=findViewById(R.id.a13);
        textarr[1][0]=findViewById(R.id.a21);
        textarr[1][1]=findViewById(R.id.a22);
        textarr[1][2]=findViewById(R.id.a23);
        textarr[2][0]=findViewById(R.id.a31);
        textarr[2][1]=findViewById(R.id.a32);
        textarr[2][2]=findViewById(R.id.a33);
        consarr[0]=findViewById(R.id.c1);
        consarr[1]=findViewById(R.id.c2);
        consarr[2]=findViewById(R.id.c3);

    }


    public static void displayOutput(double[][] mat,double[] constant){

        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                //convert string to int


                textarr[i][j].setText(mat[i][j]+"");

            }
            consarr[i].setText(constant[i]+"");

        }


    }

}
class Gauss_Jordan_Elimination {

    private static final double EPSILON = 1e-8;

    private final int N;      // N-by-N system      or mat.length
    private double[][] a;     // N-by-N+1 augmented matrix

    // Gauss-Jordan elimination with partial pivoting
    public Gauss_Jordan_Elimination(double[][] A, double[] b) {
        N = b.length;

        // build augmented matrix
        a = new double[N][N + N + 1];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                a[i][j] = A[i][j];

        // only need if you want to find certificate of infeasibility (or compute inverse)
        for (int i = 0; i < N; i++)
            a[i][N + i] = 1.0;

        for (int i = 0; i < N; i++)
            a[i][N + N] = b[i];

        solve();

        assert check(A, b);
    }

    private void solve() {
        // Gauss-Jordan elimination
        for (int p = 0; p < N; p++) {
            int max = p;
            for (int i = p + 1; i < N; i++) {
                if (Math.abs(a[i][p]) > Math.abs(a[max][p])) {
                    max = i;
                }
            }

            // exchange row p with row max
            swap(p, max);

            // singular or nearly singular
            if (Math.abs(a[p][p]) <= EPSILON) {
                continue;
                // throw new RuntimeException("Matrix is singular or nearly singular");
            }

            // pivot
            pivot(p, p);
        }
        // show();
    }

    // swap row1 and row2
    private void swap(int row1, int row2) {
        double[] temp = a[row1];
        a[row1] = a[row2];
        a[row2] = temp;
    }


    // pivot on entry (p, q) using Gauss-Jordan elimination
    private void pivot(int p, int q) {   // everything but row p and column q
        for (int i = 0; i < N; i++) {
            double alpha = a[i][q] / a[p][q];
            for (int j = 0; j <= N + N; j++) {
                if (i != p && j != q) a[i][j] -= alpha * a[p][j];
            }
        }

        // zero out column q
        for (int i = 0; i < N; i++)
            if (i != p) a[i][q] = 0.0;

        // scale row p (ok to go from q+1 to N, but do this for consistency with simplex pivot)
        for (int j = 0; j <= N + N; j++)
            if (j != q) a[p][j] /= a[p][q];
        a[p][q] = 1.0;
    }

    // extract solution to Ax = b
    public double[] primal() {
        double[] x = new double[N];
        for (int i = 0; i < N; i++) {
            if (Math.abs(a[i][i]) > EPSILON)
                x[i] = a[i][N + N] / a[i][i];
            else if (Math.abs(a[i][N + N]) > EPSILON)
                return null;
        }
        return x;
    }

    // extract solution to yA = 0, yb != 0
    public double[] dual() {
        double[] y = new double[N];
        for (int i = 0; i < N; i++) {
            if ((Math.abs(a[i][i]) <= EPSILON) && (Math.abs(a[i][N + N]) > EPSILON)) {
                for (int j = 0; j < N; j++)
                    y[j] = a[i][N + j];
                return y;
            }
        }
        return null;
    }

    // does the system have a solution?
    public boolean isFeasible() {
        return primal() != null;
    }

    // print the tableaux
    private void show() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(" " + a[i][j]);
            }
            System.out.print("| ");
            for (int j = N; j < N + N; j++) {
                System.out.print(" " + a[i][j]);
            }
            System.out.print("| \n" + a[i][N + N]);
        }
        System.out.println();
    }


    // check that Ax = b or yA = 0, yb != 0
    private boolean check(double[][] A, double[] b) {

        // check that Ax = b
        if (isFeasible()) {
            double[] x = primal();
            for (int i = 0; i < N; i++) {
                double sum = 0.0;
                for (int j = 0; j < N; j++) {
                    sum += A[i][j] * x[j];
                }
                if (Math.abs(sum - b[i]) > EPSILON) {
                    System.out.println("not feasible");
                    System.out.println(i + " = " + b[i] + ", sum = " + sum + "\n");
                    return false;
                }
            }
            return true;
        }

        // or that yA = 0, yb != 0
        else {
            double[] y = dual();
            for (int j = 0; j < N; j++) {
                double sum = 0.0;
                for (int i = 0; i < N; i++) {
                    sum += A[i][j] * y[i];
                }
                if (Math.abs(sum) > EPSILON) {
                    System.out.println("invalid certificate of infeasibility");
                    System.out.println("sum = " + sum + "\n");
                    return false;
                }
            }
            double sum = 0.0;
            for (int i = 0; i < N; i++) {
                sum += y[i] * b[i];
            }
            if (Math.abs(sum) < EPSILON) {
                System.out.println("invalid certificate of infeasibility");
                System.out.println("yb  = " + sum + "\n");

                return false;
            }
            return true;
        }
    }


    public static void test(double[][] A, double[] b) {
        Gauss_Jordan_Elimination gaussian = new Gauss_Jordan_Elimination(A, b);
        if (gaussian.isFeasible()) {
            System.out.println("Solution to Ax = b");
            double[] x = gaussian.primal();
            gaussian.print(x);

//            for (int i = 0; i < x.length; i++)
//            {
//            	System.out.println(" "+x[i]+"\n");
//            }
        } else {
            System.out.println("Certificate of infeasibility");
            double[] y = gaussian.dual();
            gaussian.print(y);
//            for (int j = 0; j < y.length; j++)
//            {
//            	System.out.println(" "+y[j]+"\n");
//            }
        }
        System.out.println();
    }
    /* public static void main(String[] args)
     {

        Scanner input = new Scanner(System.in);
         System.out.println("Enter the number of variables in the equations: ");
         int n =3; //number of variables
         System.out.println("Enter the coefficients of each variable for each equations");
         System.out.println("ax + by + cz + ... = d");
         double [][]mat = new double[n][n];
         double []constants = new double[n];
         //input
         for(int i=0; i<n; i++)
         {
             for(int j=0; j<n; j++)
             {
                 mat[i][j] = input.nextDouble();
             }
             constants[i] = input.nextDouble();
         }
        //Take input fun and prepare output function
         test(mat, constants);
     }*/

    void print(double[] constants) {
        //print whole array
       /* for(int i=0; i<a.length; i++)
        {
            for(int j=0; j<a.length; j++)
            {
                System.out.print( a[i][j]+" ");
            }
            System.out.print(constants[i]);
            System.out.println("");

        }
        */
       //roundoff a and constants
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                //convert string to int
                a[i][j]=Math.round(a[i][j]);
            }
            constants[i]=Math.round(constants[i]);
        }


        MainActivity.displayOutput(a, constants);

    }

}
