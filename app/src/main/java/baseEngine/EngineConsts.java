package baseEngine;

class EngineConsts{

    public static final String LOG_TAG = "Engine";

    public static final String DATABASE_NAME = "HeroesDB";

    public static final int DATABASE_VERSION = 1;
}

class HeroesTableConsts{

    public static final String TABLE_NAME = "heroes";

    public static final String PRIMARY_KEY = "id";

    public static final String HERO_NAME = "name";

    public static final String HERO_CASTLE_ID = "castleId";

}

class CastleTableConsts{

    public static final String T_NAME = "castles";

    public static final String P_KEY = "id";

    public static final String C_NAME = "castleName";

}
