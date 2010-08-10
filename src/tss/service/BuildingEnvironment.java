package tss.service;

/**
 *
 * @author Dong Won Kim
 */
import java.util.List;
import java.util.LinkedList;
public class BuildingEnvironment {

    private String courseId; 
    private String topicTitle; 
    private String station;
    private String placeName;
    private String placeNickName;
    private String guideName;
    private String guideNickName;
    private List<String> nextStations = new LinkedList<String>();
    private String introduction;
    private String question;
    private String answer;
    private String description;
    private String conclusion;
    private String golds;    
    private String experiencePoints;
    private String tool;
    private String armor;
    private String weapon;
    private String treasureBoxName;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getGuideName() {
        return guideName;
    }

    public void setGuideName(String guideName) {
        this.guideName = guideName;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public List<String> getNextStations() {
        return nextStations;
    }

    public void setNextStations(List<String> nextStations) {
        this.nextStations = nextStations;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public String getExperiencePoints() {
        return experiencePoints;
    }

    public void setExperiencePoints(String experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

    public String getGolds() {
        return golds;
    }

    public void setGolds(String golds) {
        this.golds = golds;
    }

    public String getArmor() {
        return armor;
    }

    public void setArmor(String armor) {
        this.armor = armor;
    }

    public String getTool() {
        return tool;
    }

    public void setTool(String tool) {
        this.tool = tool;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public String getTreasureBoxName() {
        return treasureBoxName;
    }

    public void setTreasureBoxName(String treasureBoxName) {
        this.treasureBoxName = treasureBoxName;
    }

    public String getGuideNickName() {
        return guideNickName;
    }

    public void setGuideNickName(String guideNickName) {
        this.guideNickName = guideNickName;
    }

    public String getPlaceNickName() {
        return placeNickName;
    }

    public void setPlaceNickName(String placeNickName) {
        this.placeNickName = placeNickName;
    }    
}
