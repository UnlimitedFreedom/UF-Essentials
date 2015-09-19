package com.earth2me.essentials.commands;

import com.earth2me.essentials.CommandSource;
import org.bukkit.ChatColor;
import org.bukkit.Server;

public class Commandrules extends EssentialsCommand {

    public Commandrules() {
        super("rules");
    }

    @Override
    public void run(final Server server, final CommandSource sender, final String commandLabel, final String[] args) throws Exception {
        sender.sendMessage(ChatColor.GREEN + "Go to http://rubyfreedom.boards.net/ for the rules.");
    }
}
