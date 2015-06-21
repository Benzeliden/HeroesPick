package baseEngine;

import java.util.ArrayList;
import java.util.List;

public class DataProvider {

    private List<PickModel> heroesList;

    public DataProvider(){
        this.heroesList = new ArrayList<PickModel>();

        heroesList.add(new PickModel(1,1,false,"Сорша"));
        heroesList.add(new PickModel(2,2,false,"Ивор"));
    }

    public List<PickModel> GetModels() {

        return heroesList;
    }
}
