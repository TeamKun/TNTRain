package net.kunmc.lab.tntrain.command;

import net.kunmc.lab.commandlib.Command;
import net.kunmc.lab.configlib.ConfigCommand;

public class Main extends Command {

  public Main(ConfigCommand configCommand) {

    super(CommandNameEnum.Main.commandName);
    addChildren(new Start(),
        new Stop(),
        configCommand);
  }
}
