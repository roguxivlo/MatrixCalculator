package pl.edu.mimuw.matrix;

public final class DiagonalMatrix extends Diagonal implements IDoubleMatrix {

  public DiagonalMatrix(double... diagonalValues) {
    super(diagonalValues);
  }

  @Override
  public IDoubleMatrix times(IDoubleMatrix other) {
    int l = this.shape().columns, m = other.shape().rows,
            n = other.shape().columns;
//  Check if dimensions match.
    assert l == m;
    if (this.getClass() == other.getClass()) {
      double[] newValues = new double[this.shape().columns];
      for (int i = 0; i < l; i++) {
        newValues[i] = this.values[i] * other.get(i,i);
      }

      return DoubleMatrixFactory.diagonal(newValues);
    }
    else {
      return super.times(other);
    }
  }

  @Override
  public IDoubleMatrix times(double scalar) {
    double[] newValues = new double[this.shape().columns];
    for (int i = 0; i < this.shape().columns; i++) {
      newValues[i] = scalar * values[i];
    }
    return DoubleMatrixFactory.diagonal(newValues);
  }

  @Override
  public IDoubleMatrix plus(IDoubleMatrix other) {
    assert this.shape().equals(other.shape());
    if (getClass() == other.getClass()) {
      double newValues[] = new double[this.shape().columns];
      for (int i = 0; i < this.shape().columns; i++) {
        newValues[i] = values[i] + other.get(i, i);
      }

      return DoubleMatrixFactory.diagonal(newValues);
    }
    else {
      return super.plus(other);
    }
  }

  @Override
  public IDoubleMatrix plus(double scalar) {
    double[] newValues = new double[this.shape().columns];
    for (int i = 0; i < this.shape().columns; i++) {
      newValues[i] = scalar + values[i];
    }
    return DoubleMatrixFactory.diagonal(newValues);
  }

  @Override
  public IDoubleMatrix minus(IDoubleMatrix other) {
    return this.plus(other.times(-1));
  }

  @Override
  public IDoubleMatrix minus(double scalar) {
    return this.plus(-scalar);
  }

  @Override
  public double get(int row, int column) {
    this.shape().assertInShape(row, column);
    if (row == column) {
      return values[row];
    }
    else {
      return 0;
    }
  }

}