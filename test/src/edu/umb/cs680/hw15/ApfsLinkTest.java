package edu.umb.cs680.hw15;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;

import java.time.LocalDateTime;

class ApfsLinkTest {
    private static APFS fs;
    private static LocalDateTime datetime;
    private static ApfsDirectory root;
    private static ApfsDirectory system;
    private static ApfsFile a;
    private static ApfsFile b;
    private static ApfsFile c;
    private static ApfsDirectory home;
    private static ApfsFile d;
    private static ApfsLink x;
    private static ApfsDirectory pictures;
    private static ApfsFile e;
    private static ApfsFile f;
    private static ApfsLink y;

    @BeforeAll
    public static void setUp() {
        fs = APFS.getFileSystem();
        datetime = LocalDateTime.of(2042, 12, 31, 12, 0);
        root = new ApfsDirectory(null, "root", datetime);
        system = new ApfsDirectory(root, "system", datetime);
        a = new ApfsFile(system, "a", 1, datetime);
        b = new ApfsFile(system, "b", 2, datetime);
        c = new ApfsFile(system, "c", 3, datetime);
        home = new ApfsDirectory(root, "home", datetime);
        d = new ApfsFile(home, "d", 4, datetime);
        x = new ApfsLink(home, "x", datetime, system);
        pictures = new ApfsDirectory(home, "pictures", datetime);
        e = new ApfsFile(pictures, "e", 5, datetime);
        f = new ApfsFile(pictures, "f", 6, datetime);
        y = new ApfsLink(pictures, "y", datetime, e);
    }

    private String[] linkToStringArray(ApfsLink l){
        String[] linkInfo = {
            l.isFile().toString(), l.isDirectory().toString(),
            l.getName(), l.getParent().toString(),
            l.getTarget().toString(),
            l.getSize().toString(), 
            l.getCreationTime().toString(),
        };
        return linkInfo;
    }

    @Test
    public void verifyLinkEqualityX() {
        String[] expected = {"false", "false",
                             "x", "home",
                             "system",
                             "0",
                             datetime.toString(),
        };
        String[] actual = linkToStringArray(x);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void verifyFileEqualityY() {
        String[] expected = {"false", "false",
                             "y", "pictures",
                             "e",
                             "0",
                             datetime.toString(),
        };
        String[] actual = linkToStringArray(y);
        assertArrayEquals(expected, actual);
    }

    @AfterAll
    public static void doSomething() {
        fs.erase();
    }

}

