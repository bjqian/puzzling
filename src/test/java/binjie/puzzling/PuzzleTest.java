package binjie.puzzling;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by binjie.qian@ele.me on 2019/4/22
 */
public class PuzzleTest {

  State completed;
  Action front;
  Action up;
  Action right;

  @Before
  public void init() {
    completed = new State();
    completed.setBack(Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE);
    completed.setUp(Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE);
    completed.setRight(Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.YELLOW);
    completed.setLeft(Color.GREEN, Color.GREEN, Color.GREEN, Color.GREEN);
    completed.setDown(Color.PINK, Color.PINK, Color.PINK, Color.PINK);
    completed.setFront(Color.ORANGE, Color.ORANGE, Color.ORANGE, Color.ORANGE);

    front = new FrontAction(1);
    up = new UpAction(1);
    right = new RightAction(1);
  }

  @Test
  public void testRollBack() {
    up.act(completed);
    up.rollBack(completed);
    int step = completed.getSolution();
    Assert.assertEquals(step, 0);

    right.act(completed);
    right.rollBack(completed);
    step = completed.getSolution();
    Assert.assertEquals(step, 0);

    front.act(completed);
    front.rollBack(completed);
    step = completed.getSolution();
    Assert.assertEquals(step, 0);

  }

  @Test
  public void simple0() {
    up.act(completed);
    front.act(completed);
    right.act(completed);
    right.act(completed);
    up.act(completed);
    front.act(completed);
    right.act(completed);
    up.act(completed);
    completed.getSolution();
  }

  @Test
  public void simple1() throws IOException {
    State state = new State();
    state.initByImage();
    state.getSolution();
  }

  @Test
  public void complex0() {
    State state = new State();
    state.setBack(Color.WHITE, Color.WHITE, Color.WHITE, Color.PINK);
    state.setDown(Color.PINK, Color.BLUE, Color.GREEN, Color.WHITE);
    state.setFront(Color.YELLOW, Color.YELLOW, Color.PINK, Color.YELLOW);
    state.setLeft(Color.BLUE, Color.BLUE, Color.PINK, Color.BLUE);
    state.setRight(Color.GREEN, Color.GREEN, Color.YELLOW, Color.GREEN);
    state.setUp(Color.ORANGE, Color.ORANGE, Color.ORANGE, Color.ORANGE);

    state.getSolution();
  }


}
