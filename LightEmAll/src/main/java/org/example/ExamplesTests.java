package org.example;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

// tests and examples for LightEmAll
class ExamplesTests {
  int cellSize = 50;

  LightEmAll leaBase;
  LightEmAll leaBeforeCreation;
  LightEmAll leaMax;
  LightEmAll world1;

  GamePiece gp1;
  GamePiece gp2;
  GamePiece gp3;
  GamePiece gp4;
  GamePiece gp5;
  GamePiece gp6;
  GamePiece gp7;
  GamePiece gp8;
  GamePiece gp9;

  GamePiece gpL;
  GamePiece gpR;
  GamePiece gpT;
  GamePiece gpT1;
  GamePiece gpT2;
  GamePiece gpB;
  GamePiece gpB1;
  GamePiece gpB2;
  GamePiece gpTBR;
  GamePiece gpTBL;
  GamePiece gpTBRL;

  WorldScene background;

  WorldImage baseGamePiece;
  WorldImage singleDownG;
  WorldImage singleUpG;
  WorldImage upDownG;
  WorldImage upDownRightG;
  WorldImage upDownLeftG;
  WorldImage crossG;
  WorldImage powerStationG;
  WorldImage singleDownY;
  WorldImage singleUpY;
  WorldImage upDownY;
  WorldImage upDownRightY;
  WorldImage upDownLeftY;
  WorldImage crossY;
  WorldImage powerStationY;

  ArrayList<GamePiece> tempMasterList;
  ArrayList<GamePiece> trueMasterList;
  ArrayList<GamePiece> falseMasterList;

  HashMap<GamePiece, GamePiece> pieceHash;

  // initial conditions #1
  public void initialConditions1() {
    leaBase = new LightEmAll();
    leaBeforeCreation = new LightEmAll(3);
    leaMax = new LightEmAll(8);
  }

  // initial conditions #2
  public void initialConditions2() {
    leaBase = new LightEmAll();
    gp1 = new GamePiece(0, 0);
    gp2 = new GamePiece(true, false, true, false);
    gpL = new GamePiece(true, false, false, false);
    gpR = new GamePiece(false, true, false, false);
    gpB = new GamePiece(0, 0, false, false, false, true);
    gpT = new GamePiece(2, 0, false, false, true, false);
    gpTBR = new GamePiece(1, 0, false, true, true, true);
    gpTBRL = new GamePiece(1, 1, true, true, true, true);
  }

  // initial conditions #3
  public void initialConditions3() {
    leaBase = new LightEmAll();
    leaBeforeCreation = new LightEmAll(3);
    leaMax = new LightEmAll(8);
    gpB = new GamePiece(0, 0, false, false, false, true);
    gpB1 = new GamePiece(0, 1, false, false, false, true);
    gpB2 = new GamePiece(0, 2, false, false, false, true);
    gpT = new GamePiece(2, 0, false, false, true, false);
    gpT1 = new GamePiece(2, 1, false, false, true, false);
    gpT2 = new GamePiece(2, 2, false, false, true, false);
    gpTBR = new GamePiece(1, 0, false, true, true, true);
    gpTBL = new GamePiece(1, 2, true, false, true, true);
    gpTBRL = new GamePiece(1, 1, true, true, true, true);
    trueMasterList = new ArrayList<GamePiece>(
            Arrays.asList(gpB, gpB1, gpB2, gpTBR, gpTBRL, gpTBL, gpT, gpT1, gpT2));
    falseMasterList = new ArrayList<GamePiece>(Arrays.asList(gpB, gpTBR, gpTBRL, gpTBL, gpT));
  }

