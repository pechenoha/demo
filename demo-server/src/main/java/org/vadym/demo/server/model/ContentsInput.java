package org.vadym.demo.server.model;

/**
 * Wrapper for document contents coming from the clients.
 *
 * @author Vadym Pechenoha
 */
public class ContentsInput {

    private String contents;

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
