package pl.edu.mimuw.matrix;

public class DoubleMatrixFactory {

  private DoubleMatrixFactory() {
  }

  public static IDoubleMatrix sparse(Shape shape, MatrixCellValue... values) {
    assert shape != null;
    assert values != null;
    assert values.length > 0;

    return new SparseMatrix(shape, values);
  }

  public static IDoubleMatrix full(double[][] values) {
    assert values != null;
    assert values.length > 0;
    assert values[0].length > 0;
    for (int i = 0; i < values.length; i++) {
      assert values[i].length == values[0].length;
    }

    return new FullMatrix(values);
  }

  public static IDoubleMatrix identity(int size) {
    assert size > 0;
    return new IdentityMatrix(size);
  }

  public static IDoubleMatrix diagonal(double... diagonalValues) {
    assert diagonalValues != null;
    assert diagonalValues.length > 0;

    return new DiagonalMatrix(diagonalValues);
  }

  public static IDoubleMatrix antiDiagonal(double... antiDiagonalValues) {
    assert antiDiagonalValues != null;
    assert antiDiagonalValues.length > 0;

    return new AntiDiagonalMatrix(antiDiagonalValues);
  }

//  Vector has dimensions n x 1.
  public static IDoubleMatrix vector(double... values){
    assert values != null;
    assert values.length > 0;
    double newValues[][] = new double[values.length][1];
    for (int i = 0; i < values.length; i++) {
      newValues[i][0] = values[i];
    }

    return new FullMatrix(newValues);
  }

  public static IDoubleMatrix zero(Shape shape) {
    assert shape != null;
    return new ZeroMatrix(shape);
  }

//  New constructors:

  // Constant matrix.
  public static IDoubleMatrix constant(Shape shape, double value) {
    assert shape != null;
    return new ConstantMatrix(shape, value);
  }

  //  Constant columns.
  public static IDoubleMatrix columns(Shape shape, double... columnValues) {
    assert shape != null;
    assert columnValues != null;
    assert columnValues.length > 0;

    return new ConstantColumnsMatrix(shape, columnValues);
  }

  //  Constant rows.
  public static IDoubleMatrix rows(Shape shape, double... rowValues) {
    assert shape != null;
    assert rowValues != null;
    assert rowValues.length > 0;

    return new ConstantRowsMatrix(shape, rowValues);
  }

}
