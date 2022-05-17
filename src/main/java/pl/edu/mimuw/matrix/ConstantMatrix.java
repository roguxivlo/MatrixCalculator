package pl.edu.mimuw.matrix;
import java.lang.Math;
import java.util.Arrays;


public class ConstantMatrix extends DoubleMatrix implements IDoubleMatrix {
  protected double value;

  public ConstantMatrix(Shape shape, double value) {
    super(shape.rows, shape.columns);
    this.value = value;
  }

  @Override
  public double get(int row, int column) {
    this.shape().assertInShape(row, column);
    return value;
  }

  @Override
  public double[][] data() {
    double result[][] = new double[this.shape().rows][this.shape().columns];
    for (int i = 0; i < this.shape().rows; i++) {
      Arrays.fill(result[i], value);
    }
    return result;
  }

  @Override
  public double normOne() {
    if (normOne < 0) {
      normOne = value * this.shape().rows;
    }
    return normOne;
  }

  @Override
  public double normInfinity() {
    if (normInfinity < 0) {
      normInfinity = value * this.shape().columns;
    }
    return normInfinity;
  }

  @Override
  public double frobeniusNorm() {
    if (frobeniusNorm < 0) {
      frobeniusNorm = value * value * this.shape().columns * this.shape().rows;
      frobeniusNorm = Math.sqrt(frobeniusNorm);
    }
    return frobeniusNorm;
  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    result.append("Rozmiar: " + this.shape().rows + " x " + this.shape().columns + "\n");
    if (this.shape().columns < 3) {
      for (int i = 0; i < this.shape().rows; i++) {
        for (int j = 0; j < this.shape().columns; j++) {
          result.append(value + " ");
        }
        result.append("\n");
      }
    }
    else {
      for (int i = 0; i < this.shape().rows; i++) {
        result.append(value + " ... " + value);
        result.append("\n");
      }
    }

    return result.toString();
  }
}