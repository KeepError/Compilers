public class Token {

    private String type;
    private int id;
    private String token;

    private int start;

    private int end;

    public Token(String type, int id, String token, int start, int end) {
        this.type = type;
        this.id = id;
        this.token = token;
        this.start = start;
        this.end = end;

    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setStart(int start){
        this.start = start;
    }

    public int getStart(){
        return start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Token{" +
                "type='" + type + '\'' +
                ", id=" + id +
                ", token='" + token + '\'' +
                ", start_position='" + start + '\'' +
                ", end_position='" + end + '\'' +
                '}';
    }

    public String toJSON() {
        return String.format("{\"type\":\"%s\",\"start\":%d,\"end\":%d,\"token\":\"%s\"}", type, start, end, token);
    }
}
