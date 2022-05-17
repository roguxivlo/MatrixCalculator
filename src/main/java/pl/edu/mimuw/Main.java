package pl.edu.mimuw;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ArgumentsSource;
//import pl.edu.mimuw.matrix.DoubleMatrixFactory;
//import pl.edu.mimuw.matrix.IDoubleMatrix;

//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static pl.edu.mimuw.TestMatrixData.TEST_PRECISION;
//import static pl.edu.mimuw.TestMatrixData.assertArrayEqualsWithTestPrecision;
//import static pl.edu.mimuw.matrix.DoubleMatrixFactory.zero;
//import static pl.edu.mimuw.matrix.MatrixCellValue.cell;
//import static pl.edu.mimuw.matrix.Shape.matrix;
//import static pl.edu.mimuw.matrix.SparseMatrix.*;
import pl.edu.mimuw.matrix.*;

public class Main {

  public static void main(String[] args) {
    // Tu trzeba wpisać kod testujący toString dla poszczególnych macierzy i wyników operacji
    Shape shape = Shape.matrix(10, 10);

    double[] Values = new double[]{1, -2, 3, -4, 5, -6, 7, -8, 9, -10};

    IDoubleMatrix constant = DoubleMatrixFactory.constant(shape, Values[0]);
    IDoubleMatrix columns = DoubleMatrixFactory.columns(shape, Values);
    IDoubleMatrix rows = DoubleMatrixFactory.rows(shape, Values);

//    Macierz Zerowa
    System.out.println(DoubleMatrixFactory.zero(shape));

//    Macierz stała
    System.out.println(constant.toString());

//    Macierz kolumnowa
    System.out.println(columns.toString());

//    Macierz Wierszowa
    System.out.println(rows.toString());

//    Wektor
    System.out.println(DoubleMatrixFactory.vector(Values));

//    Macierz Diagonalna
    System.out.println(DoubleMatrixFactory.diagonal(Values));

//    Macierz Antydiagonalna
    System.out.println(DoubleMatrixFactory.antiDiagonal(Values));

  }
}
