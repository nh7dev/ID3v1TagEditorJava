package dev.nh7.id3;

import java.io.File;
import java.io.IOException;

public class Application {

    public static void main(String[] args) {
        System.out.println("started");

        File file = new File("song.mp3");
        writeID3(file);
        readID3(file);

        System.out.println("done");
    }

    private static void writeID3(File file) {
        ID3v1TagEditor editor = new ID3v1TagEditor();
        ID3v1Tags tags = new ID3v1Tags(
                "some title",
                "some artist",
                "some album",
                "2021",
                "some comment",
                7 //7=Hip-Hop,13=Pop,17=Rock,... (https://en.wikipedia.org/wiki/List_of_ID3v1_Genres)
        );

        try {
            editor.writeID3v1Tags(file, tags);
            System.out.println("write success");
        } catch (IOException e) {
            System.out.println("write error:");
            e.printStackTrace();
        }
    }

    private static void readID3(File file) {
        ID3v1TagEditor editor = new ID3v1TagEditor();


        ID3v1Tags tags;
        try {
            tags = editor.readID3v1Tags(file);
            System.out.println("read success");
        } catch (IOException e) {
            System.out.println("read error:");
            e.printStackTrace();
            return;
        }

        if (tags == null) {
            System.out.println("no tags found");
            return;
        }

        System.out.println(tags.getTitle());
        System.out.println(tags.getArtist());
        System.out.println(tags.getAlbum());
        System.out.println(tags.getYear());
        System.out.println(tags.getComment());
        System.out.println(tags.getGenreId());
    }
}
