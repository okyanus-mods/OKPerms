package club.issizler.okperms;

import club.issizler.okyanus.api.Mod;
import club.issizler.okyanus.api.cmd.CommandBuilder;
import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.file.FileConfig;

public class OKPermsMod extends Mod {

    static FileConfig config;

    @Override
    public void init() {
        config = CommentedFileConfig.builder("config/okperms.toml").defaultResource("/config.toml").autosave().build();
        config.load();

        registerCommand(
                new CommandBuilder("hello")
                        .run(source -> {
                            source.send("Hello, world!");
                            return 1;
                        })
        );
    }

}
