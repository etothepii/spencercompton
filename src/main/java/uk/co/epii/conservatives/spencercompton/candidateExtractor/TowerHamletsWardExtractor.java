package uk.co.epii.conservatives.spencercompton.candidateExtractor;

import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableRow;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * User: James Robinson
 * Date: 26/04/2014
 * Time: 00:08
 */
public class TowerHamletsWardExtractor extends DocWardExtractor {

  private static final int NAME_COLUMN = 0;
  private static final int PARTY_COLUMN = 2;

  @Override
  protected int getFooterRows() {
    return 0;
  }

  @Override
  protected int getHeaderRows() {
    return 1;
  }

  @Override
  protected int getDescriptionCell() {
    return 2;
  }

  @Override
  protected int getNameCell() {
    return 0;
  }

  @Override
  protected String getTitleNameCell() {
    return "Name of Candidate";
  }
}
