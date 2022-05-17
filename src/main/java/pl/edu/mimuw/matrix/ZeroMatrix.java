package pl.edu.mimuw.matrix;

public final class ZeroMatrix extends ConstantMatrix implements IDoubleMatrix {

  public ZeroMatrix(Shape shape) {
    super(shape, 0);
    normOne = 0;
    normInfinity = 0;
    frobeniusNorm = 0;
  }

  @Override
  public IDoubleMatrix times(IDoubleMatrix other) {
    int k = this.shape().rows, l = this.shape().columns,
            m = other.shape().rows, n = other.shape().columns;
//  Check if dimensions match.
    assert l == m;
    return DoubleMatrixFactory.zero(Shape.matrix(k,n));
  }

  @Override
  public IDoubleMatrix times(double scalar) {
    return this;
  }

  @Override
  public IDoubleMatrix plus(IDoubleMatrix other) {
//    Check if dimensions match.
    assert this.shape().equals(other.shape());

    return other;
  }

  @Override
  public IDoubleMatrix plus(double scalar) {
    return DoubleMatrixFactory.constant(this.shape(), scalar);
  }

  @Override
  public IDoubleMatrix minus(IDoubleMatrix other) {
    assert this.shape().equals(other.shape());
    return other.times(-1);
  }

  @Override
  public IDoubleMatrix minus(double scalar) {
    return DoubleMatrixFactory.constant(this.shape(), -scalar);
  }

}