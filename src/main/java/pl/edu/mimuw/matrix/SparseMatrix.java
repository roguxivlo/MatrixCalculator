package pl.edu.mimuw.matrix;
import java.util.*;
import java.lang.Math;

public final class SparseMatrix extends DoubleMatrix implements IDoubleMatrix {
  public final ArrayList<ArrayList<MatrixCellValue>> values;
  public final ArrayList<ArrayList<MatrixCellValue>> valuesTransposed;
  public final ArrayList<Integer> nonZeroRows;
  public final ArrayList<Integer> nonZeroColumns;
  public final int isZero;

  public SparseMatrix(Shape shape, MatrixCellValue... values) {
    super(shape.rows, shape.columns);

    //    How many non-zero rows are there?

    if (values.length == 0) {
      isZero = 1;
      this.values = null;
      valuesTransposed = null;
      nonZeroRows = null;
      nonZeroColumns = null;
    }
    else {
      isZero = 0;
      nonZeroRows = new ArrayList<Integer>();
      nonZeroColumns = new ArrayList<Integer>();
      Arrays.sort(values);
      int tmp;
      for (int i = 0; i < values.length; i=i) {
        assert (values[i].row >= 0 && values[i].row < shape.rows);
        assert (values[i].column >= 0 && values[i].column < shape.columns);
        nonZeroRows.add(values[i].row);
        tmp = values[i].row;
        while(i < values.length && values[i].row == tmp) i++;
      }

      this.values = new ArrayList<ArrayList<MatrixCellValue>>();
      for (int i = 0; i < nonZeroRows.size(); i++) {
        this.values.add(new ArrayList<MatrixCellValue>());
      }

//    System.out.println("niezerowe wiersze: " + nonZeroRows.size());
//    System.out.println("Ilość wierszolist: " + this.values.size());
//    System.out.println("Być albo nie być: " + this.values.get(0) == null);
      int rowCounter = 0;
      for (int i = 0; i < values.length; i=i) {
        tmp = values[i].row;
//      System.out.println(tmp);
        while (i < values.length && tmp == values[i].row) {
          this.values.get(rowCounter).add(values[i]);
          i++;
        }
        rowCounter++;
      }

//    How many non-zero columns are there?
      Arrays.sort(values, new Comparator<MatrixCellValue>() {
        public int compare(MatrixCellValue a, MatrixCellValue b) {
          if (a.column < b.column) return -1;
          if (a.column > b.column) return 1;
          if (a.row < b.row) return -1;
          return 1;
        }
      });
      for (int i = 0; i < values.length; i=i) {
        nonZeroColumns.add(values[i].column);
        tmp = values[i].column;
        while(i < values.length && values[i].column == tmp) i++;
      }
      valuesTransposed = new ArrayList<ArrayList<MatrixCellValue>>();
      for (int i = 0; i < nonZeroColumns.size(); i++) {
        valuesTransposed.add(new ArrayList<MatrixCellValue>());
      }
      int columnCounter = 0;
      for (int i = 0; i < values.length; i=i) {
        tmp = values[i].column;
        while (i < values.length && tmp == values[i].column) {
          valuesTransposed.get(columnCounter).add(values[i]);
          i++;
        }
        columnCounter++;
      }
    }
  }

  public void debug() {
    for (int i = 0; i < nonZeroRows.size(); i++) {
      for (int j = 0; j < values.get(i).size(); j++) {
        System.out.println(values.get(i).get(j).toString());
      }
    }
    System.out.println("\n\n");

    for (int i = 0; i < nonZeroColumns.size(); i++) {
      for (int j = 0; j < valuesTransposed.get(i).size(); j++) {
        System.out.println(valuesTransposed.get(i).get(j).toString());
      }
    }

    System.out.println(nonZeroRows);
    System.out.println(nonZeroColumns);

  }

  @Override
  public IDoubleMatrix times(IDoubleMatrix other) {
    int k = this.shape().rows, l = this.shape().columns,
            m = other.shape().rows, n = other.shape().columns;
//  Check if dimensions match.
    assert l == m;
    if (isZero == 1) {
      return DoubleMatrixFactory.zero(Shape.matrix(k, n));
    }
    if (this.getClass() == other.getClass()) {
      if(((SparseMatrix) other).isZero == 1) return DoubleMatrixFactory.zero(Shape.matrix(k, n));

      ArrayList<MatrixCellValue> newValues = new ArrayList<MatrixCellValue>();

      for (int i = 0; i < this.nonZeroRows.size(); i++) {
        for (int j = 0; j < ((SparseMatrix) other).nonZeroColumns.size(); j++) {
          double result = 0;
          for (MatrixCellValue cell : this.values.get(i)) {
            result += cell.value * other.get(cell.column, ((SparseMatrix) other).nonZeroColumns.get(j));
          }
          if (result != 0) {
            MatrixCellValue newCell = new MatrixCellValue(nonZeroRows.get(i), ((SparseMatrix) other).nonZeroColumns.get(j), result);
            newValues.add(newCell);
          }
        }
      }
      MatrixCellValue[] newArray = newValues.toArray(new MatrixCellValue[newValues.size()]);
      return DoubleMatrixFactory.sparse(Shape.matrix(k, n), newArray);
    }
    else {
      return super.times(other);
    }
  }