  // initial conditions #4
  public void initialConditionsMakeScene() {
    leaBase = new LightEmAll();
    background = new WorldScene(200, 200);
    baseGamePiece = new OverlayImage(
            new RectangleImage(cellSize - 1, cellSize - 1, "solid", Color.gray),
            new RectangleImage(cellSize, cellSize, "solid", Color.black));
    singleDownG = new OverlayOffsetAlign(AlignModeX.CENTER, AlignModeY.BOTTOM,
            new RectangleImage(cellSize / 8, cellSize / 2 - 1, "solid", Color.lightGray), 0, 1,
            baseGamePiece);
    singleUpG = new OverlayOffsetAlign(AlignModeX.CENTER, AlignModeY.TOP,
            new RectangleImage(cellSize / 8, cellSize / 2 - 1, "solid", Color.lightGray), 0, -1,
            baseGamePiece);
    upDownG = new OverlayOffsetAlign(AlignModeX.CENTER, AlignModeY.BOTTOM,
            new RectangleImage(cellSize / 8, cellSize / 2 - 1, "solid", Color.lightGray), 0, 1,
            new OverlayOffsetAlign(AlignModeX.CENTER, AlignModeY.TOP,
                    new RectangleImage(cellSize / 8, cellSize / 2 - 1, "solid", Color.lightGray), 0, -1,
                    baseGamePiece));
    upDownRightG = new OverlayOffsetAlign(AlignModeX.CENTER, AlignModeY.BOTTOM,
            new RectangleImage(cellSize / 8, cellSize / 2 - 1, "solid", Color.lightGray), 0, 1,
            new OverlayOffsetAlign(AlignModeX.CENTER, AlignModeY.TOP,
                    new RectangleImage(cellSize / 8, cellSize / 2 - 1, "solid", Color.lightGray), 0, -1,
                    new OverlayOffsetAlign(AlignModeX.RIGHT, AlignModeY.MIDDLE,
                            new RectangleImage(cellSize / 2 - 1, cellSize / 8, "solid", Color.lightGray), 1, 0,
                            baseGamePiece)));
    upDownLeftG = new OverlayOffsetAlign(AlignModeX.CENTER, AlignModeY.BOTTOM,
            new RectangleImage(cellSize / 8, cellSize / 2 - 1, "solid", Color.lightGray), 0, 1,
            new OverlayOffsetAlign(AlignModeX.CENTER, AlignModeY.TOP,
                    new RectangleImage(cellSize / 8, cellSize / 2 - 1, "solid", Color.lightGray), 0, -1,
                    new OverlayOffsetAlign(AlignModeX.LEFT, AlignModeY.MIDDLE,
                            new RectangleImage(cellSize / 2 - 1, cellSize / 8, "solid", Color.lightGray), -1, 0,
                            baseGamePiece)));
    crossG = new OverlayOffsetAlign(AlignModeX.CENTER, AlignModeY.BOTTOM,
            new RectangleImage(cellSize / 8, cellSize / 2 - 1, "solid", Color.lightGray), 0, 1,
            new OverlayOffsetAlign(AlignModeX.CENTER, AlignModeY.TOP,
                    new RectangleImage(cellSize / 8, cellSize / 2 - 1, "solid", Color.lightGray), 0, -1,
                    new OverlayOffsetAlign(AlignModeX.RIGHT, AlignModeY.MIDDLE,
                            new RectangleImage(cellSize / 2 - 1, cellSize / 8, "solid", Color.lightGray), 1, 0,
                            new OverlayOffsetAlign(AlignModeX.LEFT, AlignModeY.MIDDLE,
                                    new RectangleImage(cellSize / 2 - 1, cellSize / 8, "solid", Color.lightGray),
                                    -1, 0, baseGamePiece))));
    powerStationG = new OverlayImage(new OverlayImage(
            new StarImage(cellSize / 3, 7, OutlineMode.OUTLINE, new Color(255, 128, 0)),
            new StarImage(cellSize / 3, 7, OutlineMode.SOLID, new Color(0, 255, 255))), crossG);
    singleDownY = new OverlayOffsetAlign(AlignModeX.CENTER, AlignModeY.BOTTOM,
            new RectangleImage(cellSize / 8, cellSize / 2 - 1, "solid", Color.yellow), 0, 1,
            baseGamePiece);
    singleUpY = new OverlayOffsetAlign(AlignModeX.CENTER, AlignModeY.TOP,
            new RectangleImage(cellSize / 8, cellSize / 2 - 1, "solid", Color.yellow), 0, -1,
            baseGamePiece);
    upDownY = new OverlayOffsetAlign(AlignModeX.CENTER, AlignModeY.BOTTOM,
            new RectangleImage(cellSize / 8, cellSize / 2 - 1, "solid", Color.yellow), 0, 1,
            new OverlayOffsetAlign(AlignModeX.CENTER, AlignModeY.TOP,
                    new RectangleImage(cellSize / 8, cellSize / 2 - 1, "solid", Color.yellow), 0, -1,
                    baseGamePiece));
    upDownRightY = new OverlayOffsetAlign(AlignModeX.CENTER, AlignModeY.BOTTOM,
            new RectangleImage(cellSize / 8, cellSize / 2 - 1, "solid", Color.yellow), 0, 1,
            new OverlayOffsetAlign(AlignModeX.CENTER, AlignModeY.TOP,
                    new RectangleImage(cellSize / 8, cellSize / 2 - 1, "solid", Color.yellow), 0, -1,
                    new OverlayOffsetAlign(AlignModeX.RIGHT, AlignModeY.MIDDLE,
                            new RectangleImage(cellSize / 2 - 1, cellSize / 8, "solid", Color.yellow), 1, 0,
                            baseGamePiece)));
    upDownLeftY = new OverlayOffsetAlign(AlignModeX.CENTER, AlignModeY.BOTTOM,
            new RectangleImage(cellSize / 8, cellSize / 2 - 1, "solid", Color.yellow), 0, 1,
            new OverlayOffsetAlign(AlignModeX.CENTER, AlignModeY.TOP,
                    new RectangleImage(cellSize / 8, cellSize / 2 - 1, "solid", Color.yellow), 0, -1,
                    new OverlayOffsetAlign(AlignModeX.LEFT, AlignModeY.MIDDLE,
                            new RectangleImage(cellSize / 2 - 1, cellSize / 8, "solid", Color.yellow), -1, 0,
                            baseGamePiece)));
    crossY = new OverlayOffsetAlign(AlignModeX.CENTER, AlignModeY.BOTTOM,
            new RectangleImage(cellSize / 8, cellSize / 2 - 1, "solid", Color.yellow), 0, 1,
            new OverlayOffsetAlign(AlignModeX.CENTER, AlignModeY.TOP,
                    new RectangleImage(cellSize / 8, cellSize / 2 - 1, "solid", Color.yellow), 0, -1,
                    new OverlayOffsetAlign(AlignModeX.RIGHT, AlignModeY.MIDDLE,
                            new RectangleImage(cellSize / 2 - 1, cellSize / 8, "solid", Color.yellow), 1, 0,
                            new OverlayOffsetAlign(AlignModeX.LEFT, AlignModeY.MIDDLE,
                                    new RectangleImage(cellSize / 2 - 1, cellSize / 8, "solid", Color.yellow), -1,
                                    0, baseGamePiece))));
    powerStationY = new OverlayImage(new OverlayImage(
            new StarImage(cellSize / 3, 7, OutlineMode.OUTLINE, new Color(255, 128, 0)),
            new StarImage(cellSize / 3, 7, OutlineMode.SOLID, new Color(0, 255, 255))), crossY);

    background.placeImageXY(singleDownY, 50, 50);
    background.placeImageXY(singleDownY, 100, 50);
    background.placeImageXY(singleDownY, 150, 50);
    background.placeImageXY(upDownRightY, 50, 100);
    background.placeImageXY(powerStationY, 100, 100);
    background.placeImageXY(upDownLeftY, 150, 100);
    background.placeImageXY(singleUpY, 50, 150);
    background.placeImageXY(singleUpY, 100, 150);
    background.placeImageXY(singleUpY, 150, 150);
  }

