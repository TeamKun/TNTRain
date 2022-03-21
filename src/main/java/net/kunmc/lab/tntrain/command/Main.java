package net.kunmc.lab.tntrain.command;

import dev.kotx.flylib.command.Command;
import net.kunmc.lab.configlib.ConfigCommand;

public class Main extends Command {

  public Main(ConfigCommand configCommand) {
    super(CommandNameEnum.Main.commandName);
    children(new Start(),
        configCommand);
  }
}
