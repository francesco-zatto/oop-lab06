/**
 * 
 */
package it.unibo.collections.social.impl;

import it.unibo.collections.social.api.SocialNetworkUser;
import it.unibo.collections.social.api.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * 
 * This will be an implementation of
 * {@link SocialNetworkUser}:
 * 1) complete the definition of the methods by following the suggestions
 * included in the comments below.
 * 
 * @param <U>
 *            Specific {@link User} type
 */
public final class SocialNetworkUserImpl<U extends User> extends UserImpl implements SocialNetworkUser<U> {

    private static final int AGE_UNDEFINED = -1;

    private final Map<U, String> followedUsers;

    public SocialNetworkUserImpl(final String name, final String surname, final String user, final int userAge) {
        super(name, surname, user, userAge);
        this.followedUsers = new HashMap<>();
    }

    public SocialNetworkUserImpl(String firstName, String lastName, String username, String group) {
        this(firstName, lastName, username, AGE_UNDEFINED);
    }

    @Override
    public boolean addFollowedUser(final String circle, final U user) {
        if (this.followedUsers.containsKey(user)) {
            return false;
        }
        this.followedUsers.put(user, circle);
        return true;
    }

    @Override
    public Collection<U> getFollowedUsersInGroup(final String groupName) {
        Collection<U> followedUsersInGroup = new HashSet<>();
        for (final U user : this.followedUsers.keySet()) {
            if (this.followedUsers.get(user) == groupName) {
                followedUsersInGroup.add(user);
            }
        }
        return followedUsersInGroup;
    }

    @Override
    public List<U> getFollowedUsers() {
        return List.copyOf(this.followedUsers.keySet());
    }
}
