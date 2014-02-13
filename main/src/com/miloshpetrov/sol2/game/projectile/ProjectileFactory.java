package com.miloshpetrov.sol2.game.projectile;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.miloshpetrov.sol2.game.Fraction;
import com.miloshpetrov.sol2.game.SolGame;
import com.miloshpetrov.sol2.game.item.*;

public interface ProjectileFactory {
  Projectile create(SolGame cmp, float angle, Vector2 muzzlePos, Vector2 gunSpd, Fraction fraction, float dmg);
  float getProjSpd();
  int getAmmoPerClip();
  String getClipTexName();
  SolItem getClipExample();


  public static class RocketFactory implements ProjectileFactory {
    public Projectile create(SolGame cmp, float angle, Vector2 muzzlePos, Vector2 gunSpd, Fraction fraction, float dmg) {
      return cmp.getProjectileBuilder().buildRocket(cmp, muzzlePos, angle, gunSpd, fraction, dmg);
    }

    @Override
    public float getProjSpd() {
      return Rocket.MAX_SPD_LEN;
    }

    @Override
    public int getAmmoPerClip() {
      return RocketClip.AMMO_PER_CLIP;
    }

    @Override
    public String getClipTexName() {
      return RocketClip.TEX_NAME;
    }

    @Override
    public SolItem getClipExample() {
      return RocketClip.EXAMPLE;
    }
  }

  public static class BulletFactory implements ProjectileFactory {

    private final TextureAtlas.AtlasRegion myTex;
    private final float myWidth;
    private final float mySpdLen;
    private final boolean myExplode;

    public BulletFactory(TextureAtlas.AtlasRegion tex, float width, float spdLen, boolean explode) {
      myTex = tex;
      myWidth = width;
      mySpdLen = spdLen;
      myExplode = explode;
    }

    public Projectile create(SolGame game, float angle, Vector2 muzzlePos, Vector2 gunSpd, Fraction fraction, float dmg) {
      return new Bullet(game, angle, muzzlePos, gunSpd, fraction, dmg, myTex, myWidth, mySpdLen, myExplode);
    }

    @Override
    public float getProjSpd() {
      return mySpdLen;
    }

    @Override
    public int getAmmoPerClip() {
      return BulletClip.AMMO_PER_CLIP;
    }

    @Override
    public String getClipTexName() {
      return BulletClip.TEX_NAME;
    }

    @Override
    public SolItem getClipExample() {
      return BulletClip.EXAMPLE;
    }
  }
}