package net.kunmc.lab.tntrain.game.area;

import net.kunmc.lab.tntrain.TNTRain;
import net.kunmc.lab.tntrain.game.Manager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.FallingBlock;
import org.bukkit.scheduler.BukkitRunnable;

public class TNT extends BukkitRunnable {

  private FallingBlock tnt;
  private Location previousLocation;
  private boolean isBombed;

  public TNT(Location location) {
    location.setY(TNTRain.config.ceilingHeight.value());
    Manager.blockSetTask.offer(
        new BukkitRunnable() {
          @Override
          public void run() {
            tnt = location.getWorld().spawnFallingBlock(location,
                Material.TNT.createBlockData());
            previousLocation = location;
          }
        }
    );

    runTaskTimerAsynchronously(
        TNTRain.plugin,
        0,
        1
    );
  }

  @Override
  public void run() {
    Location location = tnt.getLocation();

    // 前回からの落下距離を取得
    double fallDistance = previousLocation.distance(location);

    if (fallDistance != 0) {
      this.previousLocation = location;
    } else {
      // 爆破
      this.isBombed = true;
      Manager.blockSetTask.offer(
          new BukkitRunnable() {
            @Override
            public void run() {
              tnt.setTicksLived(1000);
              location.getBlock().setType(Material.AIR);
              location.getWorld().createExplosion(location, TNTRain.config.explosivePower.value());
            }
          }
      );
      cancel();
    }
  }

  public boolean isBombed() {
    return isBombed;
  }
}
