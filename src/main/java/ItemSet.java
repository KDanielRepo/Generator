
import com.google.common.collect.Lists;
import java.util.List;
import lombok.Getter;
@Getter
public class ItemSet extends AbstractNameEntity {
    private List<Item> items = Lists.newArrayList();
}
