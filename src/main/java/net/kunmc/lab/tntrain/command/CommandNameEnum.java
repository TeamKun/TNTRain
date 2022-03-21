package net.kunmc.lab.tntrain.command;

public enum CommandNameEnum {
  Main("tntrain"),
  Start("start"),
  Stop("stop");

  public String commandName;

  CommandNameEnum(String commandName) {
    this.commandName = commandName;
  }
}
