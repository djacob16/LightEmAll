package org.example;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

// represents LightEmAll game
class LightEmAll extends World {
  ArrayList<ArrayList<GamePiece>> board;
  ArrayList<GamePiece> nodes;
  // minimum spanning tree
  ArrayList<Edge> mst;
  // a list of all edges
  ArrayList<Edge> allEdges;
  // pointers of each gamepiece
  HashMap<GamePiece, GamePiece> hash;
  int width;
  int height;
  int powerRow;
  int powerCol;
  int radius;
  int cellSize = 50;
  boolean playerWins = false;

  // testing constructor #1
  LightEmAll() {
    this.board = new ArrayList<ArrayList<GamePiece>>();
    this.nodes = new ArrayList<GamePiece>();
    // this.mst = new ArrayList<Edge>();
    this.width = 3;
    this.height = 3;
    this.powerRow = 1;
    this.powerCol = 1;
    // this.radius = 10; extra credit
    this.makeNodes();
    this.createBoard();
    this.board.get(powerRow).get(powerCol).powerStation = true;
    this.lightUp();
  }

  // testing constructor #2
  LightEmAll(int size) {
    this.board = new ArrayList<ArrayList<GamePiece>>();
    this.nodes = new ArrayList<GamePiece>();
    this.width = size;
    this.height = size;
    this.powerRow = size / 2;
    this.powerCol = size / 2;
    // this.radius = 10;
    this.makeNodes();
    this.board.get(powerRow).get(powerCol).powerStation = true;
    this.lightUp();
  }

  // testing constructor #3
  LightEmAll(String b) {
    this.board = new ArrayList<ArrayList<GamePiece>>();
    this.nodes = new ArrayList<GamePiece>();
    // this.mst = new ArrayList<Edge>();
    this.width = 3;
    this.height = 3;
    this.powerRow = 1;
    this.powerCol = 1;
    // this.radius = 10;
    this.makeNodes();
    this.createBoard();
    this.board.get(powerRow).get(powerCol).powerStation = true;

  }

  // manual constructor
  LightEmAll(int width, int height) {
    this.board = new ArrayList<ArrayList<GamePiece>>();
    this.nodes = new ArrayList<GamePiece>();
    // this.mst = new ArrayList<Edge>();
    this.width = width;
    this.height = height;
    this.powerRow = width / 2;
    this.powerCol = height / 2;
    // this.radius = 10;
    this.makeNodes();
    this.createAllEdges();
    this.createHashMap();
    this.createBoardPattern();
    this.board.get(powerRow).get(powerCol).powerStation = true;
    this.randomizeBoard();
    this.lightUp();
  }

  // creates the empty board of GamePiece
  public void makeNodes() {
    ArrayList<ArrayList<GamePiece>> result = new ArrayList<ArrayList<GamePiece>>();
    nodes = new ArrayList<>();
    for (int r = 0; r < this.height; r++) {
      ArrayList<GamePiece> row = new ArrayList<GamePiece>();
      for (int c = 0; c < this.width; c++) {
        GamePiece newPiece = new GamePiece(r, c);
        row.add(newPiece);
        nodes.add(newPiece);
      }
      result.add(row);
    }

    this.board = result;
  }

  // creates grid image
  public WorldScene makeScene() {
    WorldScene scene = new WorldScene(this.width * this.cellSize + this.cellSize,
            this.height * this.cellSize + this.cellSize);

    for (int r = 0; r < this.height; r++) {
      for (int c = 0; c < this.width; c++) {
        scene.placeImageXY(this.board.get(r).get(c).draw(), this.cellSize + this.cellSize * c,
                this.cellSize + this.cellSize * r);
      }
    }

    return scene;
  }

