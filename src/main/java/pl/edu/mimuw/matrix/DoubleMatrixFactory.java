package pl.edu.mimuw.matrix;

public class DoubleMatrixFactory {

  private DoubleMatrixFactory() {
  }

  public static IDoubleMatrix sparse(Shape shape, MatrixCellValue... values) {
    return null; // Tu trzeba wpisać właściwą instrukcję
  }

  public static IDoubleMatrix full(double[][] values) {
    return null; // Tu trzeba wpisać właściwą instrukcję
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

  public static IDoubleMatrix vector(double... values){
    return null; // Tu trzeba wpisać właściwą instrukcję
  }

  public static IDoubleMatrix zero(Shape shape) {
    return null; // Tu trzeba wpisać właściwą instrukcję
  }

//  New constructors:

  // Constannt matrix.
  public static IDoubleMatrix constant(Shape shape, double value) {
    return null;
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
