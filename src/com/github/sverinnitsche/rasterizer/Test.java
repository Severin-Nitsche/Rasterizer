package com.github.sverinnitsche.rasterizer;

import com.github.sverinnitsche.rasterizer.math.Matrix;
import com.github.sverinnitsche.rasterizer.math.Vertex;
import com.github.sverinnitsche.rasterizer.visual.Mesh;
import com.github.sverinnitsche.rasterizer.visual.Rasterizer;

import java.util.Arrays;

public class Test {
  
  public static void main(String[] args) {
    Vertex[] vertices = {
        new Vertex(0,0,3),
        new Vertex(10,0,3),
        new Vertex(0,10,3),
        new Vertex(10,10,3)
    };
    int[] indices = { 0,1,2,3,1 };
    Mesh mesh = new Mesh(vertices,indices);
    Vertex origin = new Vertex(0,0,0);
    Vertex x = new Vertex(1,0,0);
    Vertex y = new Vertex(0,1,0);
    Vertex z = new Vertex(0,0,1);
    Matrix cam = new Matrix(x,y,z,origin);
    Rasterizer renderer = new Rasterizer(mesh,cam);
    renderer.render().show();
  }
  
}
