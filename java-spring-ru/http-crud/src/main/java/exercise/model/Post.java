package exercise.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Post {
    private String id;
    private String title;
    private String body;

   /* public Post(String id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }*/

    public String getId() {
        return id;
    }

    public String getBody() {
        return body;
    }


    public String getTitle() {
        return title;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
