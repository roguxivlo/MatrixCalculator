package pl.edu.mimuw.matrix;
import java.lang.Math;

public abstract class Diagonal extends DoubleMatrix implements IDoubleMatrix {
  protected double values[];

  public Diagonal(double values[]) {
    super(values.length, values.length);
    this.values = values.clone();
  }

  @Override
  public double normOne() {
    if (normOne < 0) {
      double result = 0;
      for (int i = 0; i < values.length; i++) {
        result = Math.max(result, values[i]);
      }
      normOne = result;
      normInfinity = result;
    }
    return normOne;
  }

  @Override
  public double normInfinity() {
    if (normInfinity < 0) {
      double result = 0;
      for (int i = 0; i < values.length; i++) {
        result = Math.max(result, values[i]);
      }
      normOne = result;
      normInfinity = result;
    }
    return normInfinity;
  }

  @Override
  public double frobeniusNorm() {
    if (frobeniusNorm < 0) {
      double result = 0;
      for (int i = 0; i < values.length; i++) {
        result += values[i] * values[i];
      }

      frobeniusNorm = Math.sqrt(result);
    }
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
        result.append(data[rowId][colId] + "  ");
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