package club.issizler.okperms.cmd;

import club.issizler.okyanus.api.Okyanus;
import club.issizler.okyanus.api.cmd.CommandRunnable;
import club.issizler.okyanus.api.cmd.CommandSource;
import club.issizler.okyanus.api.entity.Player;

import java.util.Optional;

public class HasPermCmd implements CommandRunnable {

    @Override
    public int run(CommandSource source) {
        Optional<Player> player = source.getArgPlayer("player");
        Optional<String> perm = source.getArgText("permission");

        if (!player.isPresent())
            return -1;

        if (!perm.isPresent())
            return -1;

        source.send(String.valueOf(Okyanus.getPermissionService().hasPermission(player.get(), perm.get())));
        return 1;
    }

}
