package net.kunmc.lab.tntrain.game.area;

import java.util.ArrayList;
import java.util.List;
import net.kunmc.lab.tntrain.TNTRain;
import net.kunmc.lab.tntrain.util.RandomCalculator;
import org.bukkit.Location;

public class Ceiling {

  private List<Location> locationList = new ArrayList<>();
  private TNTList tntList;

  public Ceiling(Location vertex, int size) {
    // TNTが降り始めるエリア
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        this.locationList.add(
            new Location(
                vertex.getWorld(),
                vertex.getX() - i,
                vertex.getY(),
                vertex.getZ() - j
            )
        );
      }
    }
  }

  public void rain() {
    List<Location> list = new ArrayList<>();
    if (this.tntList != null && !this.tntList.isEmpty()) {
      return;
    }

    for (Location location : locationList) {
      if (RandomCalculator.lottery(TNTRain.config.rainfallRate.value())) {
        list.add(location);
      }
    }
    tntList = new TNTList(list);
  }
}
