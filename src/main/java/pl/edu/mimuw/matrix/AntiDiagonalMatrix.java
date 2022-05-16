package pl.edu.mimuw.matrix;
import java.lang.Math;

public final class AntiDiagonalMatrix extends Diagonal implements IDoubleMatrix {

  public AntiDiagonalMatrix(double... anitDiagonalValues) {
    super(anitDiagonalValues);
  }

  @Override
  public IDoubleMatrix times(double scalar) {
    double[] newValues = new double[this.shape().columns];
    for (int i = 0; i < this.shape().columns; i++) {
      newValues[i] = scalar * values[i];
    }
    return DoubleMatrixFactory.antiDiagonal(newValues);
  }

  @Override
  public IDoubleMatrix plus(double scalar) {
    double[] newValues = new double[this.shape().columns];
    for (int i = 0; i < this.shape().columns; i++) {
      newValues[i] = scalar + values[i];
    }
    return DoubleMatrixFactory.antiDiagonal(newValues);
  }

  @Override
  public double get(int row, int column) {
    this.shape().assertInShape(row, column);
    if (row + column == this.shape().rows - 1) {
      return values[row];
    }
    else return 0;
  }
}