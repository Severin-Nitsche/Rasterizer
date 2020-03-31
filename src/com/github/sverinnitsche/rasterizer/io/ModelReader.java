package com.github.sverinnitsche.rasterizer.io;

import com.github.sverinnitsche.rasterizer.math.Vertex;
import com.github.sverinnitsche.rasterizer.visual.Mesh;

import java.io.*;

public class ModelReader {
  
  private InputStream mdlStream; // file input stream
  private long length; // file size in bytes
  
  public ModelReader(File mdl) throws FileNotFoundException {
    mdlStream = new FileInputStream(mdl);
    length = mdl.length();
  }
  
  public Mesh mesh() throws IOException {
    Vertex[] vertices= new Vertex[nextTriple()];
    for(int i = 0; i<vertices.length; i++) {
      vertices[i] = nextVertex();
    }
    long len = length - 3 - vertices.length * 3 * 8;
    len /= 3;
    int[] indices = new int[(int)len];
    for(int i=0; i<len; i++) {
      indices[i] = nextTriple();
    }
    return new Mesh(vertices, indices);
  }
  
  private Vertex nextVertex() throws IOException {
    double x = nextDouble();
    double y = nextDouble();
    double z = nextDouble();
    
    return new Vertex(x, y, z);
  }
  
  private int nextTriple() throws IOException {
    byte[] number = new byte[3];
    mdlStream.read(number);
    int result = 0;
    for(int i = 0; i<3; i++) {
      result <<= 8;
      result |= (0xff & number[i]);
    }
    return result;
  }
  
  private double nextDouble() throws IOException {
    byte[] number = new byte[8];
    mdlStream.read(number);
    return asDouble(number);
  }
  
  private double asDouble(final byte[] bytes) {
    long bits = asLong(bytes);
    return Double.longBitsToDouble(bits);
  }
  
  private long asLong(final byte[] bytes) {
    assert bytes.length == 8;
    
    long result = 0;
    for(int i = 0; i<8; i++) {
      result <<= 8;
      result |= (0xff & bytes[i]);
    }
    return result;
  }
  
}
