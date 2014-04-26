package uk.co.epii.conservatives.spencercompton.csvbuilder;

/**
 * User: James Robinson
 * Date: 26/04/2014
 * Time: 22:53
 */
public class PollingArea {

  private static int count = 1;

  private int id;
  private String name;
  private String childType;
  private int parent;

  public PollingArea(String name, int parent) {
    synchronized (PollingArea.class) {
      id = count++;
    }
    this.name = name;
    this.parent = parent;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getChildType() {
    return childType;
  }

  public void setChildType(String childType) {
    this.childType = childType;
  }

  public int getParent() {
    return parent;
  }

  public void setParent(int parent) {
    this.parent = parent;
  }

  @Override
  public String toString() {
    return String.format("%s,\"%s\",%s,%s",
            id + "",
            name,
            enquote(childType),
            parent + "");
  }

  private String enquote(String childType) {
    if (childType == null) {
      return "NULL";
    }
    return String.format("\"%s\"", childType);
  }
}
