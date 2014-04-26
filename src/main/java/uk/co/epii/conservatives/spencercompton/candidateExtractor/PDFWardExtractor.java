package uk.co.epii.conservatives.spencercompton.candidateExtractor;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import java.io.IOException;
import java.net.URL;

/**
 * User: James Robinson
 * Date: 26/04/2014
 * Time: 00:07
 */
public abstract class PDFWardExtractor {

  private PDFTextStripper pdfTextStripper;

  public PDFWardExtractor() {
    try {
      this.pdfTextStripper = new PDFTextStripper();
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public Ward extract(URL url) {
    PDDocument document = load(url);
    try {
      return extractFromText(pdfTextStripper.getText(document));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  protected abstract Ward extractFromText(String text);

  private PDDocument load(URL url) {
    try {
      return PDDocument.load(url);
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
