package com.github.sverinnitsche.rasterizer.visual;

import com.github.sverinnitsche.rasterizer.math.Vertex;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Raster {
  
  private BufferedImage img = new BufferedImage(500,500,BufferedImage.TYPE_INT_RGB);
  private Graphics g = img.getGraphics();
  
  private Vertex transform(Vertex v) {
    v.setX(v.getX()-img.getWidth()/2d);
    v.setY(v.getY()-img.getHeight()/2d);
  
    v.setX(v.getX()/Math.sqrt(v.getZ()));
    v.setY(v.getY()/Math.sqrt(v.getZ()));
  
    v.setX(v.getX()+img.getWidth()/2d);
    v.setY(v.getY()+img.getHeight()/2d);
    
    return v;
  }
  
  private Vertex transform2(Vertex v) {
    v.setX(v.getX() + v.getZ()/2);
    v.setY(v.getY() - v.getZ()/2);
    return v;
  }
  
  public void tri(Vertex a, Vertex b, Vertex c) {
    transform2(a);
    transform2(b);
    transform2(c);
    img.getGraphics().drawLine((int)a.getX(),(int)a.getY(),(int)b.getX(),(int)b.getY());
    img.getGraphics().drawLine((int)a.getX(),(int)a.getY(),(int)c.getX(),(int)c.getY());
    img.getGraphics().drawLine((int)c.getX(),(int)c.getY(),(int)b.getX(),(int)b.getY());
  }
  
  public void fillTri(Vertex a, Vertex b, Vertex c) {
    transform2(a);
    transform2(b);
    transform2(c);
    int[] x = {(int)a.getX(), (int)b.getX(), (int)c.getX()};
    int[] y = {(int)a.getY(), (int)b.getY(), (int)c.getY()};
    g.fillPolygon(x,y,3);
  }
  
  public void fillTri(Vertex a, Vertex b, Vertex c, double strength) {
    transform2(a);
    transform2(b);
    transform2(c);
    strength = Math.abs(strength)>1?1:Math.abs(strength);
    int[] x = {(int)a.getX(), (int)b.getX(), (int)c.getX()};
    int[] y = {(int)a.getY(), (int)b.getY(), (int)c.getY()};
    g.setColor(new Color((int)(255*strength),(int)(255*strength),(int)(255*strength)));
    g.fillPolygon(x,y,3);
  }
  
  public void show() {
    JFrame frame = new JFrame();
    frame.add(new JLabel(new ImageIcon(img)));
    frame.pack();
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  
}
