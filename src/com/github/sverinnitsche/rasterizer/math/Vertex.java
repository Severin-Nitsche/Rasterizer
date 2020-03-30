package com.github.sverinnitsche.rasterizer.math;

import java.text.DecimalFormat;

public class Vertex {
  private double[] pos;
  
  private Matrix bufferedMatrix;
  private double x;
  private double y;
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
  
  public void setX(double x) {
    pos[0] = x;
  }
  
  public void setY(double y) {
    pos[1] = y;
  }
  
  public void setZ(double z) {
    pos[2] = z;
  }
  
  public double getZIndex(Matrix matrix) {
    if (matrix != bufferedMatrix) {
      bufferedMatrix = matrix;
      Vertex clone = matrix.multiply(this);
      x = clone.getX();
      y = clone.getY();
      z = clone.getZ();
    }
    return z;
  }
  
  public double getYIndex(Matrix matrix) {
    if (matrix != bufferedMatrix) {
      bufferedMatrix = matrix;
      Vertex clone = matrix.multiply(this);
      x = clone.getX();
      y = clone.getY();
      z = clone.getZ();
    }
    return y;
  }
  
  public double getXIndex(Matrix matrix) {
    if (matrix != bufferedMatrix) {
      bufferedMatrix = matrix;
      Vertex clone = matrix.multiply(this);
      x = clone.getX();
      y = clone.getY();
      z = clone.getZ();
    }
    return x;
  }
  
  public double dot(Vertex v) {
    return getX()*v.getX()+getY()*v.getY()+getZ()*v.getZ();
  }
  
  public Vertex cross(Vertex v) {
    return new Vertex(
        getY()*v.getZ() - getZ()*v.getY(),
        getZ()*v.getX() - getX()*v.getZ(),
        getX()*v.getY() - getY()*v.getX()
    );
  }
  
  public Vertex norm() {
    double m = Math.sqrt(getX()*getX()+getY()*getY()+getZ()*getZ());
    pos[0] /= m;
    pos[1] /= m;
    pos[2] /= m;
    return this;
  }
  
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    DecimalFormat format = new DecimalFormat("#.##");
    builder.append("(").append(format.format(pos[0])).append("|").append(format.format(pos[1])).append("|").append(format.format(pos[2])).append(")");
    return builder.toString();
  }
  
}
