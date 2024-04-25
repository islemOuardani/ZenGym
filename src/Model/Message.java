package Model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Message implements Serializable {
    private String pseudo;
    private String message;
    private LocalDateTime date;

    public Message(String pseudo, String message, LocalDateTime date) {
        this.pseudo = pseudo;
        this.message = message;
        this.date = date;
    }

    @Override
    public String toString() {
        return (this.pseudo+"\n"+this.message+"\n"+this.date.toString());
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
