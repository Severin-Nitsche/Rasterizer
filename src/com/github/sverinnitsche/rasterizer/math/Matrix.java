package com.github.sverinnitsche.rasterizer.math;

public class Matrix {
  private double[][] matrix;
  
  /**
   *
   * Defines the Space with {e1, e2, e3} as ONB and o as origin
   *
   * @param e1 basis Vector
   * @param e2 basis Vector
   * @param e3 basis Vector
   * @param o origin Point
   */
  public Matrix(Vertex e1, Vertex e2, Vertex e3, Vertex o) {
    matrix = new double[4][4];
    matrix[0][0] = e1.getX();
    matrix[0][1] = e2.getX();
    matrix[0][2] = e3.getX();
    matrix[0][3] = o.getX()*matrix[0][0] + o.getY()*matrix[0][1] + o.getZ()*matrix[0][2];
  
    matrix[1][0] = e1.getY();
    matrix[1][1] = e2.getY();
    matrix[1][2] = e3.getY();
    matrix[1][3] = o.getX()*matrix[1][0] + o.getY()*matrix[1][1] + o.getZ()*matrix[1][2];
  
    matrix[2][0] = e1.getZ();
    matrix[2][1] = e2.getZ();
    matrix[2][2] = e3.getZ();
    matrix[2][3] = o.getX()*matrix[2][0] + o.getY()*matrix[2][1] + o.getZ()*matrix[2][2];
    
    matrix[3][3] = 1;
  }
  
  public Vertex multiply(Vertex vertex) {
    double x = vertex.getX()*matrix[0][0] + vertex.getY()*matrix[0][1] + vertex.getZ()*matrix[0][2] + matrix[0][3];
    double y = vertex.getX()*matrix[1][0] + vertex.getY()*matrix[1][1] + vertex.getZ()*matrix[1][2] + matrix[1][3];
    double z = vertex.getX()*matrix[2][0] + vertex.getY()*matrix[2][1] + vertex.getZ()*matrix[2][2] + matrix[2][3];
    
    return new Vertex(x, y, z);
  }
}
