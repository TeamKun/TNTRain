package net.kunmc.lab.tntrain.game.area;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.kunmc.lab.tntrain.TNTRain;
import net.kunmc.lab.tntrain.game.Manager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class Wall {

  List<Location> list = new ArrayList<>();

  private final List<Material> WATER_BLOCKS = Arrays.asList(
      Material.WATER,
      Material.SEAGRASS,
      Material.TALL_SEAGRASS,
      Material.KELP,
      Material.KELP_PLANT,
      Material.LAVA);

  public Wall(Location vertex, int size) {

    // 壁
    for (int i = -1; i < size + 1; i++) {
      for (int j = -1; j < size + 1; j++) {
        for (int k = vertex.getBlockY(); k > 0; k--) {
          Location location = new Location(
              vertex.getWorld(),
              vertex.getX() - i,
              k,
              vertex.getZ() - j
          );

          Block block = location.getBlock();
          Material material = block.getType();

          if (TNTRain.config.doDeleteWater.value()) {
            // 水の消去
            if (WATER_BLOCKS.contains(material)) {
              Manager.blockSetTask.setBlock(block, Material.AIR);
            }
          }
          if (i == -1 || i == size) {
            this.list.add(location);
          } else {
            if (j == -1 || j == size) {
              this.list.add(location);
            }
          }
        }
      }
    }
  }

  void setBlock() {
    Bukkit.getLogger().info(String.valueOf(list.size()));
    for (Location location : list) {

      Block block = location.getBlock();
      if (block.getType() == Material.AIR || block.getType() == Material.CAVE_AIR
          || block.getType() == Material.BARRIER) {
        Manager.blockSetTask.setBlock(block, Material.BARRIER);
      } else {
        Manager.blockSetTask.setBlock(block, Material.BEDROCK);
      }
    }
  }

  void clear() {
    for (Location location : list) {

      Block block = location.getBlock();
      if (block.getType() == Material.BARRIER) {
        Manager.blockSetTask.setBlock(block, Material.AIR);
      } else {
        Manager.blockSetTask.setBlock(block, Material.DIRT);
      }
    }
  }
}
