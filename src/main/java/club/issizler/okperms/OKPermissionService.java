package club.issizler.okperms;

import club.issizler.okyanus.api.perms.Permissible;
import club.issizler.okyanus.api.perms.PermissionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OKPermissionService implements PermissionService {

    private Optional<List<String>> getConfigKey(Permissible p) {
        return OKPermsMod.config.getOptional("permissions." + p.getIdentifier());
    }

    @Override
    public boolean hasPermission(Permissible permissible, String perm) {
        if (permissible.getIdentifier().equals("CONSOLE"))
            return true;

        if (permissible.getIdentifier().equals("UNKNOWN"))
            return false;

        Optional<List<String>> perms = getConfigKey(permissible);

        if (!perms.isPresent())
            return false;

        if (perms.get().contains("*"))
            return true;

        for (String permission : perms.get()) {
            if (permission.endsWith(".*") && perm.startsWith(permission.substring(0, permission.length() - 2))) {
                return true;
            }
        }

        return perms.get().contains(perm);
    }

    @Override
    public void addPermission(Permissible permissible, String perm) {
        if (hasPermission(permissible, perm))
            return;

        Optional<List<String>> optionalPerms = getConfigKey(permissible);
        List<String> perms = optionalPerms.orElseGet(ArrayList::new);

        perms.add(perm);
        OKPermsMod.config.set("permissions." + permissible.getIdentifier(), perms);
    }

    @Override
    public void removePermission(Permissible permissible, String perm) {
        if (!hasPermission(permissible, perm))
            return;

        Optional<List<String>> optionalPerms = getConfigKey(permissible);

        if (!optionalPerms.isPresent())
            return; // Nothing to remove!

        List<String> perms = optionalPerms.get();

        perms.remove(perm);
        OKPermsMod.config.set("permissions." + permissible.getIdentifier(), perms);
    }

}
