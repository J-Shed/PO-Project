public enum Strategy {
    AGGRESIVE,
    MIXED,
    PASSIVE;

    public static int strategy(Strategy s) {
        if (s == AGGRESIVE) return 7;
        else if (s == MIXED) return 5;
        else if (s == PASSIVE) return 3;
        return 0;
    }
}

