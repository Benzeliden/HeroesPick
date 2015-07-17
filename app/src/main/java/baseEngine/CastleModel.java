package baseEngine;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CastleModel {

    public CastleModel(int id, String name) {
        Id = id;
        CastleName = name;
        HeroMap = new HashMap<>();
    }

    public int Id;

    public String CastleName;

    public Map<Integer, HeroModel> HeroMap;

    public Collection<HeroModel> getHeroList() {
        return HeroMap.values();
    }

    public HeroModel getHero(int id) {
        return HeroMap.get(id);
    }

    public int getHeroCount() {
        return HeroMap.size();
    }

    public void addHero(HeroModel hero) {
        if (HeroMap.containsKey(hero.Id))
            return;
        HeroMap.put(hero.Id, hero);
    }


}
