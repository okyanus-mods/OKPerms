package club.issizler.okperms;

import club.issizler.okperms.cmd.AddPermCmd;
import club.issizler.okperms.cmd.HasPermCmd;
import club.issizler.okperms.cmd.RemovePermCmd;
import club.issizler.okyanus.api.Mod;
import club.issizler.okyanus.api.cmd.ArgumentType;
import club.issizler.okyanus.api.cmd.CommandBuilder;
import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.file.FileConfig;

public class OKPermsMod extends Mod {

    static FileConfig config;

    @Override
    public void init() {
        config = CommentedFileConfig.builder("config/okperms.toml").defaultResource("/okperms.toml").autosave().build();
        config.load();

        registerCommand(
                new CommandBuilder("perms")
                        .opOnly()
                        .run(source -> -1)
                        .subCommand(new CommandBuilder("add")
                                .opOnly()
                                .arg("player", ArgumentType.PLAYER)
                                .arg("permission", ArgumentType.TEXT)
                                .run(new AddPermCmd()))
                        .subCommand(new CommandBuilder("remove")
                                .opOnly()
                                .arg("player", ArgumentType.PLAYER)
                                .arg("permission", ArgumentType.TEXT)
                                .run(new RemovePermCmd()))
                        .subCommand(new CommandBuilder("check")
                                .opOnly()
                                .arg("player", ArgumentType.PLAYER)
                                .arg("permission", ArgumentType.TEXT)
                                .run(new HasPermCmd()))
        );
    }

}
