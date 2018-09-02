package app.avery.pipemajorhelperv2;

import io.realm.RealmObject;

public class Member extends RealmObject {
    private String name;
    private String rank;
    private int yearJoined;

    public Member(){

    }

    public Member(String name, String rank, int yearJoined) {
        this.name = name;
        this.rank = rank;
        this.yearJoined = yearJoined;
    }

    public Member(String name, String rank) {
        this.name = name;
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public int getYearJoined() {
        return yearJoined;
    }

    public void setYearJoined(int yearJoined) {
        this.yearJoined = yearJoined;
    }
}
