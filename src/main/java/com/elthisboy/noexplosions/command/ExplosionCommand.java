package com.elthisboy.noexplosions.command;

import com.elthisboy.noexplosions.NoExplosions;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class ExplosionCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
            CommandManager.literal("explosions")
                .requires(source -> source.hasPermissionLevel(2))
                .executes(ExplosionCommand::toggle)
                .then(
                    CommandManager.literal("enable")
                        .executes(ExplosionCommand::enable)
                )
                .then(
                    CommandManager.literal("disable")
                        .executes(ExplosionCommand::disable)
                )
                .then(
                    CommandManager.literal("status")
                        .executes(ExplosionCommand::status)
                )
        );
    }

    private static int toggle(CommandContext<ServerCommandSource> ctx) {
        NoExplosions.explosionsBlocked = !NoExplosions.explosionsBlocked;
        sendFeedback(ctx, NoExplosions.explosionsBlocked);
        return 1;
    }

    private static int enable(CommandContext<ServerCommandSource> ctx) {
        NoExplosions.explosionsBlocked = false;
        ctx.getSource().sendFeedback(
            () -> Text.literal("§a✔ Explosions §lENABLED§r§a. TNT, creepers and everything else will explode normally."),
            true
        );
        return 1;
    }

    private static int disable(CommandContext<ServerCommandSource> ctx) {
        NoExplosions.explosionsBlocked = true;
        ctx.getSource().sendFeedback(
            () -> Text.literal("§c✘ Explosions §lDISABLED§r§c. No explosion will cause damage or block destruction."),
            true
        );
        return 1;
    }

    private static int status(CommandContext<ServerCommandSource> ctx) {
        boolean blocked = NoExplosions.explosionsBlocked;
        ctx.getSource().sendFeedback(
            () -> Text.literal("§e⚡ Current status: Explosions are " +
                (blocked ? "§c§lDISABLED" : "§a§lENABLED")),
            false
        );
        return 1;
    }

    private static void sendFeedback(CommandContext<ServerCommandSource> ctx, boolean blocked) {
        ctx.getSource().sendFeedback(
            () -> Text.literal(blocked
                ? "§c✘ Explosions §lDISABLED§r§c. No explosion will cause damage or block destruction."
                : "§a✔ Explosions §lENABLED§r§a. TNT, creepers and everything else will explode normally."),
            true
        );
    }
}