  // initial conditions #5
  public void initialConditionsMakeFinalScene() {
    leaBase = new LightEmAll();
    background = leaBase.makeScene();

    background.placeImageXY(new TextImage("YOU WIN!", 50, Color.GREEN),
            (this.leaBase.width * this.cellSize + this.cellSize) / 2,
            (this.leaBase.height * this.cellSize + this.cellSize) / 2);
  }

  // initial condition #6
  public void initialCondNonLit() {
    world1 = new LightEmAll("b");
  }

  // test for makeNodes
  void testMakeNodes(Tester t) {
    this.initialConditions1();
    // checks base board, making sure nodes constructed
    t.checkExpect(this.leaMax.board.size(), 8);
    // checking each row of nodes
    t.checkExpect(this.leaMax.board.get(0).size(), 8);
    t.checkExpect(this.leaMax.board.get(1).size(), 8);
    t.checkExpect(this.leaMax.board.get(2).size(), 8);
    t.checkExpect(this.leaMax.board.get(3).size(), 8);
    t.checkExpect(this.leaMax.board.get(4).size(), 8);
    t.checkExpect(this.leaMax.board.get(5).size(), 8);
    t.checkExpect(this.leaMax.board.get(6).size(), 8);
    t.checkExpect(this.leaMax.board.get(7).size(), 8);
  }

