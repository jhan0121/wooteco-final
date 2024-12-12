package oncall.model;

public enum Month {
    JAN(1, 31),
    FEB(2, 28),
    MAR(3, 31),
    APR(4, 30),
    MAY(5, 31),
    JUN(6, 30),
    JUL(7, 31),
    AUG(8, 31),
    SEB(9, 30),
    OCT(10, 31),
    NOV(11, 30),
    DEC(12, 31);

    private int month;
    private int totalDay;

    Month(int month, int totalDay) {
        this.month = month;
        this.totalDay = totalDay;
    }

    public int getMonth() {
        return month;
    }

    public int getTotalDay() {
        return totalDay;
    }
}

