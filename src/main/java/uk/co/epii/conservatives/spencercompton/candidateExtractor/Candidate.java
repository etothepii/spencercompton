package uk.co.epii.conservatives.spencercompton.candidateExtractor;

/**
 * User: James Robinson
 * Date: 26/04/2014
 * Time: 00:09
 */
public class Candidate {

  public final String name;
  public final String description;

  public Candidate(String name, String description) {
    this.name = name;
    this.description = description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Candidate candidate = (Candidate) o;

    if (description != null ? !description.equals(candidate.description) : candidate.description != null) return false;
    if (name != null ? !name.equals(candidate.name) : candidate.name != null) return false;

    return true;
  }

  @Override
  public String toString() {
    return "Candidate{" +
            "name='" + name + '\'' +
            ", description='" + description + '\'' +
            '}';
  }

  @Override
  public int hashCode() {
    int result = name != null ? name.hashCode() : 0;
    result = 31 * result + (description != null ? description.hashCode() : 0);
    return result;
  }

  public String display() {
    return String.format("    * %s - %s", name, description);
  }
}
