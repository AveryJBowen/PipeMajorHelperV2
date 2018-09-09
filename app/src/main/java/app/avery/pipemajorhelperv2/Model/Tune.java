package app.avery.pipemajorhelperv2.Model;

import io.realm.RealmObject;

public class Tune extends RealmObject {
    private String name;
    private String signature;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
