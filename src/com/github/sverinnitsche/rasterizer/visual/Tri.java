package com.github.sverinnitsche.rasterizer.visual;

import com.github.sverinnitsche.rasterizer.math.Matrix;
import com.github.sverinnitsche.rasterizer.math.Vertex;

public class Tri {
  private Vertex[] vertices;
  
  public Tri(Vertex a, Vertex b, Vertex c) {
    vertices = new Vertex[3];
    vertices[0] = a;
    vertices[1] = b;
    vertices[2] = c;
  }
  
  public double getZ(Matrix matrix) {
    double z1 = vertices[0].getZIndex(matrix);
    double z2 = vertices[1].getZIndex(matrix);
    double z3 = vertices[2].getZIndex(matrix);
    return z1+z2+z3;
  }
  
  public double getY(Matrix matrix) {
    double y1 = vertices[0].getYIndex(matrix);
    double y2 = vertices[1].getYIndex(matrix);
    double y3 = vertices[2].getYIndex(matrix);
    return y1+y2+y3;
  }
  
  public double getX(Matrix matrix) {
    double x1 = vertices[0].getXIndex(matrix);
    double x2 = vertices[1].getXIndex(matrix);
    double x3 = vertices[2].getXIndex(matrix);
    return x1+x2+x3;
  }
  
  public double getZIndex(Matrix matrix) {
    return getZ(matrix)+getY(matrix)-getX(matrix);
  }
  
  public void project(Matrix cam, Raster r, boolean outline, Vertex sun) {
    Vertex a = cam.multiply(vertices[0]);
    Vertex b = cam.multiply(vertices[1]);
    Vertex c = cam.multiply(vertices[2]);
  
    if(outline) r.tri(a,b,c);
    else if(sun==null) r.fillTri(a,b,c);
    else r.fillTri(a,b,c,getNormal().dot(sun));
  }
  
  public Vertex getNormal() {
    return new Vertex(
        vertices[0].getX()-vertices[1].getX(),
        vertices[0].getY()-vertices[1].getY(),
        vertices[0].getZ()-vertices[1].getZ()
    ).cross(
        new Vertex(
            vertices[0].getX()-vertices[2].getX(),
            vertices[0].getY()-vertices[2].getY(),
            vertices[0].getZ()-vertices[2].getZ()
        )
    ).norm();
  }
  
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("\t").append(vertices[0].toString()).append("\n");
    builder.append("\t/\t\\\n");
    builder.append(vertices[1].toString()).append("\t").append(vertices[2].toString());
    return builder.toString();
  }
  
}
