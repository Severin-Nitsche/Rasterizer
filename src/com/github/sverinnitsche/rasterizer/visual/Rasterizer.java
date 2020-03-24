package com.github.sverinnitsche.rasterizer.visual;

import com.github.sverinnitsche.rasterizer.math.Matrix;

public class Rasterizer {
  private Mesh mesh;
  private Matrix cam;
  
  public Rasterizer(Mesh mesh, Matrix cam) {
    this.mesh = mesh;
    this.cam = cam;
  }
  
  public Raster render() {
    Raster r = new Raster();
    mesh.index(cam);
    mesh.render(cam, r);
    return r;
  }
  
}
