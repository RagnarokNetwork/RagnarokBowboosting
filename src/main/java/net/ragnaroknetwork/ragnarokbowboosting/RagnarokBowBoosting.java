package net.ragnaroknetwork.ragnarokbowboosting;

import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.*;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class RagnarokBowBoosting extends JavaPlugin implements Listener {

    public void onEnable() {
        this.saveDefaultConfig();
        this.getCommand("ragnarokbowboosting").setExecutor(new Command());
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    public void onDisable() {
        this.getLogger().info("[RagnarokBowBoosting] Disabling plugin.");
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onMove(PlayerMoveEvent event) {
        Entity entity = event.getPlayer();
        Player player = event.getPlayer();

        if (entity.isOnGround() && !player.hasMetadata("normalShot")) {
            player.setMetadata("normalShot", new FixedMetadataValue(this, true));
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.setMetadata("normalShot", new FixedMetadataValue(this, true));
    }

    @EventHandler
    public void onEntityShootBow(EntityShootBowEvent event) {
        LivingEntity livingEntity = event.getEntity();
        Arrow arrow = (Arrow)event.getProjectile();

        double arrowMultiplier = this.getConfig().getDouble("arrowMultiplier");
        double speed = arrow.getVelocity().length();

        Vector direction = livingEntity.getLocation().getDirection();
        Vector velocity = direction.multiply(speed * arrowMultiplier);
        arrow.setVelocity(velocity);
    }

    @EventHandler
    public void onPlayerVelocity(PlayerVelocityEvent event) {
        Entity entity = event.getPlayer();

        EntityDamageEvent damageEvent = entity.getLastDamageCause();
        if (damageEvent == null) return;
        if (damageEvent.isCancelled()) return;
        if (!(damageEvent instanceof EntityDamageByEntityEvent)) return;

        Entity attacker = ((EntityDamageByEntityEvent)damageEvent).getDamager();
        if (!(attacker instanceof Arrow)) return;

        Arrow arrow = (Arrow)attacker;
        if (!arrow.getShooter().equals(entity)) {
            this.handleKnockback(event, arrow);
            return;
        }

        this.handleBoostKnockback(event, arrow);
    }

    private void handleKnockback(PlayerVelocityEvent event, Arrow arrow) {
        double horizontalMultiplier = this.getConfig().getDouble("horizontalMultiplier");
        double verticalMultiplier = this.getConfig().getDouble("verticalMultiplier");

        double speed = 0.35 + arrow.getKnockbackStrength() * 0.50;
        double speedVertical = 0.70 + arrow.getKnockbackStrength() * 0.10;
        Vector dir = arrow.getLocation().getDirection().normalize();

        Vector newVelocity = new Vector(dir.getX() * speed * horizontalMultiplier * -1.0D, speedVertical * verticalMultiplier, dir.getZ() * speed * horizontalMultiplier);
        event.setVelocity(newVelocity);
    }

    private void handleBoostKnockback(PlayerVelocityEvent event, Arrow arrow) {
        Player player = event.getPlayer();
        Vector velocity = event.getVelocity();

        double playerMultiplierHorizontal = this.getConfig().getDouble("playerMultiplierHorizontal");
        double playerMultiplierVertical = this.getConfig().getDouble("playerMultiplierVertical");
        double playerMultiplierHorizontalChain = this.getConfig().getDouble("playerMultiplierHorizontalChain");
        double playerMultiplierVerticalChain = this.getConfig().getDouble("playerMultiplierVerticalChain");

        double speed = 0.50 + arrow.getKnockbackStrength() * 0.50;
        double speedVertical = 0.50 + arrow.getKnockbackStrength() * 0.10;
        Vector dir = arrow.getLocation().getDirection().normalize();

        if (player.hasMetadata("normalShot")) {
            Vector newVelocity = new Vector(dir.getX() * speed * playerMultiplierHorizontal * -1.0D, speedVertical * playerMultiplierVertical, dir.getZ() * speed * playerMultiplierHorizontal);
            event.setVelocity(newVelocity);
            player.removeMetadata("normalShot", this);
        } else {
            Vector newVelocity = new Vector(dir.getX() * speed * playerMultiplierHorizontalChain * -1.0D, velocity.getY() * playerMultiplierVerticalChain, dir.getZ() * speed * playerMultiplierHorizontalChain);
            event.setVelocity(newVelocity);
        }
    }
}

