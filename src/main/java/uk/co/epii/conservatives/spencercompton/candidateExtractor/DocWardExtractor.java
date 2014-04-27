package uk.co.epii.conservatives.spencercompton.candidateExtractor;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.*;

import java.io.IOException;
import java.net.URL;

/**
 * User: James Robinson
 * Date: 27/04/2014
 * Time: 11:28
 */
public abstract class DocWardExtractor {

  private HWPFDocument document;
  private Range range;
  protected Ward ward;

  public Ward extract(URL url) throws IOException {
    document = new HWPFDocument(url.openStream());
    range = document.getRange();
    int startOfTable = findStartOfTable(getTitleNameCell());
    Table table = range.getTable(range.getParagraph(startOfTable));
    addCandidates(table);
    return ward;
  }

  protected void addCandidates(Table table) {
    for (int i = getHeaderRows(); i < table.numRows() - getFooterRows(); i++) {
      addCandidate(table.getRow(i));
    }
  }

  protected abstract int getFooterRows();

  protected abstract int getHeaderRows();

  private void addCandidate(TableRow row) {
    String name = extractText(row.getCell(getNameCell()));
    String description = extractText(row.getCell(getDescriptionCell()));
    ward.candidates.add(new Candidate(name, description));
  }

  protected String extractText(TableCell cell) {
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < cell.numParagraphs(); i++) {
      if (i != 0) {
        stringBuilder.append(" ");
      }
      stringBuilder.append(cell.getParagraph(i).text().trim());
    }
    return stringBuilder.toString();
  }

  protected abstract int getDescriptionCell();

  protected abstract int getNameCell();

  private int findStartOfTable(String titleNameCell) {
    for (int i = 0; i < range.numParagraphs(); i++) {
      Paragraph paragraph = range.getParagraph(i);
      String text = paragraph.text().trim();
      if (text.matches(".* Ward")) {
        ward = new Ward(text);
      }
      if (paragraph.isInTable()) {
        if (text.equals(titleNameCell)) {
          return i;
        }
        System.out.println(String.format("'%s'", text));
      };
    }
    throw new RuntimeException("Table starting cell not found");
  }

  protected abstract String getTitleNameCell();

}
