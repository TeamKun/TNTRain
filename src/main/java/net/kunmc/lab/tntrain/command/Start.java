package net.kunmc.lab.tntrain.command;

import net.kunmc.lab.commandlib.Command;
import net.kunmc.lab.commandlib.CommandContext;
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
