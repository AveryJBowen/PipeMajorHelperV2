package app.avery.pipemajorhelperv2;

import io.realm.RealmList;
import io.realm.RealmObject;

public class MusicSet extends RealmObject {
    private RealmList<Tune> tuneList;

    public MusicSet(){

    }

    public MusicSet(RealmList<Tune> tuneList){
        this.tuneList = tuneList;
    }

    public RealmList<Tune> getTuneList() {
        return tuneList;
    }
}
