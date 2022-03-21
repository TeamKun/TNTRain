package net.kunmc.lab.tntrain.game;

import static java.lang.Math.max;
import static java.lang.Math.min;

import com.destroystokyo.paper.event.server.ServerTickStartEvent;
import java.util.Deque;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.stream.IntStream;
import net.kunmc.lab.tntrain.TNTRain;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

public class QueuedExecutor implements Listener {

  /**
   * 1秒あたりに実行されるアクション（Runnable）の数
   */
  private int numberOfActionPerSec = 100000;

  // 実際に実行できるアクションの数。5秒ごとにリセットされ、次のターゲットを決定するために使用される
  private int count = 0;

  private Deque<Runnable> taskQueue = new ConcurrentLinkedDeque();
  private boolean isRunning;
  private final float updatePeriodSeconds = 3L;

  public QueuedExecutor() {
    new Timer().scheduleAtFixedRate(new TimerTask() {
      @Override
      public void run() {
        if (isRunning) {

          int Candidate;
          float average = count / updatePeriodSeconds;
          if (average > numberOfActionPerSec * 0.9) {
            Candidate = (int) max(1000, (numberOfActionPerSec * 1.2));
          } else {
            Candidate = (int) max(1000, (numberOfActionPerSec * 0.9));
          }

          numberOfActionPerSec = min(1000000, Candidate);
          count = 0;
        }
      }
    }, (long) updatePeriodSeconds * 1000, (long) updatePeriodSeconds * 1000);

    Bukkit.getPluginManager().registerEvents(this, TNTRain.plugin);
  }

  @EventHandler(ignoreCancelled = true)
  public void onServerTickStart(ServerTickStartEvent event) {
    IntStream.range(0, numberOfActionPerSec / 20).forEach(i -> {
      Runnable task = taskQueue.poll();

      if (task == null) {
        isRunning = false;
        count = 0;
        return;
      }

      isRunning = true;
      task.run();
      count++;
    });
  }

  /**
   * タスクを追加する
   */
  public void offer(Runnable task) {
    taskQueue.offer(task);
  }

  /**
   * タスクをクリアする
   */
  public void clear() {
    taskQueue.clear();
  }

  public void setBlock(Block block, Material material) {
    this.offer(
        new BukkitRunnable() {
          @Override
          public void run() {
            block.setType(material);
          }
        }
    );
  }
}
