package net.kunmc.lab.tntrain.game.area;

import java.util.ArrayList;
import java.util.List;
import net.kunmc.lab.tntrain.TNTRain;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

public class TNTList extends BukkitRunnable {

  private List<TNT> list = new ArrayList<>();

  public TNTList(List<Location> locationList) {
    for (Location location : locationList) {
      list.add(new TNT(location));
    }

    runTaskTimerAsynchronously(TNTRain.plugin, 0, 1);
  }

  @Override
  public void run() {
    list.removeIf(TNT::isBombed);
  }

  public boolean isEmpty() {
    return this.list.isEmpty();
  }

  @Override
  protected void finalize() throws Throwable {
    cancel();
  }
}
