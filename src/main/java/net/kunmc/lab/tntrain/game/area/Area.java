package net.kunmc.lab.tntrain.game.area;

import net.kunmc.lab.tntrain.TNTRain;
import org.bukkit.Location;

public class Area {

  private Location center;
  private final Ceiling ceiling;
  private final Wall wall;

  public Area(Location center) {
    this.center = center;

    int size = TNTRain.config.areaSize.value();
    Location vertex = new Location(
        center.getWorld(),
        center.getX() + size / 2,
        255,
        center.getZ() + size / 2
    );

    this.ceiling = new Ceiling(vertex, size);
    this.wall = new Wall(vertex, size);

    wall.setBlock();
  }

  public Area(int size) {

    Location vertex = new Location(
        this.center.getWorld(),
        this.center.getX() - size / 2,
        255,
        this.center.getZ() + size / 2
    );

    this.ceiling = new Ceiling(vertex, size);

    this.wall = new Wall(vertex, size);

    wall.setBlock();
  }

  public void rain() {
    this.ceiling.rain();
  }

  @Override
  protected void finalize() throws Throwable {
    this.wall.clear();
  }
}
