package com.github.sverinnitsche.rasterizer.visual;

import com.github.sverinnitsche.rasterizer.math.Matrix;
import com.github.sverinnitsche.rasterizer.math.Vertex;

import java.util.Arrays;
import java.util.Comparator;

public class Mesh {
  private Tri[] tris;
  
  /**
   *
   * vertices: [A,B,C,D,E,F,G]<br />
   *
   * indices: [0,5,1,3,0,2,4,5,6]<br />
   *
   * => A - F - B - D - A - C - E - F - G<br />
   *
   * @param vertices The vertices of the Mesh
   * @param indices The order in which the vertices are connected
   */
  public Mesh(Vertex[] vertices, int[] indices) {
    assert indices.length%2 == 1;
    tris = new Tri[(indices.length-1)/2];
    for(int i=0; i<indices.length-1; i+=2) {
      tris[(i)/2] = new Tri(vertices[indices[i]], vertices[indices[i+1]], vertices[indices[i+2]]);
    }
  }
  
  public void index(Matrix cam) {
    Arrays.sort(tris, Comparator.comparingDouble(o -> o.getZ(cam)));
  }
  
  public void render(Matrix cam, Raster r) {
    for(Tri tri : tris) {
      tri.project(cam, r);
    }
  }
  
}