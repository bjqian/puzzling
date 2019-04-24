package binjie.puzzling;

/**
 * Created by binjie.qian@ele.me on 2019/4/22
 */
public abstract class Action {

  int n;

  public Action(int n) {
    this.n = n;
  }

  public void act(State state) {
    for (int i = 0; i < n; i++) {
      act0(state);
    }
  }

  protected abstract void act0(State state);

  public void rollBack(State state) {
    for (int i = 0; i < 4 - n; i++) {
      act0(state);
    }
  }

  protected void rotate(Color[] colors) {
    Color[] tmp = new Color[]{colors[3], colors[0], colors[1], colors[2]};
    for (int i = 0; i < 4; i++) {
      colors[i] = tmp[i];
    }
  }
}
