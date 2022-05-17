package pl.edu.mimuw.matrix;
import java.util.Arrays;
import java.lang.Math;

public final class ConstantColumnsMatrix extends DoubleMatrix implements IDoubleMatrix {
  private double[] columnValues;

  public ConstantColumnsMatrix(Shape shape, double... columnValues) {
    super(shape.rows, shape.columns);
    this.columnValues = columnValues.clone();
  }

  @Override
  public IDoubleMatrix times(double scalar) {
    double newColumnValues[] = new double[this.shape().columns];
    for (int i = 0; i < this.shape().columns; i++) {
      newColumnValues[i] = scalar * columnValues[i];
    }
    return DoubleMatrixFactory.columns(this.shape(), newColumnValues);
  }

  @Override
  public IDoubleMatrix plus(double scalar) {
    double newColumnValues[] = new double[this.shape().columns];
    for (int i = 0; i < this.shape().columns; i++) {
      newColumnValues[i] = scalar + columnValues[i];
    }
    return DoubleMatrixFactory.columns(this.shape(), newColumnValues);
  }

  @Override
  public IDoubleMatrix minus(double scalar) {
    return this.plus(-scalar);
  }

  @Override
  public double get(int row, int column) {
    this.shape().assertInShape(row,column);
    return columnValues[column];
  }

  @Override
  public double[][] data() {
    double[][] result = new double[this.shape().rows][this.shape().columns];
    for (int i = 0; i < this.shape().rows; i++) {
      for (int j = 0; j < this.shape().columns; j++) {
        result[i][j] = columnValues[j];
      }
    }
    return result;
  }

  @Override
  public double normOne() {
    if (normOne < 0) {
      normOne = 0;
      for (int i = 0; i < this.shape().columns; i++) {
        normOne = Math.max(normOne, Math.abs(columnValues[i]));
      }
      normOne = normOne * this.shape().rows;
    }
    return normOne;
  }

  @Override
  public double normInfinity() {
    if (normInfinity < 0) {
      normInfinity = 0;
      for (int i = 0; i < this.shape().columns; i++) {
        normInfinity += Math.abs(columnValues[i]);
      }
    }
    return normInfinity;
  }

  @Override
  public double frobeniusNorm() {
    if (frobeniusNorm < 0) {
      double result = 0;
      for(int i = 0; i < this.shape().columns; i++) {
        result += columnValues[i] * columnValues[i] * this.shape().rows;
      }
      frobeniusNorm = Math.sqrt(result);
    }
    return frobeniusNorm;
  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    result.append("Rozmiar: " + this.shape().rows + " x " + this.shape().columns + "\n");
    for (int i = 0; i < this.shape().rows; i++) {
      for (int j = 0; j < this.shape().columns; j++) {
        result.append(columnValues[j] + "  ");
      }
      result.append("\n");
    }
    return result.toString();
  }
}