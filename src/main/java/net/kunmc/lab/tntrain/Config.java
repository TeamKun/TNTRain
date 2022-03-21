package net.kunmc.lab.tntrain;

import net.kunmc.lab.configlib.BaseConfig;
import net.kunmc.lab.configlib.value.BooleanValue;
import net.kunmc.lab.configlib.value.DoubleValue;
import net.kunmc.lab.configlib.value.IntegerValue;
import net.kunmc.lab.configlib.value.LocationValue;
import net.kunmc.lab.tntrain.game.Manager;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class Config extends BaseConfig {

  public LocationValue center = new LocationValue(null);
  public IntegerValue areaSize = new IntegerValue(64);
  public BooleanValue doDeleteWater = new BooleanValue(true);
  public DoubleValue rainfallRate = new DoubleValue(20.0);

  public Config(@NotNull Plugin plugin) {
    super(plugin);
    center.onModify((location) -> Manager.setArea(location));
    areaSize.onModify((integer) -> Manager.setArea(integer));
  }
}