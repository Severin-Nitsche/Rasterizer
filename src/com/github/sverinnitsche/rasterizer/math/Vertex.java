package com.github.sverinnitsche.rasterizer.math;

public class Vertex {
  private double[] pos;
  
  private Matrix bufferedMatrix;
  private double z;
  
  public Vertex(double x, double y, double z) {
    pos = new double[3];
    pos[0] = x;
    pos[1] = y;
    pos[2] = z;
  }
  
  public double get(int i) {
    return pos[i];
  }
  
  public double getX() {
    return pos[0];
  }
  
  public double getY() {
    return pos[1];
  }
  
  public double getZ() {
    return pos[2];
  }
  
  public double getZIndex(Matrix matrix) {
    if(matrix == bufferedMatrix) return z;
    else return z = matrix.multiply(this).getZ();
  }
}
