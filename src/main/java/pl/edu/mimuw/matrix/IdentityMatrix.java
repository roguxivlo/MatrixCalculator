package pl.edu.mimuw.matrix;
import java.util.Arrays;
import java.lang.Math;

public final class IdentityMatrix extends DoubleMatrix implements IDoubleMatrix {

  public IdentityMatrix(int size) {
    super(size, size);
  }

  @Override
  public IDoubleMatrix times(IDoubleMatrix other) {
    assert other.shape().rows == this.shape().columns;

    return other;
  }

  @Override
  public IDoubleMatrix times(double scalar) {
    double newValues[] = new double[this.shape().rows];
    Arrays.fill(newValues, scalar);

    return DoubleMatrixFactory.diagonal(newValues);
  }

  @Override
  public double get(int row, int column) {
    this.shape().assertInShape(row, column);
    if (row == column) return 1;
    return 0;
  }

  @Override
  public double normOne() {
    if (normOne < 0) normOne = 1;
    return normOne;
  }

  @Override
  public double normInfinity() {
    if (normInfinity < 0) normInfinity = 1;
    return normInfinity;
  }

  @Override
  public double frobeniusNorm() {
    if (frobeniusNorm < 0) frobeniusNorm = Math.sqrt((double) this.shape().rows);
    return frobeniusNorm;
  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    int k = this.shape().columns, n = this.shape().rows;
    result.append("Rozmiar: " + n + " x " + k + "\n");
    double[][] data = this.data();
    int zeroCounter = 0;
    for (int rowId = 0; rowId < n; rowId++) {
      int colId = 0;
      while (colId < n && data[rowId][colId] == 0) {
        zeroCounter++;
        colId++;
      }
      if (zeroCounter >= 3) {
        result.append("0" + "..." + "0");
      }
      else {
        for (int i = 0; i < zeroCounter; i++) {
          result.append("0" + "  ");
        }
      }
      if (colId < n) {
        result.append("1" + "  ");
        colId++;
      }
      zeroCounter = 0;
      while (colId < n && data[rowId][colId] == 0) {
        zeroCounter++;
        colId++;
      }
      if (zeroCounter >= 3) {
        result.append("0" + "..." + "0");
      }
      else {
        for (int i = 0; i < zeroCounter; i++) {
          result.append("0" + "  ");
        }
      }
      result.append("\n");
    }
    return result.toString();
  }

}