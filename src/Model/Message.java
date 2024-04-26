package Model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Message implements Serializable {
    private String pseudo;
    private String message;
    private LocalTime date;

    public Message(String pseudo, String message, LocalTime date) {
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

    public LocalTime getDate() {
        return date;
    }

    public void setDate(LocalTime date) {
        this.date = date;
    }
}
