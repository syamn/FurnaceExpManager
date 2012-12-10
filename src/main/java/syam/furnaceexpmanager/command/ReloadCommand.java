/**
 * FurnaceExpManager - Package: syam.furnaceexpmanager.command
 * Created: 2012/12/10 14:04:34
 */
package syam.furnaceexpmanager.command;

import syam.furnaceexpmanager.Perms;
import syam.furnaceexpmanager.util.Actions;

/**
 * ReloadCommand (ReloadCommand.java)
 * @author syam(syamn)
 */
public class ReloadCommand extends BaseCommand {
    public ReloadCommand() {
        bePlayer = false;
        name = "reload";
        argLength = 0;
        usage = "<- reload config.yml";
    }

    @Override
    public void execute() {
        try {
            plugin.getConfigs().loadConfig(false);
        } catch (Exception ex) {
            log.warning(logPrefix
                    + "an error occured while trying to load the config file.");
            ex.printStackTrace();
            return;
        }
        Actions.message(sender, "&aConfiguration reloaded!");
    }

    @Override
    public boolean permission() {
        return Perms.RELOAD.has(sender);
    }
}