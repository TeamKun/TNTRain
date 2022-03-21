package net.kunmc.lab.tntrain.game;

import net.kunmc.lab.tntrain.TNTRain;
import net.kunmc.lab.tntrain.game.area.Area;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

public class Game extends BukkitRunnable {

  private boolean isRunning;
  private Area area;

  public Game() {
    runTaskTimerAsynchronously(
        TNTRain.plugin,
        0,
        1
    );
  }

  @Override
  public void run() {
    if (!isRunning) {
      return;
    }
    this.area.rain();
  }

  public void setArea(Location location) {
    new BukkitRunnable() {
      @Override
      public void run() {
        area = new Area(location);
      }
    }.runTaskAsynchronously(TNTRain.plugin);
  }

  public void setArea(int size) {
    this.area = new Area(size);
  }

  public boolean existsArea() {
    return this.area != null;
  }

  void start() {
    this.isRunning = true;
  }

  public void stop() {
    this.isRunning = false;

  }

  boolean isRunning() {
    return this.isRunning;
  }
}
