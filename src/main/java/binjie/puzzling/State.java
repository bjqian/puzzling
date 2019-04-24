package binjie.puzzling;

import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by binjie.qian@ele.me on 2019/4/22
 */
public class State {

  Color[] up;
  Color[] down;
  Color[] left;
  Color[] right;
  Color[] front;
  Color[] back;

  LinkedList<Action> solution;

  Action[] actions = new Action[]{new FrontAction(1), new FrontAction(2), new FrontAction(3),
      new RightAction(1), new RightAction(2), new RightAction(3),
      new UpAction(1), new UpAction(2), new UpAction(3)};

  public State() {
    solution = new LinkedList<Action>();
  }

  public void initByImage() throws IOException {
    JpegUtil jpegUtil = new JpegUtil();
    up = jpegUtil.extractColor("u", 2);
    Face.UP.format(up);
    right = jpegUtil.extractColor("r", 2);
    Face.RIGHT.format(right);
    front = jpegUtil.extractColor("f", 2);
    Face.FRONT.format(front);
    back = jpegUtil.extractColor("b", 2);
    Face.BACK.format(back);
    left = jpegUtil.extractColor("l", 2);
    Face.LEFT.format(left);
    down = jpegUtil.extractColor("d", 2);
    Face.DOWN.format(down);
  }

  public void setUp(Color one, Color two, Color three, Color four) {
    up = new Color[]{one, two, three, four};
  }

  public void setDown(Color one, Color two, Color three, Color four) {
    down = new Color[]{one, two, three, four};
  }

  public void setLeft(Color one, Color two, Color three, Color four) {
    left = new Color[]{one, two, three, four};
  }

  public void setRight(Color one, Color two, Color three, Color four) {
    right = new Color[]{one, two, three, four};
  }

  public void setFront(Color one, Color two, Color three, Color four) {
    front = new Color[]{one, two, three, four};
  }

  public void setBack(Color one, Color two, Color three, Color four) {
    back = new Color[]{one, two, three, four};
  }

  public int getSolution() {
    if (check()) {
      System.out.println("已经好了");
      return 0;
    }
    int n = 1;

    while (true) {
      long start = System.currentTimeMillis();
      for (Action action : actions) {
        boolean result = tryAction(action, 1, n);
        if (result) {
          System.out.println("Solution:");
          for (Action str : solution) {
            System.out.print(str);
          }
          return n;
        }
      }
      long end = System.currentTimeMillis();
      System.out.println("step=" + n + "  cost " + (end - start) + "  ms");
      n++;
    }
  }

  public boolean check() {
    return check(up) && check(front) && check(left) && check(right) && check(back);
  }

  private boolean check(Color[] tmp) {
    int n = tmp.length;
    for (int i = 1; i < n; i++) {
      if (tmp[i] != tmp[i - 1]) {
        return false;
      }
    }
    return true;
  }

  private boolean tryAction(Action action, int level, int maxLevel) {
    action.act(this);
    if (level == maxLevel) {
      if (check()) {
        solution.addFirst(action);
        return true;
      } else {
        action.rollBack(this);
        return false;
      }
    } else {
      for (Action next : actions) {
        boolean result = tryAction(next, level + 1, maxLevel);
        if (result) {
          solution.addFirst(action);
          return true;
        }
      }
      action.rollBack(this);
      return false;
    }
  }
}
