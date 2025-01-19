package uoc.ds.pr.util;

import uoc.ds.pr.LibraryPR3;

public class LevelHelper {

    public static LibraryPR3.Level getLevel(int points) {
        if (points < -10){
            return LibraryPR3.Level.ASLAN;
        }
        else if (points < 0){
            return  LibraryPR3.Level.WINDRUNNER;
        }
        else if (points < 10){
            return  LibraryPR3.Level.FREMEN;
        }
        else if (points < 20){
            return LibraryPR3.Level.HOBBIT;
        }
        else if (points < 30){
            return LibraryPR3.Level.OOMPA_LOOMPA;
        }
        else if (points < 40){
            return  LibraryPR3.Level.MUGGLE;
        }
        else {
            return  LibraryPR3.Level.TROLL;
        }

    }

}
