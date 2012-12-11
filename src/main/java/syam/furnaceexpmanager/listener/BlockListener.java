/**
 * FurnaceExpManager - Package: syam.furnaceexpmanager.listener
 * Created: 2012/12/10 14:13:25
 */
package syam.furnaceexpmanager.listener;

import java.util.logging.Logger;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceExtractEvent;

import syam.furnaceexpmanager.FurnaceExpManager;
import syam.furnaceexpmanager.util.Actions;

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

    public BlockListener(final FurnaceExpManager plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onFurnaceExtract(final FurnaceExtractEvent event) {
        Actions.debug("&c=========");
        Actions.debug("getPlayer().getName(): "
                + ((event.getPlayer() != null) ? event.getPlayer().getName()
                        : "null"));
        Actions.debug("getBlock().getType().name(): "
                + ((event.getBlock() != null) ? event.getBlock().getType()
                        .name() : "null"));
        Actions.debug("getItemType().name(): "
                + ((event.getItemType() != null) ? event.getItemType().name()
                        : "null"));
        Actions.debug("getExpToDrop(): " + event.getExpToDrop());
        Actions.debug("event.getItemAmount(): " + event.getItemAmount());
    }
}
