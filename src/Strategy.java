public enum Strategy {
    AGGRESSIVE,
    MIXED,
    PASSIVE;

    public static int strategy(Strategy s) {
        if (s == AGGRESSIVE) return 7;
        else if (s == MIXED) return 5;
        else if (s == PASSIVE) return 3;
        return 0;
    }
}

