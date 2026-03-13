public class Feedback 
{
    private String firstName;
    private String lastName;
    private String email;
    private String completeFeedback;
    private int reviewID;
    private boolean longFeedback;

    private static int nextID = 1;

    public Feedback(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.reviewID = nextID++;
    }

    public void analyseFeedback(boolean isConcatenation, String sent1, String sent2,
                                String sent3, String sent4, String sent5) {
        if (isConcatenation) {
            this.completeFeedback = feedbackUsingConcatenation(sent1, sent2, sent3, sent4, sent5);
        } else {
            StringBuilder sb = feedbackUsingStringBuilder(sent1, sent2, sent3, sent4, sent5);
            this.completeFeedback = sb.toString();
        }
        boolean isLong = checkFeedbackLength(this.completeFeedback);
        createReviewID(this.firstName, this.lastName, this.completeFeedback);
    }

    private String feedbackUsingConcatenation(String s1, String s2, String s3,
                                              String s4, String s5) {
        String concatenatedFeedback = (s1 == null ? "" : s1) +
                                      (s2 == null ? "" : s2) +
                                      (s3 == null ? "" : s3) +
                                      (s4 == null ? "" : s4) +
                                      (s5 == null ? "" : s5);
        return concatenatedFeedback;
    }

    private StringBuilder feedbackUsingStringBuilder(String s1, String s2, String s3,
                                                     String s4, String s5) {
        StringBuilder sb = new StringBuilder();
        if (s1 != null) sb.append(s1);
        if (s2 != null) sb.append(s2);
        if (s3 != null) sb.append(s3);
        if (s4 != null) sb.append(s4);
        if (s5 != null) sb.append(s5);
        return sb;
    }

    private boolean checkFeedbackLength(String feedback) {
        this.longFeedback = (feedback != null && feedback.length() > 500);
        return this.longFeedback;
    }

    private void createReviewID(String firstName, String lastName, String completeFeedback) {
        this.reviewID = nextID++;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getCompleteFeedback() {
        return completeFeedback;
    }

    public int getReviewID() {
        return reviewID;
    }

    public boolean isLongFeedback() {
        return longFeedback;
    }
}