/**
 * FurnaceExpManager - Package: syam.furnaceexpmanager.listener
 * Created: 2012/12/10 14:13:25
 */
package syam.furnaceexpmanager.listener;

import java.util.logging.Logger;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceExtractEvent;

import syam.furnaceexpmanager.ConfigurationManager;
import syam.furnaceexpmanager.FurnaceExpManager;
import syam.furnaceexpmanager.util.Actions;
import syam.furnaceexpmanager.util.Util;

/**
 * BlockListener (BlockListener.java)
 *
 * @author syam(syamn)
 */
public class BlockListener implements Listener {
    public final static Logger log = FurnaceExpManager.log;
    private static final String logPrefix = FurnaceExpManager.logPrefix;
    private static final String msgPrefix = FurnaceExpManager.msgPrefix;

    private final FurnaceExpManager plugin;
    private final ConfigurationManager conf;

    public BlockListener(final FurnaceExpManager plugin) {
        this.plugin = plugin;
        this.conf = plugin.getConfigs();
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onFurnaceExtract(final FurnaceExtractEvent event) {
        final Player player = event.getPlayer();

        String valueStr = conf.getExpValue(event.getItemType().name());
        if (conf.isDebug()) Actions.message(player, "[Debug] Get config value: " + ((valueStr == null) ? "null" : valueStr));
        if (valueStr == null) return;

        boolean perc = false;
        if (valueStr.endsWith("%")){
            perc = true;
            valueStr = valueStr.replace("%", "");
            if (conf.isDebug()) Actions.message(player, "[Debug] Using percentage value: " + valueStr);
        }

        if (!Util.isDouble(valueStr)){
            log.warning(logPrefix + "Material " + event.getItemType().name() + " value is not valid!");
            return;
        }

        double value = Double.parseDouble(valueStr);
        int result = 0;
        if (perc){
            value = value / 100;
            result = (int)(event.getExpToDrop() * value);
            if (conf.isDebug()) Actions.message(player, "[Debug] " + event.getExpToDrop() + "*" + value + " => (int)" + event.getExpToDrop() * value);
        }
        else{
            result = (int)(event.getItemAmount() * value);
            if (conf.isDebug()) Actions.message(player, "[Debug] " + event.getItemAmount() + "*" + value + " => (int)" + event.getItemAmount() * value);
        }

        if (conf.isDebug()) Actions.message(player, "Set ExpAmount: " + result);
        event.setExpToDrop(result);
    }
}
