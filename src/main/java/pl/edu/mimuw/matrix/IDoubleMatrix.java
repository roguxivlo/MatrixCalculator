package pl.edu.mimuw.matrix;

public interface IDoubleMatrix {

  default IDoubleMatrix times(IDoubleMatrix other) {
    int k = this.shape().rows, l = this.shape().columns,
            m = other.shape().rows, n = other.shape().columns;
//  Check if dimensions match.
    assert (l == m) && (k > 0 && l > 0 && n > 0);
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

    assert (k > 0 && l > 0);

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
//    assert (k == m && l == n);
//    assert (k > 0 && l > 0);

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
    int k = this.shape().rows, l = this.shape().columns,
            m = other.shape().rows, n = other.shape().columns;
//    Check if dimensions maatch.
    assert this.shape().equals(other.shape());
//    assert (k == m && l == n);
//    assert (k > 0 && l > 0);

    double data[][] = new double[k][l];

    for (int rowId = 0; rowId < k; rowId++) {
      for(int colId = 0; colId < l; colId++) {
        data[rowId][colId] = this.get(rowId, colId) - other.get(rowId, colId);
      }
    }

    return DoubleMatrixFactory.full(data);
  }

  default IDoubleMatrix minus(double scalar) {
    int k = this.shape().rows, l = this.shape().columns;

    assert (k > 0 && l > 0);

    double data[][] = new double[k][l];

    for (int rowId = 0; rowId < k; rowId++) {
      for(int colId =  0; colId < l; colId++) {
        data[rowId][colId] = this.get(rowId, colId) - scalar;
      }
    }

    return DoubleMatrixFactory.full(data);
  }

  double get(int row, int column);

  double[][] data();

  double normOne();

  double normInfinity();

  double frobeniusNorm();

  String toString();

  Shape shape();
}