package edu.umb.cs680.hw06;

import java.time.LocalDateTime;

public class Link extends FSElement {

    private FSElement target;

    public Link(Directory parentDir,
                String name,
                LocalDateTime creationTime,
                FSElement target) {
        // why not get creationTime as time of constructor call?
        // creationTime = LocalDateTime.now();
        super(parentDir, name, 0, creationTime);
        this.target = target;
    }; 

    public FSElement getTarget() {
        return this.target;
    }

    public Boolean isDirectory() {
        return false;
    }

    public Boolean isLink() {
        return true;
    }


    public Boolean isFile() {
        return false;
    }


}
