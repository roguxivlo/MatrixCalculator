package pl.edu.mimuw.matrix;

public abstract class DoubleMatrix implements IDoubleMatrix {

  private Shape shape;
  protected double normOne = -1;
  protected double normInfinity = -1;
  protected double frobeniusNorm = -1;

  public DoubleMatrix(int rows, int columns) {
    shape = Shape.matrix(rows, columns);
  }

  @Override
  public Shape shape() {
    return shape;
  }

}