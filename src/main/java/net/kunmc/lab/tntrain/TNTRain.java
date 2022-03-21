package net.kunmc.lab.tntrain;

import dev.kotx.flylib.FlyLib;
import net.kunmc.lab.configlib.ConfigCommandBuilder;
import net.kunmc.lab.tntrain.command.Main;
import org.bukkit.plugin.java.JavaPlugin;

public final class TNTRain extends JavaPlugin {

  public static TNTRain plugin;
  public static Config config;

  @Override

  public void onEnable() {
    plugin = this;

    config = new Config(this);
    config.saveConfigIfAbsent();
    config.loadConfig();

    FlyLib.create(this, builder -> {
      builder.command(new Main(new ConfigCommandBuilder(config).build()));
    });
  }

  @Override
  public void onDisable() {
  }
}