  // handles up, down, left, right for moving the power station
  public void onKeyEvent(String key) {
    if (key.equals("up")) {

      if (this.board.get(powerRow).get(powerCol).top
              && this.board.get(powerRow - 1).get(powerCol).bottom) {
        this.board.get(powerRow - 1).get(powerCol).powerStation = true;
        this.board.get(powerRow).get(powerCol).powerStation = false;
        this.powerRow -= 1;
      }
    }

    else if (key.equals("down")) {
      if (this.board.get(powerRow).get(powerCol).bottom
              && this.board.get(powerRow + 1).get(powerCol).top) {
        this.board.get(powerRow + 1).get(powerCol).powerStation = true;
        this.board.get(powerRow).get(powerCol).powerStation = false;
        this.powerRow += 1;
      }
    }

    else if (key.equals("left")) {
      if (this.board.get(powerRow).get(powerCol).left
              && this.board.get(powerRow).get(powerCol - 1).right) {
        this.board.get(powerRow).get(powerCol - 1).powerStation = true;
        this.board.get(powerRow).get(powerCol).powerStation = false;
        this.powerCol -= 1;
      }
    }

    else if (key.equals("right")) {
      if (this.board.get(powerRow).get(powerCol).right
              && this.board.get(powerRow).get(powerCol + 1).left) {
        this.board.get(powerRow).get(powerCol + 1).powerStation = true;
        this.board.get(powerRow).get(powerCol).powerStation = false;
        this.powerCol += 1;
      }
    }
  }

  // creates the final win scene
  public WorldScene makeAFinalScene() {
    WorldScene background = this.makeScene();
    background.placeImageXY(new TextImage("YOU WIN!", 50, Color.GREEN),
            (width * cellSize + cellSize) / 2, (height * cellSize + cellSize) / 2);

    return background;
  }

  // finds the row or col position from initial posn input
  public int findIndex(int posDirection) {
    int result = 0;

    for (int i = 0; i < cellSize * this.height + cellSize / 2; i++) {
      int lower = i * cellSize + cellSize / 2;
      int upper = (i + 1) * cellSize + cellSize / 2;

      if (lower <= posDirection && posDirection < upper) {
        result = i;
      }
    }

    return result;
  }

  // creates perfect board
  public void createBoard() {
    for (int r = 0; r < this.height; r++) {
      for (int c = 0; c < this.width; c++) {
        if (r < this.height - 1) {
          board.get(r).get(c).change("bottom");
        }

        if (r > 0) {
          board.get(r).get(c).change("top");
        }

        if (r == this.height / 2 && c != 0 && c != this.width - 1) {
          board.get(r).get(c).change("left");
          board.get(r).get(c).change("right");
        }

        if (r == this.height / 2 && c == 0) {
          board.get(r).get(c).change("right");
        }

        if (r == this.height / 2 && c == this.width - 1) {
          board.get(r).get(c).change("left");
        }
      }
    }
  }

  // creates master list of connections
  public ArrayList<GamePiece> createMasterList() {
    ArrayList<GamePiece> masterList = new ArrayList<GamePiece>(
            Arrays.asList(this.board.get(powerRow).get(powerCol)));

    for (int i = 0; i < masterList.size(); i++) {
      masterList.get(i).connected(board, masterList);
    }
    return masterList;
  }

  // determines if the player wins
  public void playerWins(ArrayList<GamePiece> lst) {
    playerWins = lst.size() == height * width;
  }

  // handles a left mouse click to turn a game piece
  public void onMouseClicked(Posn pos, String buttonName) {
    int xPos = this.findIndex(pos.x);
    int yPos = this.findIndex(pos.y);
    GamePiece gp = board.get(yPos).get(xPos);

    if (buttonName.equals("LeftButton")) {
      gp.turn();
      for (int r = 0; r < height; r++) {
        for (int c = 0; c < width; c++) {
          this.board.get(r).get(c).powered = false;
        }
        this.lightUp();
      }
      this.playerWins(this.createMasterList());
    }
  }

  // handles a left mouse click to turn a game piece, but takes in a
  // GamePiece instead of a Posn
  public void onMouseClickedTesting(GamePiece gp, String buttonName) {
    if (buttonName.equals("LeftButton")) {
      gp.turn();
      for (int r = 0; r < height; r++) {
        for (int c = 0; c < width; c++) {
          this.board.get(r).get(c).powered = false;
        }
        this.lightUp();
      }
      this.playerWins(this.createMasterList());
    }
  }

