package cn.srd.library.java.tool.lang.core.pieline;

import cn.srd.library.java.tool.lang.core.asserts.Assert;
import cn.srd.library.java.tool.lang.core.pipeline.NodeConsumer1;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

@Setter
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
public class CheckUserAuthorizationNode implements NodeConsumer1<UserVO> {

    private static final class SingleTonHolder {
        private static final CheckUserAuthorizationNode INSTANCE = new CheckUserAuthorizationNode();
    }

    public static CheckUserAuthorizationNode getInstance() {
        return SingleTonHolder.INSTANCE;
    }

    @Override
    public void handle(UserVO userVO) {
        Assert.INSTANCE.set("用户鉴权不通过").throwsIfFalse(userVO.getAuthorizationPass());
    }

    public static boolean enable(UserVO userVO) {
        return true;
    }

}
