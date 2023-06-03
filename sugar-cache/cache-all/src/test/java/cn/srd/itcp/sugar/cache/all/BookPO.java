package cn.srd.itcp.sugar.cache.all;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@SuperBuilder(toBuilder = true)
public class BookPO implements Serializable {

    @Serial private static final long serialVersionUID = 5858155802780007051L;

    private Long id;

    private String name;

}
