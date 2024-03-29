package pl.edu.mimuw.matrix;

public interface IDoubleMatrix {

  default IDoubleMatrix times(IDoubleMatrix other) {
    int k = this.shape().rows, l = this.shape().columns,
            m = other.shape().rows, n = other.shape().columns;
//  Check if dimensions match.
    assert l == m;
    double[][] data = new double[k][n];
    double result = 0;

    for (int colId = 0; colId < n; colId++) {
      for (int rowId = 0; rowId < k; rowId++) {
        result = 0;
        for (int i = 0; i < l; i++) {
          result += this.get(rowId, i) * other.get(i, colId);
        }
        data[rowId][colId] = result;
      }
    }

    return DoubleMatrixFactory.full(data);
  }

  default IDoubleMatrix times(double scalar) {
    int k = this.shape().rows, l = this.shape().columns;

    double[][] data = new double[k][l];

    for (int rowId = 0; rowId < k; rowId++) {
      for (int colId = 0; colId < l; colId++) {
        data[rowId][colId] = this.get(rowId, colId) * scalar;
      }
    }

    return DoubleMatrixFactory.full(data);
  }

  default IDoubleMatrix plus(IDoubleMatrix other) {
    int k = this.shape().rows, l = this.shape().columns,
            m = other.shape().rows, n = other.shape().columns;
//    Check if dimensions match.
    assert this.shape().equals(other.shape());

    double data[][] = new double[k][l];

    for (int rowId = 0; rowId < k; rowId++) {
      for(int colId = 0; colId < l; colId++) {
        data[rowId][colId] = this.get(rowId, colId) + other.get(rowId, colId);
      }
    }

    return DoubleMatrixFactory.full(data);
  }

  default IDoubleMatrix plus(double scalar) {
    int k = this.shape().rows, l = this.shape().columns;

    assert (k > 0 && l > 0);

    double data[][] = new double[k][l];

    for (int rowId = 0; rowId < k; rowId++) {
      for(int colId =  0; colId < l; colId++) {
        data[rowId][colId] = this.get(rowId, colId) + scalar;
      }
    }

    return DoubleMatrixFactory.full(data);
  }

  default IDoubleMatrix minus(IDoubleMatrix other) {
    return this.plus(other.times(-1));
  }

  default IDoubleMatrix minus(double scalar) {
    return this.plus(-scalar);
  }

  double get(int row, int column);

  default double[][] data() {
    double[][] result = new double[this.shape().rows][this.shape().columns];
    for (int rowId = 0; rowId < this.shape().rows; rowId++) {
      for (int colId = 0; colId < this.shape().columns; colId++) {
        result[rowId][colId] = this.get(rowId, colId);
      }
    }
    return result;
  }

  double normOne();

  double normInfinity();

  double frobeniusNorm();

  String toString();

  Shape shape();
}