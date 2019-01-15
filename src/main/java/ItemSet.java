
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import lombok.Getter;
@Getter
public class ItemSet extends ArrayList {
    private List<Item> items = Lists.newArrayList();
}
