package com.griffin.testdome;

import java.util.*;

public class Song {
    private String name;
    private Song nextSong;

    public Song(String name) {
        this.name = name;
    }

    public void setNextSong(Song nextSong) {
        this.nextSong = nextSong;
    }

    public boolean isRepeatingPlaylist() {
        // Walk through next song to look for match
        if (this.equals(nextSong))
            return false;
        Song nextSongRef = null;
        TreeSet<String> stringSet = new TreeSet<>();
        stringSet.add(this.name);
        nextSongRef = ((nextSong == null) || (nextSong.nextSong == null)) ? null : nextSong.nextSong;
        while (nextSongRef != null) {
            if (stringSet.add(nextSongRef.name)) {
                nextSongRef = nextSongRef.nextSong;
            } else
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Song first = new Song("Hello");
        Song second = new Song("Eye of the tiger");

        first.setNextSong(second);
        second.setNextSong(first);

        System.out.println(first.isRepeatingPlaylist());
    }
}
