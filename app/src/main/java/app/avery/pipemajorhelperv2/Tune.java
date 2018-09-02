package app.avery.pipemajorhelperv2;

import io.realm.RealmObject;

public class Tune extends RealmObject {
    private String name;

    public Tune(){

    }

    Tune(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
