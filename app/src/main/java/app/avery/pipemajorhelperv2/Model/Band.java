package app.avery.pipemajorhelperv2.Model;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Band extends RealmObject {
    private String name;
    private User user;
    private RealmList<Member> roster;
    private RealmList<MusicSet> sets;
    private RealmList<Job> jobs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public RealmList<Member> getRoster() {
        return roster;
    }

    public void setRoster(RealmList<Member> roster) {
        this.roster = roster;
    }

    public RealmList<MusicSet> getSets() {
        return sets;
    }

    public void setSets(RealmList<MusicSet> sets) {
        this.sets = sets;
    }

    public RealmList<Job> getJobs() {
        return jobs;
    }

    public void setJobs(RealmList<Job> jobs) {
        this.jobs = jobs;
    }
}
