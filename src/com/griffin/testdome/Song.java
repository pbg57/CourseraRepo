package com.griffin.testdome;

import java.util.*;

/*
Create a class which controls which songs are to be played in a singly linked list. If the next song points
to a previously played song, the list is considered a repeating play list.
 */
public class Song {
    private final String name;
    private Song nextSong;

    public Song(String name) {
        this.name = name;
    }

    public void setNextSong(Song nextSong) {
        this.nextSong = nextSong;
    }

    public boolean isRepeatingPlaylist() {
        /*
        A repeating playlist has a Song whose next Song to play exists earlier in the playlist.
         */
        if (this.equals(nextSong))
            return false;       // A list of one Song. Won't consider this repeating?

        Song nextSongRef;
        TreeSet<String> stringSet = new TreeSet<>();      // Store songs in a Set which only allows unique entries
        stringSet.add(this.name);
        nextSongRef = (nextSong == null) ? null : nextSong.nextSong;

        while (nextSongRef != null) {
            if (stringSet.add(nextSongRef.name)) {      // If TreeSet does not already contain 'name'...
                nextSongRef = nextSongRef.nextSong;     // Walk forward to next Song ref and loop
            } else
                // TreeSet already contains song name, so this is a repeating playlist
                return true;
        }
        return false;   // No Song links to a previous Song
    }

    public static void main(String[] args) {
        Song first = new Song("Hello");
        Song second = new Song("Eye of the tiger");

        first.setNextSong(second);
        second.setNextSong(first);

        System.out.println(first.isRepeatingPlaylist());
    }
}
