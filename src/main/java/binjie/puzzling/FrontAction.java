package binjie.puzzling;

/**
 * Created by binjie.qian@ele.me on 2019/4/22
 */
public class FrontAction extends Action {

  public FrontAction(int n) {
    super(n);
  }

  protected void act0(State state) {
    Color[] tmp = new Color[]{state.up[0], state.up[3]};

    state.up[0] = state.right[1];
    state.up[3] = state.right[0];

    state.right[0] = state.down[0];
    state.right[1] = state.down[3];

    state.down[0] = state.left[1];
    state.down[3] = state.left[0];

    state.left[0] = tmp[0];
    state.left[1] = tmp[1];

    rotate(state.front);
  }

  @Override
  public String toString() {
    return "front(" + n + ") ";
  }


}
