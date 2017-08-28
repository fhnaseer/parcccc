package de.tum.in.repobruegge.pom17aprilsnow.flightapp.Model;

import java.io.Serializable;

/**
 * Created by dmitriinechaev on 01/07/17.
 */

public class Trip implements Serializable {
    private String name;

    public Trip(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
