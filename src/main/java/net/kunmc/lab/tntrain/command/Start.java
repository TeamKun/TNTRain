package net.kunmc.lab.tntrain.command;

import dev.kotx.flylib.command.Command;
import dev.kotx.flylib.command.CommandContext;
import net.kunmc.lab.tntrain.game.Manager;
import org.jetbrains.annotations.NotNull;

public class Start extends Command {

  public Start() {
    super(CommandNameEnum.Start.commandName);
  }

  @Override
  public void execute(@NotNull CommandContext ctx) {
    Manager.start(ctx);
  }
}
