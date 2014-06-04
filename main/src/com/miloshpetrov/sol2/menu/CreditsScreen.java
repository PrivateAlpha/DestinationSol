package com.miloshpetrov.sol2.menu;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.miloshpetrov.sol2.Const;
import com.miloshpetrov.sol2.SolCmp;
import com.miloshpetrov.sol2.common.Col;
import com.miloshpetrov.sol2.common.SolMath;
import com.miloshpetrov.sol2.ui.*;

import java.util.ArrayList;
import java.util.List;

public class CreditsScreen implements SolUiScreen {
  public static final float MAX_AWAIT = 6f;
  private final ArrayList<SolUiControl> myControls;
  private final SolUiControl myCloseCtrl;
  private final ArrayList<String> myPages;
  private final Color myColor;

  private int myIdx;
  private float myPerc;

  public CreditsScreen(float r) {
    myControls = new ArrayList<SolUiControl>();
    myCloseCtrl = new SolUiControl(MainScreen.creditsBtnRect(r), true, Input.Keys.ESCAPE);
    myCloseCtrl.setDisplayName("Close");
    myControls.add(myCloseCtrl);
    myColor = Col.col(1, 1);

    myPages = new ArrayList<String>();
    myPages.add("Page 1\n test");
    myPages.add("Page 2\n test2");
  }

  @Override
  public List<SolUiControl> getControls() {
    return myControls;
  }

  @Override
  public void onAdd(SolCmp cmp) {
    myIdx = 0;
    myPerc = 0;
    myColor.a = 0;
  }

  @Override
  public void updateCustom(SolCmp cmp, SolInputMan.Ptr[] ptrs) {
    if (myCloseCtrl.isJustOff()) {
      cmp.getInputMan().setScreen(cmp, cmp.getMenuScreens().main);
      return;
    }
    myPerc += Const.REAL_TIME_STEP / MAX_AWAIT;
    if (myPerc > 1) {
      myPerc = 0;
      myIdx++;
      if (myIdx >= myPages.size()) myIdx = 0;
    }
    float a = myPerc * 2;
    if (a > 1) a = 2 - a;
    a *= 3;
    myColor.a = SolMath.clamp(a);
  }

  @Override
  public boolean isCursorOnBg(SolInputMan.Ptr ptr) {
    return false;
  }

  @Override
  public void blurCustom(SolCmp cmp) {
  }

  @Override
  public void drawBg(UiDrawer uiDrawer, SolCmp cmp) {
  }

  @Override
  public void drawImgs(UiDrawer uiDrawer, SolCmp cmp) {
  }

  @Override
  public void drawText(UiDrawer uiDrawer, SolCmp cmp) {
    uiDrawer.drawString(myPages.get(myIdx), uiDrawer.r/2, .5f, FontSize.WINDOW, true, myColor);
  }
}