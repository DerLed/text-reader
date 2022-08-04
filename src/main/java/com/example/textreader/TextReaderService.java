package com.example.textreader;

public class TextReaderService {
    private TextFile textFile;

    public TextReaderService() {
    }


    public TextFile getTextFile() {
        return this.textFile;
    }

    public void setTextFile(TextFile textFile) {
        this.textFile = textFile;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof TextReaderService)) return false;
        final TextReaderService other = (TextReaderService) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$textFile = this.getTextFile();
        final Object other$textFile = other.getTextFile();
        if (this$textFile == null ? other$textFile != null : !this$textFile.equals(other$textFile)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof TextReaderService;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $textFile = this.getTextFile();
        result = result * PRIME + ($textFile == null ? 43 : $textFile.hashCode());
        return result;
    }

    public String toString() {
        return "TextReaderService(textFile=" + this.getTextFile() + ")";
    }
}
