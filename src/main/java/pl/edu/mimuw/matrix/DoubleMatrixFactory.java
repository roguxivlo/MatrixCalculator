package pl.edu.mimuw.matrix;

public class DoubleMatrixFactory {

  private DoubleMatrixFactory() {
  }

  public static IDoubleMatrix sparse(Shape shape, MatrixCellValue... values) {
    return null; // Tu trzeba wpisać właściwą instrukcję
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
    return null; // Tu trzeba wpisać właściwą instrukcję
  }

  public static IDoubleMatrix diagonal(double... diagonalValues) {
    return null; // Tu trzeba wpisać właściwą instrukcję
  }

  public static IDoubleMatrix antiDiagonal(double... antiDiagonalValues) {
    return null; // Tu trzeba wpisać właściwą instrukcję
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

  // Constannt matrix.
  public static IDoubleMatrix constant(Shape shape, double value) {
    assert shape != null;
    return new ConstantMatrix(shape, value);
  }

  //  Constant columns.
  public static IDoubleMatrix columns(Shape shape, double... columnValues) {
    return null;
  }

  //  Constant rows.
  public static IDoubleMatrix rows(Shape shape, double... rowValues) {
    return null;
  }

}
