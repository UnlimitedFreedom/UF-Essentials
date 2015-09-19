package me.StevenLawson.essentials;

import com.google.common.base.Function;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;

public class EssentialsHandler {

    public static final boolean DEBUG = true;
    private static Logger logger;
    private static Function<Player, Boolean> superAdminProvider;

    public static void setLogger(Logger logger) {
        EssentialsHandler.logger = logger;
    }

    public static Plugin getTFM() {
        final Plugin tfm = Bukkit.getPluginManager().getPlugin("UnlimitedFreedomMod");
        if (tfm == null) {
            logger.warning("Could not resolve plugin: UnlimitedFreedomMod");
        }

        return tfm;
    }

    @SuppressWarnings("unchecked")
    public static boolean isSuperAdmin(Player player) {

        if (superAdminProvider == null) {
            final Plugin tfm = getTFM();
            if (tfm == null) {
                return false;
            }

            Object provider = null;
            for (RegisteredServiceProvider<?> serv : Bukkit.getServicesManager().getRegistrations(tfm)) {
                if (Function.class.isAssignableFrom(serv.getService())) {
                    provider = serv.getProvider();
                }
            }

            if (provider == null) {
                warning("Could not obtain SuperAdmin service provider!");
                return false;
            }

            superAdminProvider = (Function<Player, Boolean>) provider;
        }

        return superAdminProvider.apply(player);
    }

    public static void warning(String warning) {
        logger.warning(warning);
    }

    public static void info(String info) {
        logger.info(info);
    }

}
