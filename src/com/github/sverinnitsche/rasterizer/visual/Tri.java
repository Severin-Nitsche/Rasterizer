package com.github.sverinnitsche.rasterizer.visual;

import com.github.sverinnitsche.rasterizer.math.Matrix;
import com.github.sverinnitsche.rasterizer.math.Vertex;

public class Tri {
  private Vertex[] vertecies;
  
  public Tri(Vertex a, Vertex b, Vertex c) {
    vertecies = new Vertex[3];
    vertecies[0] = a;
    vertecies[1] = b;
    vertecies[2] = c;
  }
  
  public double getZ(Matrix matrix) {
    double z1 = vertecies[0].getZIndex(matrix);
    double z2 = vertecies[1].getZIndex(matrix);
    double z3 = vertecies[2].getZIndex(matrix);
    return z1>z2?z1>z3?z1:z3:z2;
  }
  
  public void project(Matrix cam, Raster r, boolean outline) {
    Vertex a = cam.multiply(vertecies[0]);
    Vertex b = cam.multiply(vertecies[1]);
    Vertex c = cam.multiply(vertecies[2]);
  
    if(outline) r.tri(a,b,c);
    else r.fillTri(a,b,c);
  }
}
