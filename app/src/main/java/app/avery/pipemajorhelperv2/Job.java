package app.avery.pipemajorhelperv2;

import io.realm.RealmObject;

public class Job extends RealmObject {
    private String name;
    public Job(){

    }

    public Job(String name){
        this.name = name;
    }
}
