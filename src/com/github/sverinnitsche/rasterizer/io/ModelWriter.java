package com.github.sverinnitsche.rasterizer.io;

import com.github.sverinnitsche.rasterizer.visual.Mesh;

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
  
  private byte[] fromDouble(final double value) {
    
    return null;
  }
}
