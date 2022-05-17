package pl.edu.mimuw.matrix;
import java.util.*;


public final class SparseMatrix extends DoubleMatrix implements IDoubleMatrix {
  private ArrayList<ArrayList<MatrixCellValue>> values;
  private ArrayList<ArrayList<MatrixCellValue>> valuesTransposed;
  private ArrayList<Integer> nonZeroRows;
  private ArrayList<Integer> nonZeroColumns;

  public SparseMatrix(Shape shape, MatrixCellValue... values) {
    super(shape.rows, shape.columns);
    //    How many non-zero rows are there?
    Arrays.sort(values);
    int tmp;
    for (int i = 0; i < values.length; i=i) {
      nonZeroRows.add(values[i].row);
      tmp = values[i].row;
      while(values[i].row == tmp && i < values.length) i++;
    }

    this.values = new ArrayList<ArrayList<MatrixCellValue>>(nonZeroRows.size());
    int rowCounter = 0;
    for (int i = 0; i < values.length; i=i) {
      tmp = values[i].row;
      while (tmp == values[i].row && i < values.length) {
        this.values.get(rowCounter).add(values[i]);
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
      while(values[i].column == tmp && i < values.length) i++;
    }
    valuesTransposed = new ArrayList<ArrayList<MatrixCellValue>>(nonZeroColumns.size());
    int columnCounter = 0;
    for (int i = 0; i < values.length; i=i) {
      tmp = values[i].column;
      while (tmp == values[i].column && i < values.length) {
        valuesTransposed.get(columnCounter).add(values[i]);
      }
      columnCounter++;
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


  }


  @Override public double get(int row, int column){return 0;}

  @Override public double normOne(){return 0;}

  @Override public double normInfinity(){return 0;}

  @Override public double frobeniusNorm(){return 0;}

  @Override public String toString(){return "0";}
}
