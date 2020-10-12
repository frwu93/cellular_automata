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

  @Override
  public void nextGen() {
    for (int i = 0; i < getGameBoard().getHeight(); i++) {
      for (int j = 0; j < getGameBoard().getWidth(); j++) {
        updateCell(getGameBoard(), i,j);
      }
    }
    incrementGeneration();
  }

  public boolean fullNextGen(int currentRow, int currentColumn){
    for (int i = currentRow - 1; i <= currentRow + 1; i++){
      for (int j = currentColumn - 1; j <= currentColumn + 1; j++){
        return (getGameBoard().inBounds(i,j) &&
            isDirectNeighbor(i,j,currentRow, currentColumn) &&
            isFull(getGameBoard().getCell(i,j)) &&
            isOpen(getGameBoard().getCell(currentRow, currentColumn)));
      }
    }
    return false;
  }

  public boolean isDirectNeighbor(int x, int y, int currentRow, int currentCol){
    if( x == currentRow || y == currentCol){
      System.out.println(x + " " + y);
      return true;
    }
    return false;
  }


  public boolean isFull(Cell cell) {
    return cell.getState().equals(PercolationCell.FULL);
  }

  public boolean isOpen(Cell cell) {
    return cell.getState().equals(PercolationCell.OPEN);
  }

}