  @Override
  public IDoubleMatrix plus(IDoubleMatrix other) {
    assert this.shape().equals(other.shape());
    if (isZero == 1) return other;

    if (this.getClass() == other.getClass()) {
      if (((SparseMatrix)other).isZero == 1) return this;
      ArrayList<MatrixCellValue> newValues = new ArrayList<MatrixCellValue>();
      for (int i = 0; i < nonZeroRows.size(); i++) {
        for (MatrixCellValue cell : values.get(i)) {
          double result = cell.value + other.get(cell.row, cell.column);
          if (result != 0) {
            newValues.add(new MatrixCellValue(cell.row, cell.column, result));
          }
        }
      }

      for (int i = 0; i < ((SparseMatrix)other).nonZeroRows.size(); i++) {
        for (MatrixCellValue cell : ((SparseMatrix)other).values.get(i)) {
          if (this.get(cell.row, cell.column) == 0) {
            double result = cell.value;
            if (result != 0) {
              newValues.add(new MatrixCellValue(cell.row, cell.column, result));
            }
          }
        }
      }
      MatrixCellValue[] arr = newValues.toArray(new MatrixCellValue[newValues.size()]);
      return DoubleMatrixFactory.sparse(this.shape(), arr);
    }
    else {
      return super.plus(other);
    }
  }

  @Override
  public IDoubleMatrix times(double scalar) {
    if (isZero == 1) return DoubleMatrixFactory.zero(this.shape());


    ArrayList<MatrixCellValue> newValues = new ArrayList<MatrixCellValue>();
    for (int i = 0; i < nonZeroRows.size(); i++) {
      for (MatrixCellValue cell : values.get(i)) {
        newValues.add(MatrixCellValue.cell(cell.row, cell.column, cell.value * scalar));
      }
    }
    MatrixCellValue[] arr = newValues.toArray(new MatrixCellValue[newValues.size()]);
    return DoubleMatrixFactory.sparse(this.shape(), arr);
  }

  @Override
  public IDoubleMatrix minus(IDoubleMatrix other) {
    assert this.shape().equals(other.shape());
    return this.plus(other.times(-1));
  }

  @Override
  public double get(int row, int column) {
    this.shape().assertInShape(row, column);

    if (isZero == 1) return 0;

    int rowId = Collections.binarySearch(nonZeroRows, row);
    int colId = Collections.binarySearch(nonZeroColumns, column);
    if (rowId >= 0 && colId >= 0) {
      for (int i = 0; i < values.get(rowId).size(); i++) {
        if (values.get(rowId).get(i).column == column) {
          return values.get(rowId).get(i).value;
        }
      }
    }
    return 0;
  }

  @Override
  public double normOne() {
    if (normOne < 0) {

      normOne = 0;
      if (isZero == 1) return normOne;
      double result = 0;
      for (ArrayList<MatrixCellValue> arr : valuesTransposed) {
        for (int i = 0; i < arr.size(); i++) {
          result += Math.abs(arr.get(i).value);
        }
        normOne = Math.max(normOne, result);
        result = 0;
      }
    }
    return normOne;
  }

  @Override
  public double normInfinity() {
    if (normInfinity < 0) {
      normInfinity = 0;
      if (isZero == 1) return normInfinity;
      double result = 0;
      for (ArrayList<MatrixCellValue> arr : values) {
        for (int i = 0; i < arr.size(); i++) {
          result += Math.abs(arr.get(i).value);
        }
        normInfinity = Math.max(normInfinity, result);
        result = 0;
      }
    }
    return normInfinity;
  }

  @Override
  public double frobeniusNorm() {
    if (frobeniusNorm < 0) {
      double result = 0;

      if (isZero == 1) {
        frobeniusNorm = 0;
        return frobeniusNorm;
      }

      for (ArrayList<MatrixCellValue> arr : values) {
        for (int i = 0; i < arr.size(); i++) {
          result += arr.get(i).value * arr.get(i).value;
        }
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
    double data[][] = this.data();
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < k; j++) {
        result.append(data[i][j] + "  ");
      }
      result.append("\n");
    }
    return result.toString();
  }
}
