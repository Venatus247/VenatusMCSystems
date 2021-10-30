package de.venatus247.vutils.utils.scoreboard;

import de.venatus247.vutils.VUtils;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class VScoreboard {

    private final HashMap<UUID, Scoreboard> playerScoreboards = new HashMap<>();

    public VScoreboard() {
    }

    /**
     * Get the player specific spigot scoreboard
     * @param player The player the scoreboard is requested from
     * @return Scoreboard
     */
    @Deprecated
    public Scoreboard getScoreboard(Player player) {
        if(playerScoreboards.containsKey(player.getUniqueId()))
            return playerScoreboards.get(player.getUniqueId());

        Scoreboard scoreboard = VUtils.getInstance().getMain().getServer().getScoreboardManager().getNewScoreboard();
        playerScoreboards.put(player.getUniqueId(), scoreboard);

        return scoreboard;
    }

    /**
     * Get the player specific spigot scoreboard
     * @param uuid Uuid of the player the scoreboard is requested from
     * @return Scoreboard of given uuid
     */
    public Scoreboard getScoreboard(UUID uuid) {
        if(playerScoreboards.containsKey(uuid))
            return playerScoreboards.get(uuid);

        Scoreboard scoreboard = VUtils.getInstance().getMain().getServer().getScoreboardManager().getNewScoreboard();
        playerScoreboards.put(uuid, scoreboard);

        return scoreboard;
    }

    /**
     * Define the sidebar scorebaord for the given player
     * @param player The player the scoreboard should be set for
     * @param title The title of the scoreboard
     * @param list a list of id and score that should be displayed (dynamic content is set separately)
     */
    public void setSidebar(Player player, String title, Map<String, Integer> list) {
        Scoreboard scoreboard = getScoreboard(player);
        Objective obj = scoreboard.getObjective(player.getName());

        if(obj != null)
            obj.unregister();

        obj = scoreboard.registerNewObjective(player.getName(), "dummy");
        obj.setDisplayName(title);
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        for(Map.Entry<String, Integer> entry : list.entrySet())
            obj.getScore(entry.getKey()).setScore( entry.getValue() );

        player.setScoreboard(scoreboard);
    }

    /**
     * Update an entry of a player specific scoreboard
     * @param player The Player instance that the scoreboard entry should be updated for
     * @param id The id or name of the entry
     * @param entry the main identifier for a scoreboard entry, mostly needs to be a unique combination of color codes to be not visible
     * @param prefix everything before 'entry'
     * @param suffix everything after 'entry', mostly the main part of the visible content
     */
    public void updateEntry(Player player, String id, String entry, String prefix, String suffix) {
        Scoreboard scoreboard = getScoreboard(player);
        Team team = scoreboard.getTeam(id);

        if(team == null)
            team = scoreboard.registerNewTeam(id);

        team.addEntry(entry);

        team.setPrefix(prefix);
        team.setSuffix(suffix);
    }

}
