package org.example;

import java.awt.*;
import java.util.ArrayList;

// represents an individual game piece
class GamePiece {
  int row;
  int col;
  boolean left;
  boolean right;
  boolean top;
  boolean bottom;
  boolean powerStation;
  boolean powered;
  int cellSize = 50;

  // normal constructor
  GamePiece(int row, int col) {
    this.row = row;
    this.col = col;
    this.left = false;
    this.right = false;
    this.top = false;
    this.bottom = false;
    this.powerStation = false;
    this.powered = false;
  }

  // testing constructor #1
  GamePiece(boolean left, boolean right, boolean top, boolean bottom) {
    this.row = 0;
    this.col = 0;
    this.left = left;
    this.right = right;
    this.top = top;
    this.bottom = bottom;
    this.powerStation = false;
    this.powered = false;
  }

  // testing constructor #2
  GamePiece(int row, int col, boolean left, boolean right, boolean top, boolean bottom) {
    this.row = row;
    this.col = col;
    this.left = left;
    this.right = right;
    this.top = top;
    this.bottom = bottom;
    this.powerStation = false;
    this.powered = false;
  }

  // draws a game piece
  public WorldImage draw() {
    WorldImage backgroundPiece = new OverlayImage(
            new RectangleImage(cellSize - 1, cellSize - 1, "solid", Color.gray),
            new RectangleImage(cellSize, cellSize, "solid", Color.black));
    Color color = Color.lightGray;
    if (powered) {
      color = Color.yellow;
    }

    if (this.left) {
      backgroundPiece = new OverlayOffsetAlign(AlignModeX.LEFT, AlignModeY.MIDDLE,
              new RectangleImage(cellSize / 2 - 1, cellSize / 8, "solid", color), -1, 0,
              backgroundPiece);
    }

    if (this.right) {
      backgroundPiece = new OverlayOffsetAlign(AlignModeX.RIGHT, AlignModeY.MIDDLE,
              new RectangleImage(cellSize / 2 - 1, cellSize / 8, "solid", color), 1, 0,
              backgroundPiece);
    }

    if (this.top) {
      backgroundPiece = new OverlayOffsetAlign(AlignModeX.CENTER, AlignModeY.TOP,
              new RectangleImage(cellSize / 8, cellSize / 2 - 1, "solid", color), 0, -1,
              backgroundPiece);
    }

    if (this.bottom) {
      backgroundPiece = new OverlayOffsetAlign(AlignModeX.CENTER, AlignModeY.BOTTOM,
              new RectangleImage(cellSize / 8, cellSize / 2 - 1, "solid", color), 0, 1,
              backgroundPiece);
    }

    if (powerStation) {
      backgroundPiece = new OverlayImage(
              new OverlayImage(
                      new StarImage(cellSize / 3, 7, OutlineMode.OUTLINE, new Color(255, 128, 0)),
                      new StarImage(cellSize / 3, 7, OutlineMode.SOLID, new Color(0, 255, 255))),
              backgroundPiece);
    }

    return backgroundPiece;
  }

  // changes a game piece's respective field to true
  public void change(String field) {
    if (field.equals("left")) {
      this.left = true;
    }

    if (field.equals("right")) {
      this.right = true;
    }

    if (field.equals("top")) {
      this.top = true;
    }

    if (field.equals("bottom")) {
      this.bottom = true;
    }
  }

  // helps determine if game pieces are connected
  public ArrayList<GamePiece> connected(ArrayList<ArrayList<GamePiece>> board,
                                        ArrayList<GamePiece> workList) {
    this.powered = true;
    if (this.left && board.get(this.row).get(this.col - 1).right
            && !workList.contains(board.get(this.row).get(this.col - 1))) {
      board.get(this.row).get(this.col - 1).powered = true;
      workList.add(board.get(this.row).get(this.col - 1));

    }

    if (this.right && board.get(this.row).get(this.col + 1).left
            && !workList.contains(board.get(this.row).get(this.col + 1))) {
      board.get(this.row).get(this.col + 1).powered = true;
      workList.add(board.get(this.row).get(this.col + 1));
    }
    if (this.top && board.get(this.row - 1).get(this.col).bottom
            && !workList.contains(board.get(this.row - 1).get(this.col))) {
      workList.add(board.get(this.row - 1).get(this.col));
      board.get(this.row - 1).get(this.col).powered = true;
    }

    if (this.bottom && board.get(this.row + 1).get(this.col).top
            && !workList.contains(board.get(this.row + 1).get(this.col))) {
      workList.add(board.get(this.row + 1).get(this.col));
      board.get(this.row + 1).get(this.col).powered = true;
    }
    return workList;
  }

  // turns the tile clockwise
  public void turn() {
    boolean topTemp = this.top;
    boolean rightTemp = this.right;
    boolean bottomTemp = this.bottom;
    boolean leftTemp = this.left;

    this.top = leftTemp;
    this.right = topTemp;
    this.bottom = rightTemp;
    this.left = bottomTemp;
  }

  // determines if this GamePiece is the same is that GamePiece
  public boolean sameGamePiece(GamePiece that) {
    return this.row == that.row && this.col == that.col && this.left == that.left
            && this.right == that.right && this.top == that.top && this.bottom == that.bottom
            && this.powerStation == that.powerStation && this.powered == that.powered;
  }

  // connect the two gamepiece within edge
  public void connect(GamePiece that) {
    if (this.row == that.row - 1 && this.col == that.col) {
      this.bottom = true;
      that.top = true;
    }
    else if (this.row == that.row + 1 && this.col == that.col) {
      this.top = true;
      that.bottom = true;
    }
    else if (this.col == that.col - 1 && this.row == that.row) {
      this.right = true;
      that.left = true;
    }
    else if (this.col == that.col + 1 && this.row == that.row) {
      this.left = true;
      that.right = true;
    }
  }
}
