package cellsociety.model.games;

import cellsociety.model.GameBoard;
import cellsociety.model.cells.Cell;
import cellsociety.model.cells.ConwayCell;
import cellsociety.model.cells.PercolationCell;

public class Percolation extends Simulation {

  public Percolation(String csvConfig){
    super(csvConfig);
  }

  @Override
  public void updateCell(GameBoard gameBoard, int row, int col) {
    if (fullNextGen(row,col)){
      gameBoard.setPiece(row, col, PercolationCell.FULL);
    }
  }

  public boolean fullNextGen(int currentRow, int currentColumn){
    if (!isOpen(currentRow, currentColumn)){
      return false;
    }
    for (int i = currentRow - 1; i <= currentRow + 1; i++){
      for (int j = currentColumn - 1; j <= currentColumn + 1; j++){
        if (getGameBoard().inBounds(i,j) &&
            isDirectNeighbor(i,j,currentRow, currentColumn) &&
            isFull(getGameBoard().getCell(i,j))){
          return true;
        }
      }
    }
    return false;
  }

  public boolean isDirectNeighbor(int x, int y, int currentRow, int currentCol){
    return (x == currentRow || y == currentCol);
  }


  public boolean isFull(Cell cell) {
    return cell.getState().equals(PercolationCell.FULL);
  }

  public boolean isOpen(int row, int col) {
    if(getGameBoard().getCell(row, col).getState().equals(PercolationCell.OPEN)){
      return true;
    }
    return false;
  }

}