  // test for makeScene
  void testMakeScene(Tester t) {
    this.initialConditionsMakeScene();
    t.checkExpect(leaBase.makeScene(), background);
  }

  // test for onKeyEvent
  void testOnKeyEvent(Tester t) {
    // checks "up" key
    this.initialConditions1();
    t.checkExpect(leaBase.board.get(1).get(1).powerStation, true);
    t.checkExpect(leaBase.board.get(0).get(1).powerStation, false);
    leaBase.onKeyEvent("up");
    t.checkExpect(leaBase.board.get(1).get(1).powerStation, false);
    t.checkExpect(leaBase.board.get(0).get(1).powerStation, true);
    // checks "down" key
    this.initialConditions1();
    t.checkExpect(leaBase.board.get(1).get(1).powerStation, true);
    t.checkExpect(leaBase.board.get(2).get(1).powerStation, false);
    leaBase.onKeyEvent("down");
    t.checkExpect(leaBase.board.get(1).get(1).powerStation, false);
    t.checkExpect(leaBase.board.get(2).get(1).powerStation, true);
    // checks "left" key
    this.initialConditions1();
    t.checkExpect(leaBase.board.get(1).get(1).powerStation, true);
    t.checkExpect(leaBase.board.get(1).get(0).powerStation, false);
    leaBase.onKeyEvent("left");
    t.checkExpect(leaBase.board.get(1).get(1).powerStation, false);
    t.checkExpect(leaBase.board.get(1).get(0).powerStation, true);
    // checks "right" key
    this.initialConditions1();
    t.checkExpect(leaBase.board.get(1).get(1).powerStation, true);
    t.checkExpect(leaBase.board.get(1).get(2).powerStation, false);
    leaBase.onKeyEvent("right");
    t.checkExpect(leaBase.board.get(1).get(1).powerStation, false);
    t.checkExpect(leaBase.board.get(1).get(2).powerStation, true);
  }

  // test for makeAFinalScene
  void testMakeAFinalScene(Tester t) {
    this.initialConditionsMakeFinalScene();
    t.checkExpect(this.leaBase.makeAFinalScene(), background);
  }

  // test for FindIndex
  void testFindIndex(Tester t) {
    this.initialConditions1();
    t.checkExpect(this.leaMax.findIndex(352), 6);
    t.checkExpect(this.leaMax.findIndex(200), 3);
    t.checkExpect(this.leaMax.findIndex(100000), 0);
  }

  // test for createBoard
  void testCreateBoard(Tester t) {
    this.initialConditions1();

    // checks only bottom tile before method call
    t.checkExpect(this.leaBeforeCreation.board.get(0).get(0).top, false);
    t.checkExpect(this.leaBeforeCreation.board.get(0).get(0).bottom, false);
    t.checkExpect(this.leaBeforeCreation.board.get(0).get(0).left, false);
    t.checkExpect(this.leaBeforeCreation.board.get(0).get(0).right, false);
    // checks only top tile before method call
    t.checkExpect(this.leaBeforeCreation.board.get(2).get(0).top, false);
    t.checkExpect(this.leaBeforeCreation.board.get(2).get(0).bottom, false);
    t.checkExpect(this.leaBeforeCreation.board.get(2).get(0).left, false);
    t.checkExpect(this.leaBeforeCreation.board.get(2).get(0).right, false);
    // checks top/bottom/right tile before method call
    t.checkExpect(this.leaBeforeCreation.board.get(1).get(0).top, false);
    t.checkExpect(this.leaBeforeCreation.board.get(1).get(0).bottom, false);
    t.checkExpect(this.leaBeforeCreation.board.get(1).get(0).left, false);
    t.checkExpect(this.leaBeforeCreation.board.get(1).get(0).right, false);
    // checks top/bottom/left tile before method call
    t.checkExpect(this.leaBeforeCreation.board.get(1).get(2).top, false);
    t.checkExpect(this.leaBeforeCreation.board.get(1).get(2).bottom, false);
    t.checkExpect(this.leaBeforeCreation.board.get(1).get(2).left, false);
    t.checkExpect(this.leaBeforeCreation.board.get(1).get(2).right, false);

    this.leaBeforeCreation.createBoard();

    // checks only bottom tile after method call
    t.checkExpect(this.leaBeforeCreation.board.get(0).get(0).top, false);
    t.checkExpect(this.leaBeforeCreation.board.get(0).get(0).bottom, true);
    t.checkExpect(this.leaBeforeCreation.board.get(0).get(0).left, false);
    t.checkExpect(this.leaBeforeCreation.board.get(0).get(0).right, false);
    // checks only top tile after method call
    t.checkExpect(this.leaBeforeCreation.board.get(2).get(0).top, true);
    t.checkExpect(this.leaBeforeCreation.board.get(2).get(0).bottom, false);
    t.checkExpect(this.leaBeforeCreation.board.get(2).get(0).left, false);
    t.checkExpect(this.leaBeforeCreation.board.get(2).get(0).right, false);
    // checks top/bottom/right tile after method call
    t.checkExpect(this.leaBeforeCreation.board.get(1).get(0).top, true);
    t.checkExpect(this.leaBeforeCreation.board.get(1).get(0).bottom, true);
    t.checkExpect(this.leaBeforeCreation.board.get(1).get(0).left, false);
    t.checkExpect(this.leaBeforeCreation.board.get(1).get(0).right, true);
    // checks top/bottom/left tile after method call
    t.checkExpect(this.leaBeforeCreation.board.get(1).get(2).top, true);
    t.checkExpect(this.leaBeforeCreation.board.get(1).get(2).bottom, true);
    t.checkExpect(this.leaBeforeCreation.board.get(1).get(2).left, true);
    t.checkExpect(this.leaBeforeCreation.board.get(1).get(2).right, false);
  }

