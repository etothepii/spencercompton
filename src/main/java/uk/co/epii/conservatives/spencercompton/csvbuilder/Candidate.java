package uk.co.epii.conservatives.spencercompton.csvbuilder;

/**
 * Created with IntelliJ IDEA.
 * User: James Robinson
 * Date: 26/04/2014
 * Time: 22:47
 */
public class Candidate {

  private static int count = 1;

  private int id;
  private String surname;
  private String firstNames;
  private Integer politicalParty;
  private int pollingArea;
  private boolean displayable;
  private Integer order;

  public Candidate(int pollingArea) {
    synchronized (Candidate.class) {
      id = count++;
    }
    this.pollingArea = pollingArea;
    displayable = true;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public String getFirstNames() {
    return firstNames;
  }

  public void setFirstNames(String firstNames) {
    this.firstNames = firstNames;
  }

  public Integer getPoliticalParty() {
    return politicalParty;
  }

  public void setPoliticalParty(Integer politicalParty) {
    this.politicalParty = politicalParty;
  }

  public int getPollingArea() {
    return pollingArea;
  }

  public void setPollingArea(int pollingArea) {
    this.pollingArea = pollingArea;
  }

  public boolean isDisplayable() {
    return displayable;
  }

  public void setDisplayable(boolean displayable) {
    this.displayable = displayable;
  }

  public Integer getOrder() {
    return order;
  }

  public void setOrder(Integer order) {
    this.order = order;
  }

  @Override
  public String toString() {
    return String.format("%s,\"%s\",\"%s\",%s,%s,%s,%s",
            id + "",
            surname,
            firstNames,
            politicalParty == null ? "NULL" : (politicalParty + ""),
            pollingArea + "",
            displayable ? "1" : "0",
            order == null ? "NULL" : order);

  }
}
