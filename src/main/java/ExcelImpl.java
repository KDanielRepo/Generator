import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ExcelImpl implements Validator{
    List<String> list = new ArrayList<>();
    List<Integer> integers = new ArrayList<>();
    @ExcelAdn("test")
    String test = "nazwa pod";

    @Override
    public void valid(Object obj) {

    }
    public void setList(){
        list.add("jeden");
        list.add("dwa");
        list.add("trzy");
        list.add("cztery");
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
    }
}
