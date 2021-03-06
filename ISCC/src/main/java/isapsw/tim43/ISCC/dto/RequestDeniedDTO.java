package isapsw.tim43.ISCC.dto;

public class RequestDeniedDTO {
    String email;
    String explanation;

    public RequestDeniedDTO() {
    }

    public RequestDeniedDTO(String email, String explanation) {
        this.email = email;
        this.explanation = explanation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}
