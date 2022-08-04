package com.example.textreader;

import java.util.List;

public class TextFile {

    private String path;

    private List<String> content;

    public TextFile(String path, List<String> content) {
        this.path = path;
        this.content = content;
    }

    public String getPath() {
        return this.path;
    }

    public List<String> getContent() {
        return this.content;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof TextFile)) return false;
        final TextFile other = (TextFile) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$path = this.getPath();
        final Object other$path = other.getPath();
        if (this$path == null ? other$path != null : !this$path.equals(other$path)) return false;
        final Object this$content = this.getContent();
        final Object other$content = other.getContent();
        if (this$content == null ? other$content != null : !this$content.equals(other$content)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof TextFile;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $path = this.getPath();
        result = result * PRIME + ($path == null ? 43 : $path.hashCode());
        final Object $content = this.getContent();
        result = result * PRIME + ($content == null ? 43 : $content.hashCode());
        return result;
    }

    public String toString() {
        return "TextFile(path=" + this.getPath() + ", content=" + this.getContent() + ")";
    }
}
