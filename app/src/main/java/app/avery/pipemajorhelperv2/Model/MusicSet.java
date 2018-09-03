package app.avery.pipemajorhelperv2.Model;

import io.realm.RealmList;
import io.realm.RealmObject;

public class MusicSet extends RealmObject {
    private String name;
    private RealmList<Tune> tuneList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTuneList(RealmList<Tune> tuneList) {
        this.tuneList = tuneList;
    }

    public RealmList<Tune> getTuneList() {
        return tuneList;
    }

    public void addTune(Tune tune){
        tuneList.add(tune);
    }

    public void removeTune(Tune tune){
        tuneList.remove(tune);
    }
}
