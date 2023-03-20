package cn.srd.itcp.sugar.component.actor.thingsboard.core;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启用 actor
 *
 * @author wjm
 * @since 2023-03-20 11:04:19
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({DefaultActorSystem.class, ActorSystemContext.class})
public @interface EnableActor {
}
