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
public class CheckUserLoginNode implements NodeConsumer1<UserVO> {

    private static final class SingleTonHolder {
        private static final CheckUserLoginNode INSTANCE = new CheckUserLoginNode();
    }

    public static CheckUserLoginNode getInstance() {
        return SingleTonHolder.INSTANCE;
    }

    @Override
    public void handle(UserVO userVO) {
        Assert.INSTANCE.set("用户未登录").throwsIfFalse(userVO.getLoginPass());
    }

}
