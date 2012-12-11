/**
 * FurnaceExpManager - Package: syam.furnaceexpmanager
 * Created: 2012/12/10 13:43:08
 */
package syam.furnaceexpmanager;

import org.bukkit.permissions.Permissible;

/**
 * Perms (Perms.java)
 * 
 * @author syam(syamn)
 */
public enum Perms {
    /* 権限ノード */
    // Admin Commands
    RELOAD("reload"),

    ;

    // ノードヘッダー
    final String HEADER = "furnaceexp.";
    private String node;

    /**
     * コンストラクタ
     * 
     * @param node
     *            権限ノード
     */
    Perms(final String node) {
        this.node = HEADER + node;
    }

    /**
     * 指定したプレイヤーが権限を持っているか
     * 
     * @param player
     *            Permissible. Player, CommandSender etc
     * @return boolean
     */
    public boolean has(final Permissible perm) {
        if (perm == null)
            return false;
        return perm.hasPermission(node); // only support SuperPerms
    }
}
