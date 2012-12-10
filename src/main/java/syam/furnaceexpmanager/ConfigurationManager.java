/**
 * FurnaceExpManager - Package: syam.furnaceexpmanager
 * Created: 2012/12/10 13:43:22
 */
package syam.furnaceexpmanager;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;

import syam.furnaceexpmanager.util.FileStructure;

/**
 * ConfigurationManager (ConfigurationManager.java)
 * @author syam(syamn)
 */
public class ConfigurationManager {
    /* Current config.yml File Version! */
    private final int latestVersion = 1;

    // Logger
    private static final Logger log = FurnaceExpManager.log;
    private static final String logPrefix = FurnaceExpManager.logPrefix;
    private static final String msgPrefix = FurnaceExpManager.msgPrefix;
    private final FurnaceExpManager plugin;

    // private YamlConfiguration conf;
    private FileConfiguration conf;
    private File pluginDir;

    // hookup plugin
    private boolean useVault;

    /**
     * Constructor
     */
    public ConfigurationManager(final FurnaceExpManager plugin) {
        this.plugin = plugin;

        this.pluginDir = this.plugin.getDataFolder();
    }

    /**
     * Load config.yml
     */
    public void loadConfig(final boolean initialLoad) throws Exception {
        // create directories
        FileStructure.createDir(pluginDir);

        // get config.yml path
        File file = new File(pluginDir, "config.yml");
        if (!file.exists()) {
            FileStructure.extractResource("/config.yml", pluginDir, false,
                    false);
            log.info("config.yml is not found! Created default config.yml!");
        }

        plugin.reloadConfig();
        conf = plugin.getConfig();

        checkver(conf.getInt("DontTouchThisConfig", 1));
    }

    /**
     * Check configuration file version
     */
    private void checkver(final int ver) {
        // compare configuration file version
        if (ver < latestVersion) {
            // first, rename old configuration
            final String destName = "oldconfig-v" + ver + ".yml";
            String srcPath = new File(pluginDir, "config.yml").getPath();
            String destPath = new File(pluginDir, destName).getPath();
            try {
                FileStructure.copyTransfer(srcPath, destPath);
                log.info("Copied old config.yml to " + destName + "!");
            } catch (Exception ex) {
                log.warning("Failed to copy old config.yml!");
            }

            // force copy config.yml and languages
            FileStructure
                    .extractResource("/config.yml", pluginDir, true, false);
            // Language.extractLanguageFile(true);

            plugin.reloadConfig();
            conf = plugin.getConfig();

            log.info("Deleted existing configuration file and generate a new one!");
        }
    }

    /* ***** Begin Configuration Getters *********************** */

    // Debug
    public boolean isDebug() {
        return conf.getBoolean("Debug", false);
    }
}
