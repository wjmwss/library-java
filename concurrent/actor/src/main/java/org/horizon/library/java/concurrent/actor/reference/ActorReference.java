package org.horizon.library.java.concurrent.actor.reference;

import org.horizon.library.java.concurrent.actor.id.ActorId;
import org.horizon.library.java.concurrent.actor.message.ActorMessage;

/**
 * @author wjm
 * @since 2025-01-26 21:07
 */
public interface ActorReference {

    ActorId getSelfId();

    void tell(ActorMessage message, boolean isHighPriority);

    default void tellWithNormalPriority(ActorMessage message) {
        tell(message, false);
    }

    default void tellWithHighPriority(ActorMessage message) {
        tell(message, true);
    }

}