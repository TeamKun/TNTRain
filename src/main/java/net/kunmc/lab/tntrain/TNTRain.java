package net.kunmc.lab.tntrain;

import net.kunmc.lab.commandlib.CommandLib;
import net.kunmc.lab.configlib.ConfigCommandBuilder;
import net.kunmc.lab.tntrain.command.Main;
import net.kunmc.lab.tntrain.game.Limit;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class TNTRain extends JavaPlugin {

  public static TNTRain plugin;
  public static Config config;

  @Override

  public void onEnable() {
    plugin = this;

    config = new Config(this);

    CommandLib.register(this, new Main(new ConfigCommandBuilder(config).build()));
    Bukkit.getPluginManager().registerEvents(new Limit(), this);
  }

  @Override
  public void onDisable() {
  }
}