  // test for worldEnds
  void testWorldEnds(Tester t) {
    this.initialConditions1();
    t.checkExpect(leaBase.worldEnds(), new WorldEnd(leaBase.playerWins, leaBase.makeScene()));

    this.initialConditions1();
    leaBase.playerWins = true;
    t.checkExpect(leaBase.worldEnds(), new WorldEnd(leaBase.playerWins, leaBase.makeAFinalScene()));
  }

  // test for draw
  void testDraw(Tester t) {
    // unpowered gamePiece
    this.initialConditions2();
    initialConditionsMakeScene();
    gp1.top = true;
    t.checkExpect(gp1.draw(), singleUpG);
    gp1.bottom = true;
    t.checkExpect(gp1.draw(), upDownG);
    gp1.right = true;
    t.checkExpect(gp1.draw(), upDownRightG);
    gp1.left = true;
    t.checkExpect(gp1.draw(), crossG);
    gp1.powerStation = true;
    t.checkExpect(gp1.draw(), powerStationG);
    // powered gamePiece
    this.initialConditions2();
    initialConditionsMakeScene();
    gp1.powered = true;
    gp1.top = true;
    t.checkExpect(gp1.draw(), singleUpY);
    gp1.bottom = true;
    t.checkExpect(gp1.draw(), upDownY);
    gp1.right = true;
    t.checkExpect(gp1.draw(), upDownRightY);
    gp1.left = true;
    t.checkExpect(gp1.draw(), crossY);
    gp1.powerStation = true;
    t.checkExpect(gp1.draw(), powerStationY);
  }

  // test for change
  void testChange(Tester t) {
    // checks changes left
    this.initialConditions2();
    t.checkExpect(this.gp1.left, false);
    gp1.change("left");
    t.checkExpect(this.gp1.left, true);
    // checks changes right
    this.initialConditions2();
    t.checkExpect(this.gp1.right, false);
    gp1.change("right");
    t.checkExpect(this.gp1.right, true);
    // checks changes top
    this.initialConditions2();
    t.checkExpect(this.gp1.top, false);
    gp1.change("top");
    t.checkExpect(this.gp1.top, true);
    // checks changes bottom
    this.initialConditions2();
    t.checkExpect(this.gp1.bottom, false);
    gp1.change("bottom");
    t.checkExpect(this.gp1.bottom, true);
  }

