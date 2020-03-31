package com.github.sverinnitsche.rasterizer.io;

import com.github.sverinnitsche.rasterizer.math.Vertex;
import com.github.sverinnitsche.rasterizer.visual.Mesh;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ModelWriter {
  
  private Mesh mesh;
  
  public ModelWriter(Mesh mesh) {
    this.mesh = mesh;
  }
  
  private byte[] fromTriple(final int triple) {
    assert triple == (triple & 0x00ffffff);
    
    byte[] result = new byte[3];
    result[0] = (byte)((triple & 0x00ff0000)>>>16);
    result[1] = (byte)((triple & 0x0000ff00)>>>8);
    result[2] = (byte) (triple & 0x000000ff);
    
    return result;
  }
  
  private byte[] fromLong(final long bits) {
    byte[] result = new byte[8];
    result[0] = (byte) (bits >>> 7*8);
    result[1] = (byte) (bits >>> 6*8);
    result[2] = (byte) (bits >>> 5*8);
    result[3] = (byte) (bits >>> 4*8);
    result[4] = (byte) (bits >>> 3*8);
    result[5] = (byte) (bits >>> 2*8);
    result[6] = (byte) (bits >>> 1*8);
    result[7] = (byte) (bits >>> 0*8);
    
    return result;
  }
  
  private byte[] fromDouble(final double value) {
    return fromLong(Double.doubleToLongBits(value));
  }
  
  private byte[] fromVertex(final Vertex vertex) {
    byte[] x = fromDouble(vertex.getX());
    byte[] y = fromDouble(vertex.getY());
    byte[] z = fromDouble(vertex.getZ());
    return new byte[] {
        x[0],x[1],x[2],x[3],x[4],x[5],x[6],x[7],
        y[0],y[1],y[2],y[3],y[4],y[5],y[6],y[7],
        z[0],z[1],z[2],z[3],z[4],z[5],z[6],z[7]
    };
  }
  
  public void write(File out) throws IOException {
    FileOutputStream output = new FileOutputStream(out);
    
    output.write(fromTriple(mesh.vertices()));
    for(int i=0; i<mesh.vertices(); i++) {
      output.write(fromVertex(mesh.getVertex(i)));
    }
    for(int i=0; i<mesh.indices(); i++) {
      output.write(fromTriple(mesh.getIndex(i)));
    }
    output.flush();
  }
}