  // lights up a connected game piece
  public void lightUp() {
    ArrayList<GamePiece> worklist = this.createMasterList();
    for (int j = 0; j < worklist.size(); j++) {
      worklist.get(j).powered = true;
    }
  }

  // ends the game
  public WorldEnd worldEnds() {
    if (playerWins) {
      System.out.println("Game Ends");
      return new WorldEnd(true, this.makeAFinalScene());
    }
    else {
      return new WorldEnd(false, this.makeScene());
    }
  }

  // randomizes the board
  public void randomizeBoard() {
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        int amount = new Random().nextInt(3);

        for (int i = 0; i < amount; i++) {
          board.get(row).get(col).turn();
        }
      }
    }
  }

  // randomizes board for testing
  public void randomizeBoardTester() {
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        int amount = new Random(3).nextInt(3);

        for (int i = 0; i < amount; i++) {
          board.get(row).get(col).turn();
        }
      }
    }
  }

  public void createAllEdges() {
    this.allEdges = new ArrayList<Edge>();
    for (int r = 0; r < this.height; r++) {
      for (int c = 0; c < this.width; c++) {
        // when the row is less than max row number
        if (r < this.height - 1) {
          board.get(r).get(c).change("bottom");
          this.allEdges.add(new Edge(board.get(r).get(c), board.get(r + 1).get(c)));
        }

        if (r > 0) {
          this.allEdges.add(new Edge(board.get(r).get(c), board.get(r - 1).get(c)));

        }

        if (c < this.width - 1) {
          this.allEdges.add(new Edge(board.get(r).get(c), board.get(r).get(c + 1)));
        }

        if (c > 0) {
          this.allEdges.add(new Edge(board.get(r).get(c), board.get(r).get(c - 1)));
        }
      }
    }
    this.sortEdges(this.allEdges); // sorts the edges in acending order of their weight
  }

  // sorts the edges based on acending order of their randomly generated number
  public <T> void sortEdges(ArrayList<Edge> lst) {
    ArrayList<Edge> workList = new ArrayList<Edge>();
    ArrayList<Edge> newList = new ArrayList<Edge>();
    for (int i = 0; i < allEdges.size(); i++) {
      workList.add(allEdges.get(i));
    }
    while (0 < workList.size()) {
      Edge lowest = workList.get(0);
      for (int k = 0; k > workList.size(); k++) {
        if (workList.get(k).weight < lowest.weight) {
          lowest = workList.get(k);
        }
      }
      newList.add(lowest);
      workList.remove(lowest);
    }
  }

  // creates the initial table where each gamePiece points to itself
  public void createHashMap() {
    hash = new HashMap<>();

    for (GamePiece gp : this.nodes) {
      this.hash.put(gp, gp);
    }
  }

  // create the connection between wires
  public ArrayList<Edge> kruskalSort() {
    mst = new ArrayList<>();
    ArrayList<GamePiece> nodes = this.nodes;

    for (int i = 0; i < allEdges.size(); i++) {

      GamePiece toNode = allEdges.get(i).toNode;
      GamePiece fromNode = allEdges.get(i).fromNode;
      boolean pointToSamePlace = this.findEndPointer(toNode)
              .sameGamePiece(this.findEndPointer(fromNode));
      // if the end pointer of each gamepiece of the edge are not the same --> can add
      // the edge to the list
      if (!pointToSamePlace && nodes.size() >= 1) {
        hash.replace(toNode, fromNode);
        mst.add(allEdges.get(i));
        nodes.remove(toNode);
      }
    }
    return mst;
  }

  // create the patterns of the edges
  public void createBoardPattern() {
    ArrayList<Edge> map = this.kruskalSort();
    for (int i = 0; i < map.size(); i++) {
      map.get(i).toNode.connect(map.get(i).fromNode);
    }
  }

  // find the end pointers of the piece
  public GamePiece findEndPointer(GamePiece gp) {
    // while the end is not pointing to itself
    if (hash.get(gp).sameGamePiece(gp)) {
      return gp;
    }
    else {
      return this.findEndPointer(hash.get(gp));
    }
  }
}
