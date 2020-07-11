package com.utm.app.game.element;

import java.awt.Graphics2D;
import java.awt.image.*;
import com.utm.app.Point;

public class GroundSpikesTrap extends GameObject {

  public static final String ENABLED_TRAP_TEXTURE = "/images/trap_A_on.png";
  public static final String DISABLED_TRAP_TEXTURE = "/images/trap_A_off.png";

  private BufferedImage enabledTrapTexture;
  private BufferedImage disabledTrapTexture;
  private boolean trapIsEnabled;

  GroundSpikesTrap(boolean enabled, Point p, BufferedImage enabledTrapTexture, 
    BufferedImage disabledTrapTexture) 
  {
    super(p);
    this.enabledTrapTexture = enabledTrapTexture;  
    this.disabledTrapTexture = disabledTrapTexture;
    this.walkable = true;
    this.eatable = false;
    this.trapIsEnabled = enabled;
  }

  @Override
  public void render(Graphics2D g) {
    if(trapIsEnabled() ){
      g.drawImage(this.enabledTrapTexture, getXCalculatedPos(), getYCalculatedPos(), null);
    }else{
      g.drawImage(this.disabledTrapTexture, getXCalculatedPos(), getYCalculatedPos(), null);
    }
  }

  @Override
  public boolean isAggressive() {
    return trapIsEnabled();
  }

  public void setTrapEnabled() {
    this.trapIsEnabled = true;
  }

  public void setTrapDisabled() {
    this.trapIsEnabled = false;
  }

  public boolean trapIsEnabled() {
    return trapIsEnabled;
  }
  
}