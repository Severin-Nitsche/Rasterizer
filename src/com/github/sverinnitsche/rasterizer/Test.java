package com.github.sverinnitsche.rasterizer;

import com.github.sverinnitsche.rasterizer.io.ModelReader;
import com.github.sverinnitsche.rasterizer.math.Matrix;
import com.github.sverinnitsche.rasterizer.math.Vertex;
import com.github.sverinnitsche.rasterizer.visual.Mesh;
import com.github.sverinnitsche.rasterizer.visual.Rasterizer;

import java.io.File;
import java.io.IOException;

public class Test {
  
  public static void main(String[] args) throws IOException {
    Mesh mesh = new ModelReader(new File("res/models/Cube.mdl")).mesh();
    Vertex origin = new Vertex(1,1,0);
    Vertex x = new Vertex(100,0,0);
    Vertex y = new Vertex(0,100,0);
    Vertex z = new Vertex(0,0,100);
    Matrix cam = new Matrix(x,y,z,origin);
    Rasterizer renderer = new Rasterizer(mesh,cam);
    renderer.render(false, new Vertex(-.5,-.8,.4)).show();
  }
  
}
