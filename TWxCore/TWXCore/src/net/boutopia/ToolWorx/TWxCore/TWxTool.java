/**
 * 
 */
package net.boutopia.ToolWorx.TWxCore;

import java.util.Iterator;
import java.util.Set;

import net.boutopia.ToolWorx.MechArchitech.ArchitechBlueprint;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 * @author bulshavik
 * 
 */
public class TWxTool {

	private ArchitechBlueprint blueprint;

	/**
	 * Base Location of the blueprint. All other blocks are referenced relative to this location
	 */
	private Location AnchorLoc;
	
	/**
	 * Flag set if this tool is running 
	 */
	private Boolean Running;
	private Player Owner;
	private Plugin plugin;

	public void SetLocation(Location loc) {
		AnchorLoc = loc;
	}

	/**
	 * Prints a list of blocks in the current blueprint and prints the location of its Anchor if it exists
	 * @param None
	 * @return nothing
	 * 
	 */
	public void listblocks() {
		Set<String> keys = blueprint.getkeySet();
		Iterator<String> iterator = keys.iterator();
		String key;
		while (iterator.hasNext()) {
			key = iterator.next();
			plugin.getLogger().info(
					key
							+ ":"
							+ blueprint.getBlock(key).getMaterial()
							+ " X:"
							+ Integer.toString(blueprint.getBlock(key)
									.getXoffset())
							+ " Y:"
							+ Integer.toString(blueprint.getBlock(key)
									.getYoffset())
							+ " Z:"
							+ Integer.toString(blueprint.getBlock(key)
									.getZoffset()));

		}
		Location Anchor = blueprint.GetAnchorLocation();
		if (Anchor == null)
			plugin.getLogger().info("No Anchor Set");
		else
			plugin.getLogger().info("Anchor Location:" + Anchor.toString());
	}

	public TWxTool(Plugin plugin, ArchitechBlueprint Bprint, Location AnchorLoc) {
		this.plugin = plugin;
		plugin.getLogger().info("Tool contructor start");
		Bprint.listblocks();
		blueprint = Bprint.Clone(plugin);
		this.AnchorLoc = AnchorLoc;
		Set<String> keys = blueprint.getkeySet();
		Iterator<String> iterator = keys.iterator();
		String key;
		while (iterator.hasNext()) {
			key = iterator.next();
			plugin.getLogger().info(
					key
							+ ":"
							+ blueprint.getBlock(key).getMaterial()
							+ " X:"
							+ Integer.toString(blueprint.getBlock(key)
									.getXoffset())
							+ " Y:"
							+ Integer.toString(blueprint.getBlock(key)
									.getYoffset())
							+ " Z:"
							+ Integer.toString(blueprint.getBlock(key)
									.getZoffset()));

		}

		plugin.getLogger().info("Tool contructor end");

	}

	public Boolean verify() {
		Block Anchor = AnchorLoc.getWorld().getBlockAt(AnchorLoc);
		Block B;
		if (!Anchor.getType().toString()
				.equals(blueprint.getBlock("Anchor").getMaterial()))
			return false;
		Set<String> keys = blueprint.getkeySet();
		Iterator<String> iterator = keys.iterator();
		String key;
		while (iterator.hasNext()) {
			key = iterator.next();
			B = Anchor.getRelative(blueprint.getBlock(key).getXoffset(),
					blueprint.getBlock(key).getYoffset(),
					blueprint.getBlock(key).getZoffset());
			if (!B.getType().toString()
					.equals(blueprint.getBlock(key).getMaterial()))
				return false;

		}
		return true;
	}

	public void rotateCW() {
		Erase();
		Block Anchor = AnchorLoc.getWorld().getBlockAt(AnchorLoc);
		int holding;
		Anchor.setType(Material.getMaterial(blueprint.getBlock("Anchor")
				.getMaterial()));
		Set<String> keys = blueprint.getkeySet();
		Iterator<String> iterator = keys.iterator();
		String key;
		while (iterator.hasNext()) {
			key = iterator.next();
			holding = blueprint.getBlock(key).getXoffset();
			blueprint.getBlock(key).setXoffset(
					-blueprint.getBlock(key).getZoffset());
			// if (holding < 0) Blueprint.getBlock(key).setZoffset(-holding);
			// else
			blueprint.getBlock(key).setZoffset(holding);

		}

		Draw();

	}

	/**
	 * Called by the Core Plugin when the tool needs to update.
	 * @param none
	 * @return <b> true </b> if the tool exist and is running </br>  <b> false </b> if the tool should be removed from the active tool list
	 * 
	 */
	public boolean update() {
		Erase();
		AnchorLoc.add(1, 0, 0);
		Draw();
		
		return true ;
	}

	/**
	 * Called to change every block in the tool to Air. This function should be called prior to a draw 
	 * @param none
	 * @return Nothing
	 */
	private void Erase() {
		Block Anchor = AnchorLoc.getWorld().getBlockAt(AnchorLoc);
		Block B;
		Anchor.setType(Material.AIR);
		Set<String> keys = blueprint.getkeySet();
		Iterator<String> iterator = keys.iterator();
		String key;
		while (iterator.hasNext()) {
			key = iterator.next();
			B = Anchor.getRelative(blueprint.getBlock(key).getXoffset(),
					blueprint.getBlock(key).getYoffset(),
					blueprint.getBlock(key).getZoffset());
			B.setType(Material.AIR);

		}
	}

	public void Draw() {
		Block Anchor = AnchorLoc.getWorld().getBlockAt(AnchorLoc);
		Block B;
		Anchor.setType(Material.getMaterial(blueprint.getBlock("Anchor")
				.getMaterial()));
		Set<String> keys = blueprint.getkeySet();
		Iterator<String> iterator = keys.iterator();
		String key;
		while (iterator.hasNext()) {
			key = iterator.next();
			B = Anchor.getRelative(blueprint.getBlock(key).getXoffset(),
					blueprint.getBlock(key).getYoffset(),
					blueprint.getBlock(key).getZoffset());
			B.setType(Material.getMaterial(blueprint.getBlock(key)
					.getMaterial()));

		}
	}

	public Location getLocation() {
		// TODO Auto-generated method stub
		return AnchorLoc;
	}

}
