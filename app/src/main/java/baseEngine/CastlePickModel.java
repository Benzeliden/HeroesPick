package baseEngine;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CastlePickModel {

    public CastlePickModel(int id, String name, boolean checked) {
        Id = id;
        CastleName = name;
        Checked = checked;
        HeroMap = new HashMap<>();
    }

    public int Id;

    public String CastleName;

    public boolean Checked;

    public Map<Integer, PickModel> HeroMap;

    public Collection<PickModel> GetHeroList(){
        return HeroMap.values();
    };

    public void AddHero(PickModel hero){
        if (HeroMap.containsKey(hero.Id))
            return;
        HeroMap.put(hero.Id, hero);
    }


}
