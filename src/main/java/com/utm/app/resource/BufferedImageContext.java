package com.utm.app.resource;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import java.awt.*;

import com.utm.core.Singleton;

@Singleton(lazy = true)
public class BufferedImageContext {

  private Map<Block, BufferedImage> cache = new ConcurrentHashMap<>();


  public BufferedImage getBufferedImage(Block resource){
    if(cache.containsKey(resource)){
      return cache.get(resource);
    }

    BufferedImage img = resizeTo32(load(resource.getPath()));
    cache.put(resource, img);

    return img;
  }

  private BufferedImage load(String path) {
    try(InputStream in = this.getClass().getResourceAsStream(path)) {
      return ImageIO.read(in);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  private BufferedImage resizeTo32(BufferedImage img) {

    final int newW = 32;
    final int newH = 32;

    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2d = dimg.createGraphics();
    g2d.drawImage(tmp, 0, 0, null);
    g2d.dispose();
    return dimg;
  }  

}