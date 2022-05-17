package pl.edu.mimuw;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ArgumentsSource;
import pl.edu.mimuw.matrix.DoubleMatrixFactory;
import pl.edu.mimuw.matrix.IDoubleMatrix;

//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static pl.edu.mimuw.TestMatrixData.TEST_PRECISION;
//import static pl.edu.mimuw.TestMatrixData.assertArrayEqualsWithTestPrecision;
import static pl.edu.mimuw.matrix.DoubleMatrixFactory.zero;
import static pl.edu.mimuw.matrix.MatrixCellValue.cell;
import static pl.edu.mimuw.matrix.Shape.matrix;

public class Main {

  public static void main(String[] args) {
    // Tu trzeba wpisać kod testujący toString dla poszczególnych macierzy i wyników operacji
    final var l = DoubleMatrixFactory.sparse(
            matrix(1_000_000, 1_000_000_000),
            cell(0, 0, 3),
            cell(0, 213, 2),
            cell(0, 555_555, 66),

            cell(456_456, 1, 7),
            cell(456_456, 321, 8),
            cell(456_456, 444_444, 66)

    );
  }
}
