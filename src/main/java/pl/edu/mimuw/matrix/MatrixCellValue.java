package pl.edu.mimuw.matrix;

public final class MatrixCellValue implements Comparable<MatrixCellValue> {

  public final int row;
  public final int column;
  public final double value;

  public MatrixCellValue(int row, int column, double value) {
    this.column = column;
    this.row = row;
    this.value = value;
  }

  @Override
  public String toString() {
    return "{" + value + " @[" + row + ", " + column + "]}";
  }

  public static MatrixCellValue cell(int row, int column, double value) {
    return new MatrixCellValue(row, column, value);
  }

//  Compare first by row, then by column.
  @Override
  public int compareTo(MatrixCellValue other) {
    if (row < other.row) return -1;
    if (row > other.row) return 1;
    if (column < other.column) return -1;
    if (column > other.column) return 1;
    return 0;
  }

}
