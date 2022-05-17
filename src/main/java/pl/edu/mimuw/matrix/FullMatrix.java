package pl.edu.mimuw.matrix;
import java.lang.Math;

public class FullMatrix extends DoubleMatrix implements IDoubleMatrix {
  private double data[][];

  public FullMatrix(double[][] values) {
    super(values.length, values[0].length);
    int rows = values.length, columns = values[0].length;

    data = values.clone();
//    for (int rowId = 0; rowId < rows; rowId++) {
//      for (int colId = 0; colId < columns; colId++) {
//        assert(colId < values[rowId].length);
//        data[rowId][colId] = values[rowId][colId];
//      }
//    }
  }

  @Override
  public double get(int row, int column) {
    this.shape().assertInShape(row, column);
//    assert(row >= 0 && column >= 0);
//    assert(row < this.shape().rows && column < this.shape().columns);
    return data[row][column];
  }

  @Override
  public double[][] data() {
    return data;
  }

  @Override
  public double normOne() {
    if (normOne < 0) {
      double result = 0, tmp = 0;
      int k = this.shape().columns, n = this.shape().rows;

      for (int colId = 0; colId < k; colId++) {
        result = Math.max(result, tmp);
        tmp = 0;
        for (int rowId = 0; rowId < n; rowId++) {
          tmp += data[rowId][colId];
        }
      }

      normOne = result;
    }

    return normOne;
  }

  @Override
  public double normInfinity() {
    if (normInfinity < 0) {
      double result = 0, tmp = 0;
      int k = this.shape().columns, n = this.shape().rows;

      for (int rowId = 0; rowId < n; rowId++) {
        result = Math.max(result, tmp);
        tmp = 0;
        for (int colId = 0; colId < k; colId++) {
          tmp += data[rowId][colId];
        }
      }

      normInfinity = result;
    }

    return normInfinity;
  }

  @Override
  public double frobeniusNorm() {
    if (frobeniusNorm < 0) {
      double result = 0;
      int k = this.shape().columns, n = this.shape().rows;

      for (int rowId = 0; rowId < n; rowId++) {
        for (int colId = 0; colId < k; colId++) {
          result += data[rowId][colId] * data[rowId][colId];
        }
      }

      result = Math.sqrt(result);
      frobeniusNorm = result;
    }

    return frobeniusNorm;
  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    int k = this.shape().columns, n = this.shape().rows;
    result.append("Rozmiar: " + n + " x " + k + "\n");
    for (int rowId = 0; rowId < n; rowId++) {
      for (int colId = 0; colId < k; colId++) {
        result.append(data[rowId][colId] + "  ");
      }
      result.append("\n");
    }

    return result.toString();
  }

}