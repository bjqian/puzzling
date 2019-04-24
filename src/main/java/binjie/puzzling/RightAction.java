package binjie.puzzling;

/**
 * Created by binjie.qian@ele.me on 2019/4/22
 */
public class RightAction extends Action {


  public RightAction(int n) {
    super(n);
  }

  public void act0(State state) {
    Color[] tmp = new Color[]{state.up[0], state.up[1]};
    state.up[0] = state.back[0];
    state.up[1] = state.back[3];

    state.back[0] = state.down[1];
    state.back[3] = state.down[0];

    state.down[0] = state.front[0];
    state.down[1] = state.front[3];

    state.front[0] = tmp[1];
    state.front[3] = tmp[0];

    rotate(state.right);
  }

  @Override
  public String toString() {
    return "right(" + n + ") ";
  }


}
