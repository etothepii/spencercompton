package uk.co.epii.conservatives.spencercompton.candidateExtrator;

import java.util.ArrayList;
import java.util.List;

/**
 * User: James Robinson
 * Date: 26/04/2014
 * Time: 00:09
 */
public class Ward {
  public final String name;
  public final String url;
  public final List<Candidate> candidates;

  public Ward(String name) {
    this(name, null, new ArrayList<Candidate>());
  }

  public Ward(String name, String url, List<Candidate> candidates) {
    this.name = name;
    this.url = url;
    this.candidates = candidates;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Ward ward = (Ward) o;

    if (candidates != null ? !candidates.equals(ward.candidates) : ward.candidates != null) return false;
    if (name != null ? !name.equals(ward.name) : ward.name != null) return false;
    if (url != null ? !url.equals(ward.url) : ward.url != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = name != null ? name.hashCode() : 0;
    result = 31 * result + (url != null ? url.hashCode() : 0);
    result = 31 * result + (candidates != null ? candidates.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Ward{" +
            "name='" + name + '\'' +
            ", candidates=" + candidates +
            '}';
  }
}
