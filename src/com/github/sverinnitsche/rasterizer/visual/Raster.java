package com.github.sverinnitsche.rasterizer.visual;

import com.github.sverinnitsche.rasterizer.math.Vertex;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class Raster {
  
  private BufferedImage img = new BufferedImage(100,100,BufferedImage.TYPE_INT_RGB);
  
  public void tri(Vertex a, Vertex b, Vertex c) {
    img.getGraphics().drawLine((int)a.getX(),(int)a.getY(),(int)b.getX(),(int)b.getY());
    img.getGraphics().drawLine((int)a.getX(),(int)a.getY(),(int)c.getX(),(int)c.getY());
    img.getGraphics().drawLine((int)c.getX(),(int)c.getY(),(int)b.getX(),(int)b.getY());
  }
  
  public void show() {
    JFrame frame = new JFrame();
    frame.add(new JLabel(new ImageIcon(img)));
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  
}
