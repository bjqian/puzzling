package binjie.puzzling;

/**
 *
 * Created by binjie.qian@ele.me on 2019/4/24
 */
public enum Face {
  UP {
    @Override
    public void format(Color[] colors) {
      Color[] tmp = new Color[]{colors[3], colors[1], colors[0], colors[2]};
      this.set(colors, tmp);
    }
  },
  DOWN {
    @Override
    public void format(Color[] colors) {
      Color[] tmp = new Color[]{colors[1], colors[3], colors[2], colors[0]};
      this.set(colors, tmp);
    }
  },
  LEFT {
    @Override
    public void format(Color[] colors) {
      Color[] tmp = new Color[]{colors[1], colors[3], colors[2], colors[0]};
      this.set(colors, tmp);
    }
  },
  RIGHT {
    @Override
    public void format(Color[] colors) {
      Color[] tmp = new Color[]{colors[0], colors[2], colors[3], colors[1]};
      this.set(colors, tmp);
    }
  },
  FRONT {
    @Override
    public void format(Color[] colors) {
      Color[] tmp = new Color[]{colors[1], colors[0], colors[2], colors[3]};
      this.set(colors, tmp);
    }
  },
  BACK {
    @Override
    public void format(Color[] colors) {
      Color[] tmp = new Color[]{colors[0], colors[1], colors[3], colors[2]};
      this.set(colors, tmp);
    }
  };

  public abstract void format(Color[] colors);

  void set(Color[] a, Color[] b) {
    for (int i = 0; i < a.length; i++) {
      a[i] = b[i];
    }
  }
}