  // test for turn
  void testTurn(Tester t) {
    this.initialConditions2();
    t.checkExpect(this.gp2.left, true);
    t.checkExpect(this.gp2.right, false);
    t.checkExpect(this.gp2.top, true);
    t.checkExpect(this.gp2.bottom, false);

    gp2.turn();
    t.checkExpect(this.gp2.left, false);
    t.checkExpect(this.gp2.right, true);
    t.checkExpect(this.gp2.top, true);
    t.checkExpect(this.gp2.bottom, false);

    gp2.turn();
    t.checkExpect(this.gp2.left, false);
    t.checkExpect(this.gp2.right, true);
    t.checkExpect(this.gp2.top, false);
    t.checkExpect(this.gp2.bottom, true);

    gp2.turn();
    t.checkExpect(this.gp2.left, true);
    t.checkExpect(this.gp2.right, false);
    t.checkExpect(this.gp2.top, false);
    t.checkExpect(this.gp2.bottom, true);

    gp2.turn();
    t.checkExpect(this.gp2.left, true);
    t.checkExpect(this.gp2.right, false);
    t.checkExpect(this.gp2.top, true);
    t.checkExpect(this.gp2.bottom, false);
  }

  // test for connected
  void testConnected(Tester t) {
    this.initialConditions2();
    gpB.connected(leaBase.board, new ArrayList<GamePiece>(Arrays.asList(gpB)));
    gpTBR.powered = true;
    t.checkExpect(gpB.connected(leaBase.board, new ArrayList<GamePiece>(Arrays.asList(gpB))),
            new ArrayList<GamePiece>(Arrays.asList(gpB, gpTBR)));

    gpTBR.connected(leaBase.board, new ArrayList<GamePiece>(Arrays.asList(gpB, gpTBR)));
    gpT.powered = true;
    gpTBRL.powered = true;
    gpTBRL.powerStation = true;
    t.checkExpect(gpTBR.connected(leaBase.board, new ArrayList<GamePiece>(Arrays.asList(gpTBR))),
            new ArrayList<GamePiece>(Arrays.asList(gpTBR, gpTBRL, gpB, gpT)));
  }

  // test for createMasterList
  void testCreateMasterList(Tester t) {
    this.initialConditions2();
    tempMasterList = leaBase.createMasterList();

    t.checkExpect(tempMasterList.contains(leaBase.board.get(1).get(1)), true);
    t.checkExpect(tempMasterList.contains(leaBase.board.get(0).get(1)), true);
    t.checkExpect(tempMasterList.contains(leaBase.board.get(1).get(0)), true);
    t.checkExpect(tempMasterList.contains(leaBase.board.get(2).get(1)), true);
    t.checkExpect(tempMasterList.contains(leaBase.board.get(1).get(2)), true);
  }

  // test for playerWins
  void testPlayerWins(Tester t) {
    this.initialConditions3();
    leaBase.playerWins(trueMasterList);
    t.checkExpect(leaBase.playerWins, true);
    leaBase.playerWins(falseMasterList);
    t.checkExpect(leaBase.playerWins, false);
  }

  // test for onMouseClicked
  void testOnMouseClicked(Tester t) {
    this.initialConditions2();
    leaBase.onMouseClickedTesting(gp2, "LeftButton");
    t.checkExpect(gp2.left, false);
    t.checkExpect(gp2.right, true);
    t.checkExpect(gp2.top, true);
    t.checkExpect(gp2.bottom, false);
  }

  // test for lightUp
  void testLightUp(Tester t) {
    this.initialCondNonLit();
    world1.lightUp();
    t.checkExpect(world1.board.get(0).get(0).draw(),
            new OverlayOffsetAlign(AlignModeX.CENTER, AlignModeY.BOTTOM,
                    new RectangleImage(cellSize / 8, cellSize / 2 - 1, "solid", Color.yellow), 0, 1,
                    new OverlayImage(new RectangleImage(cellSize - 1, cellSize - 1, "solid", Color.gray),
                            new RectangleImage(cellSize, cellSize, "solid", Color.black))));
    world1.board.get(0).get(0).turn();
    world1.board.get(0).get(0).turn();
    world1.board.get(0).get(0).powered = false;
    world1.lightUp();
    t.checkExpect(world1.board.get(0).get(0).draw(),
            new OverlayOffsetAlign(AlignModeX.CENTER, AlignModeY.TOP,
                    new RectangleImage(cellSize / 8, cellSize / 2 - 1, "solid", Color.lightGray), 0, -1,
                    new OverlayImage(new RectangleImage(cellSize - 1, cellSize - 1, "solid", Color.gray),
                            new RectangleImage(cellSize, cellSize, "solid", Color.black))));
  }

