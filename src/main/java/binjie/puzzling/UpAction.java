package binjie.puzzling;

/**
 * Created by binjie.qian@ele.me on 2019/4/22
 */
public class UpAction extends Action {


  public UpAction(int n) {
    super(n);
  }

  public void act0(State state) {
    Color[] tmp = new Color[]{state.front[0], state.front[1]};
    state.front[0] = state.left[0];
    state.front[1] = state.left[3];

    state.left[0] = state.back[1];
    state.left[3] = state.back[0];

    state.back[0] = state.right[0];
    state.back[1] = state.right[3];

    state.right[0] = tmp[1];
    state.right[3] = tmp[0];
    rotate(state.up);
  }

  @Override
  public String toString() {
    return "up(" + n + ") ";
  }


}
