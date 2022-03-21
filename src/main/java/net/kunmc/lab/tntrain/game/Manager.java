package net.kunmc.lab.tntrain.game;

import dev.kotx.flylib.command.CommandContext;
import net.kunmc.lab.tntrain.TNTRain;
import org.bukkit.Location;

public class Manager {

  private static Game game = new Game();
  public static QueuedExecutor blockSetTask = new QueuedExecutor();

  public static void start(CommandContext ctx) {
    if (game.isRunning()) {
      ctx.fail("ゲームが実行中です");
      return;
    }

    if (!Manager.game.existsArea()) {
      ctx.fail("エリアが設定されていません");
      return;
    }

    if (TNTRain.config.center.value() == null) {
      ctx.fail("中心地が設定されていません");
      return;
    }

    game.start();
    ctx.success("ゲームを開始します");

  }

  public static void stop(CommandContext ctx) {
    if (!game.isRunning()) {
      ctx.fail("ゲームが実行中ではありません");
      return;
    }

    game.stop();
    ctx.success("ゲームを停止しました");

  }

  public static void setArea(Location location) {
    game.setArea(location);
  }

  public static void setArea(int size) {
    game.setArea(size);
  }
}
