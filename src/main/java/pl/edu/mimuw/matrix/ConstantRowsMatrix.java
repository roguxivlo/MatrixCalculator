package pl.edu.mimuw.matrix;
import java.util.Arrays;
import java.lang.Math;

public final class ConstantRowsMatrix extends DoubleMatrix implements IDoubleMatrix {

  private double[] rowValues;

  public ConstantRowsMatrix(Shape shape, double... rowValues) {
    super(shape.rows, shape.columns);
    this.rowValues = rowValues.clone();
  }

  @Override
  public IDoubleMatrix times(double scalar) {
    double newRowValues[] = new double[this.shape().rows];
    for (int i = 0; i < this.shape().rows; i++) {
      newRowValues[i] = scalar * rowValues[i];
    }
    return DoubleMatrixFactory.rows(this.shape(), newRowValues);
  }

  @Override
  public IDoubleMatrix plus(double scalar) {
    double newRowValues[] = new double[this.shape().rows];
    for (int i = 0; i < this.shape().rows; i++) {
      newRowValues[i] = scalar + rowValues[i];
    }
    return DoubleMatrixFactory.rows(this.shape(), newRowValues);
  }

  @Override
  public IDoubleMatrix minus(double scalar) {
    return this.plus(-scalar);
  }

  @Override
  public double get(int row, int column) {
    this.shape().assertInShape(row, column);
    return rowValues[row];
  }

  @Override
  public double[][] data() {
    double[][] result = new double[this.shape().rows][this.shape().columns];
    for (int i = 0; i <this.shape().rows; i++) {
      Arrays.fill(result[i], rowValues[i]);
    }

    return result;
  }

  @Override
  public double normOne() {
    if (normOne < 0) {
      normOne = 0;
      for (int i = 0; i < this.shape().rows; i++) {
        normOne += Math.abs(rowValues[i]);
      }
    }

    return normOne;
  }

  @Override
  public double normInfinity() {
    if (normInfinity < 0) {
      double result = 0;
      for (int i = 0; i < this.shape().rows; i++) {
        result = Math.max(result, Math.abs(rowValues[i]));
      }

      normInfinity = result * this.shape().columns;
    }

    return normInfinity;
  }

  @Override
  public double frobeniusNorm() {
    if (frobeniusNorm < 0) {
      double result = 0;
      for (int i = 0; i < this.shape().rows; i++) {
        result += rowValues[i] * rowValues[i] * this.shape().columns;
      }
      frobeniusNorm = Math.sqrt(result);
    }

    return frobeniusNorm;
  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    result.append("Rozmiar: " + this.shape().rows + " x " + this.shape().columns + "\n");
    if (this.shape().columns >= 3) {
      for (int i = 0; i <this.shape().rows; i++) {
        result.append(rowValues[i] + " ... " + rowValues[i] + "\n");
      }
    }
    else {
      for (int i = 0; i <this.shape().rows; i++) {
        for (int j = 0; j < this.shape().columns; j++) {
          result.append(rowValues[i] + "  ");
        }
        result.append("\n");
      }
    }
    return result.toString();
  }

}