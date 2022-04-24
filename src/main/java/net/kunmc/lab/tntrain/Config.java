package net.kunmc.lab.tntrain;

import net.kunmc.lab.configlib.BaseConfig;
import net.kunmc.lab.configlib.value.BooleanValue;
import net.kunmc.lab.configlib.value.DoubleValue;
import net.kunmc.lab.configlib.value.IntegerValue;
import net.kunmc.lab.configlib.value.LocationValue;
import net.kunmc.lab.tntrain.game.Manager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class Config extends BaseConfig {

  public LocationValue center = new LocationValue(new Location(Bukkit.getWorld("world"), 0, 0, 0));
  public IntegerValue areaSize = new IntegerValue(64);
  public BooleanValue doDeleteWater = new BooleanValue(true);
  public DoubleValue rainfallRate = new DoubleValue(1.0);
  public IntegerValue explosivePower = new IntegerValue(4);
  public IntegerValue ceilingHeight = new IntegerValue(255);

  public Config(@NotNull Plugin plugin) {
    super(plugin);
    center.onModify(Manager::setArea);
  }
}