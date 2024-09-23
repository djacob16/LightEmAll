package org.example;

import java.util.Random;

// class for edge
class Edge {
  GamePiece fromNode;
  GamePiece toNode;
  int weight;

  // constructor for edge
  Edge(GamePiece fromNode, GamePiece toNode) {
    this.fromNode = fromNode;
    this.toNode = toNode;
    this.weight = new Random().nextInt(20);
  }

  // constructor for edge for testing purpose
  Edge(GamePiece fromNode, GamePiece toNode, int weight) {
    this.fromNode = fromNode;
    this.toNode = toNode;
    this.weight = weight;
  }

}
