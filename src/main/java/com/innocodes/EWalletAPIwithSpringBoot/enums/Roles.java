package com.innocodes.EWalletAPIwithSpringBoot.enums;

import static com.innocodes.EWalletAPIwithSpringBoot.enums.UserPermissions.*;
import com.google.common.collect.Sets;


import java.util.Set;

public enum Roles {
    ADMIN(Sets.newHashSet(USER_READ, USER_EDIT, ACCOUNT_EDIT, ACCOUNT_READ)),
    USER(Sets.newHashSet(USER_READ, USER_EDIT));

    private final Set<UserPermissions> permissions;

    Roles(Set<UserPermissions> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermissions> getPermissions() {
        return permissions;
    }
}