  // test for randomizeBoard
  void testRandomizeBoard(Tester t) {
    this.initialCondNonLit();
    // before the randomization, all of the game pieces should be connected
    t.checkExpect(world1.createMasterList().size(), 9);
    // after calling the method the world should be mix thus the pieces are no
    // longer tied together
    world1.randomizeBoardTester();
    t.checkExpect(world1.createMasterList().size(), 1);
  }

  void hashInitialCond() {
    world1 = new LightEmAll("b");
    gp1 = new GamePiece(0, 0, false, false, false, false);
    gp2 = new GamePiece(0, 1, false, false, false, false);
    gp3 = new GamePiece(0, 2, false, false, false, false);
    gp4 = new GamePiece(1, 0, false, false, false, false);
    gp5 = new GamePiece(1, 1, false, false, false, false);
    pieceHash = new HashMap<>();
    pieceHash.put(gp1, gp2);
    pieceHash.put(gp2, gp3);
    pieceHash.put(gp3, gp3);
    pieceHash.put(gp4, gp5);
    world1.hash = pieceHash;
  }

  // test find end pointer
  void testEndPointer(Tester t) {

    this.hashInitialCond();
    // test a pointer to itself
    t.checkExpect(world1.findEndPointer(gp3), gp3);
    // check if pointer recursively works
    t.checkExpect(world1.findEndPointer(gp2), gp3);
    t.checkExpect(world1.findEndPointer(gp1), gp3);
  }

  // test if the gamepieces are the same piece
  void initialCondSamePiece() {
    gp1 = new GamePiece(1, 0, false, false, false, false);
    gp2 = new GamePiece(1, 0, false, false, false, false);
    gp3 = new GamePiece(0, 0, false, false, false, false);
    gp4 = new GamePiece(1, 0, false, false, false, false);
    gp5 = new GamePiece(1, 0, false, true, false, false);
    gp6 = new GamePiece(1, 0, false, false, false, false);
  }

  // test if two nodes are the same
  void testSameNode(Tester t) {
    this.initialCondSamePiece();
    t.checkExpect(gp1.sameGamePiece(gp2), true);
    t.checkExpect(gp3.sameGamePiece(gp4), false);
    t.checkExpect(gp5.sameGamePiece(gp6), false);

  }

  // initial conditon of connect
  void initialCondConnect() {
    gp1 = new GamePiece(0, 0, false, false, false, false);
    gp2 = new GamePiece(0, 1, false, false, false, false);
    gp3 = new GamePiece(0, 2, false, false, false, false);
    gp4 = new GamePiece(1, 0, false, false, false, false);
    gp5 = new GamePiece(1, 1, false, false, false, false);
    gp6 = new GamePiece(1, 2, false, false, false, false);
    gp7 = new GamePiece(2, 0, false, false, false, false);
    gp8 = new GamePiece(2, 1, false, false, false, false);
    gp9 = new GamePiece(2, 2, false, false, false, false);

  }

  // test connect
  void testConnect(Tester t) {
    this.initialCondConnect();
    // connect the left to the right
    t.checkExpect(gp1.right, false);
    t.checkExpect(gp2.left, false);
    gp1.connect(gp2);
    t.checkExpect(gp1.right, true);
    t.checkExpect(gp2.left, true);
    // connect the right to the left
    this.initialCondConnect();
    t.checkExpect(gp3.left, false);
    t.checkExpect(gp2.right, false);
    gp3.connect(gp2);
    t.checkExpect(gp3.left, true);
    t.checkExpect(gp2.right, true);
    // connect the top to the bottom
    this.initialCondConnect();
    t.checkExpect(gp1.bottom, false);
    t.checkExpect(gp4.top, false);
    gp1.connect(gp4);
    t.checkExpect(gp1.bottom, true);
    t.checkExpect(gp4.top, true);
    // connect the bottom to the top
    this.initialCondConnect();
    t.checkExpect(gp1.bottom, false);
    t.checkExpect(gp4.top, false);
    gp4.connect(gp1);
    t.checkExpect(gp1.bottom, true);
    t.checkExpect(gp4.top, true);
  }

  // starts LightEmAll
  public void testBigBang(Tester t) {
    LightEmAll world = new LightEmAll(8, 9);
    world.bigBang(450, 500, 0.1);
  }
