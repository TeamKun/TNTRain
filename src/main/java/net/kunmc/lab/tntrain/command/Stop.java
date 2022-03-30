package net.kunmc.lab.tntrain.command;

import net.kunmc.lab.commandlib.Command;
import net.kunmc.lab.commandlib.CommandContext;
import net.kunmc.lab.tntrain.game.Manager;
import org.jetbrains.annotations.NotNull;

public class Stop extends Command {

  public Stop() {
    super(CommandNameEnum.Stop.commandName);
  }

  @Override
  protected void execute(@NotNull CommandContext ctx) {
    Manager.stop(ctx);
  }
}
