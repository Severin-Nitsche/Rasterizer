package com.github.sverinnitsche.rasterizer.visual;

import com.github.sverinnitsche.rasterizer.math.Matrix;
import com.github.sverinnitsche.rasterizer.math.Vertex;

public class Rasterizer {
  private Mesh mesh;
  private Matrix cam;
  
  public Rasterizer(Mesh mesh, Matrix cam) {
    this.mesh = mesh;
    this.cam = cam;
  }
  
  public Raster render(boolean outline) {
    Raster r = new Raster();
    mesh.index(cam);
    mesh.render(cam, r, outline, null);
    return r;
  }
  
  public Raster render(boolean outline, Vertex sun) {
    Raster r = new Raster();
    mesh.index(cam);
    mesh.render(cam, r, outline, sun.norm());
    return r;
  }
  
}
