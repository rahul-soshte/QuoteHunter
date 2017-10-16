package com.hunterlab.hunter.motiva;

/**
 * Created by hunter on 12/10/17.
 */

public class Author {
    public String id;
    public String authorname;

    public Author(String authorname)
    {

        this.authorname=authorname;
    }

    public Author()
    {

    }


    public String getAuthorname() {
        return authorname;
    }

    public void setId(String id)
    {
        this.id=id;
    }
    public String getId()
    {
        return id;
    }
}
