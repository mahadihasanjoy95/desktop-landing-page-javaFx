package sample.data.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Bookmarks {
    private String userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bookmarks bookmarks = (Bookmarks) o;

        return id != null ? id.equals(bookmarks.id) : bookmarks.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public Bookmarks(Long id) {
        this.id = id;
    }

    private Long id;
}
