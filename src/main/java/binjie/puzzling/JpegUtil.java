package binjie.puzzling;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.imageio.ImageIO;

/**
 * Created by binjie.qian@ele.me on 2019/4/24
 */
public class JpegUtil {

  private static double variance(int[] x, int[] y) {
    int r = x[0] - y[0];
    int g = x[1] - y[1];
    int b = x[2] - y[2];
    return r * r + g * g + b * b;
  }

  public Color[] extractColor(String name, int n) throws IOException {
    File jpegFile = new File("/Users/bjqian/Downloads/cubic/" + name + ".jpeg");
    BufferedImage image = ImageIO.read(jpegFile);
    //get image width and height
    int width = image.getWidth();
    int height = image.getHeight();
    long size = width * height;
    Map<Color, Integer> colorIntegerMap = new HashMap<>();
    int[][][] rgbs;
    rgbs = new int[width][height][3];
    long x = 0, y = 0, c = 0;
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        int p = image.getRGB(i, j);
        //get red
        int r = (p >> 16) & 0xff;

        //get green
        int g = (p >> 8) & 0xff;

        //get blue
        int b = p & 0xff;
        rgbs[i][j][0] = r;
        rgbs[i][j][1] = g;
        rgbs[i][j][2] = b;
        for (Color color : Color.values()) {
          if (variance(color.rgb, rgbs[i][j]) < 2000) {
            colorIntegerMap.put(color, colorIntegerMap.getOrDefault(color, 0) + 1);
            x += i;
            y += j;
            c++;
          }
        }
      }
    }
    x /= c;
    y /= c;
    int edgeLength = (int) Math.sqrt(c);
    int subEdgeLength = edgeLength / n;
    x -= (edgeLength) / 2;
    y -= (edgeLength) / 2;
    Color[] result = new Color[n * n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        int x0 = (int) x + i * subEdgeLength;
        int y0 = (int) y + j * subEdgeLength;
        Color color = getColor(rgbs, x0, y0, subEdgeLength);
        result[j * n + i] = color;
      }
    }
    return result;
  }

  public int[] extractColor(String name, int[] init) throws IOException {
    File jpegFile = new File("/Users/bjqian/Downloads/cubic/" + name + ".jpeg");
    BufferedImage image = ImageIO.read(jpegFile);
    //get image width and height
    int width = image.getWidth();
    int height = image.getHeight();
    int[][][] rgbs;
    int[][] origin;
    rgbs = new int[width][height][3];
    origin = new int[width][height];
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        int p = image.getRGB(i, j);
        origin[i][j] = p;

        //get alpha
        int a = (p >> 24) & 0xff;

        //get red
        int r = (p >> 16) & 0xff;

        //get green
        int g = (p >> 8) & 0xff;

        //get blue
        int b = p & 0xff;
        rgbs[i][j][0] = r;
        rgbs[i][j][1] = g;
        rgbs[i][j][2] = b;
      }
    }
    //get pixel value

    File file2 = new File("/Users/bjqian/Downloads/cubic/" + name + "2.jpeg");
    BufferedImage image2 = new BufferedImage(width, height,
        BufferedImage.TYPE_INT_RGB);
    for (int k = 1; k <= 1; k++) {
      long ra = 0, ga = 0, ba = 0, ca = 0;
      for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
          if (variance(rgbs[i][j], init) < 5000 / k) {
            image2.setRGB(i, j, origin[i][j]);
            ra += rgbs[i][j][0];
            ga += rgbs[i][j][1];
            ba += rgbs[i][j][2];
            ca++;
          } else {
            image2.setRGB(i, j, 0);
          }
        }
      }
      ra /= ca;
      ga /= ca;
      ba /= ca;
      init[0] = (int) ra;
      init[1] = (int) ga;
      init[2] = (int) ba;
    }
    ImageIO.write(image2, "jpeg", file2);
    //some code goes here...
    int stop = 0;
    return init;
  }

  Color getColor(int[] rgb) {
    int max = Integer.MAX_VALUE;
    Color result = null;
    for (Color color : Color.values()) {
      int variance = (int) variance(color.rgb, rgb);
      if (variance < max) {
        max = variance;
        result = color;
      }
    }
    if (max > 10000) {
      return null;
    }
    return result;
  }

  Color getColor(int[][][] rgbs, int x0, int y0, int subLength) {
    Map<Color, Integer> map = new HashMap<>();
    for (int i = x0; i < x0 + subLength; i++) {
      for (int j = y0; j < y0 + subLength; j++) {
        Color color = getColor(rgbs[i][j]);
        if (color != null) {
          map.put(color, map.getOrDefault(color, 0) + 1);
        }
      }
    }
    int max = 0;
    Color result = null;
    for (Entry<Color, Integer> entry : map.entrySet()) {
      if (entry.getValue() > max) {
        max = entry.getValue();
        result = entry.getKey();
      }
    }
    return result;
  }
}



