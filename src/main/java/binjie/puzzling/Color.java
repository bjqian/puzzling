package binjie.puzzling;

/**
 * These colors are extracted by experience.
 * Created by binjie.qian@ele.me on 2019/4/22
 */
public enum Color {
  WHITE(217, 213, 208),
  ORANGE(249, 86, 12),
  YELLOW(229, 197, 42),
  BLUE(68, 183, 235),
  PINK(224, 62, 132),
  GREEN(150, 219, 1);
  int[] rgb;

  Color(int r, int g, int b) {
    rgb = new int[]{r, g, b};
  }
}
